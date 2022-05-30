/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2022 TenxCloud. All Rights Reserved.
 * 2022-05-30  @author zhao.laihe
 */

package main

import (
	"context"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
	"os"
	"os/signal"
	"strconv"
	"strings"
	"syscall"
	"time"

	"github.com/gorilla/mux"
)

const (
	address = "0.0.0.0"
)

func main() {
	appName := os.Args[0]
	if len(os.Args) < 1 {
		log.Printf("usage: %s port", appName)
		os.Exit(-1)
	}

	port, err := strconv.Atoi(os.Args[1])
	if err != nil {
		log.Print("please provide numeric port")
		os.Exit(-1)
	}

	r := mux.NewRouter()
	r.HandleFunc("/health", healthHandler)
	r.PathPrefix("/details").HandlerFunc(detailsHandler)

	// package skywalking handler
	sm, err := getSkyHandler()
	if err != nil {
		log.Printf("init skywalking handler failed: %s", err)
		os.Exit(-1)
	}

	var exitCh = make(chan struct{}, 1)
	go func() {
		addr := fmt.Sprintf("%s:%d", address, port)
		log.Printf("Start http server: %s", addr)
		if err = http.ListenAndServe(addr, sm(r)); err != nil {
			log.Printf("Start http server failed: %s", err)
			exitCh <- struct{}{}
		}
	}()

	signalCh := make(chan os.Signal)
	signal.Notify(signalCh, syscall.SIGINT, syscall.SIGTERM)
	select {
	case <-exitCh:
		log.Printf("Http server exit")
	case sig := <-signalCh:
		log.Printf("Recieve signal %v, and exit", sig)
	}
}

func healthHandler(w http.ResponseWriter, r *http.Request) {
	log.Printf("[RECV] %s %s", r.Method, r.URL.Path)

	if r.Method != http.MethodGet && r.Method != http.MethodPost {
		w.WriteHeader(http.StatusMethodNotAllowed)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)

	err := json.NewEncoder(w).Encode(
		map[string]string{
			"status": "Details is healthy",
		},
	)
	if err != nil {
		log.Println(err)
	}
}

func detailsHandler(w http.ResponseWriter, r *http.Request) {
	log.Printf("[RECV] %s %s", r.Method, r.URL.Path)

	if r.Method != http.MethodGet && r.Method != http.MethodPost {
		w.WriteHeader(http.StatusMethodNotAllowed)
		return
	}

	pathParts := strings.Split(r.URL.Path, "/")
	headers := getForwardHeaders(r)

	w.Header().Set("Content-Type", "application/json")
	w.Header().Set("Connection", "Keep-Alive")

	// [  details id]
	if len(pathParts) < 3 {
		w.WriteHeader(http.StatusBadRequest)
		err := json.NewEncoder(w).Encode(
			map[string]string{
				"error": "please provide numeric product id",
			},
		)
		if err != nil {
			log.Println(err)
		}
		return
	}

	id, err := strconv.Atoi(pathParts[2])
	if err != nil {
		w.WriteHeader(http.StatusBadRequest)
		err := json.NewEncoder(w).Encode(
			map[string]string{
				"error": "please provide numeric product id",
			},
		)
		if err != nil {
			log.Println(err)
		}
		return
	}

	details, err := getBookDetails(id, headers)
	if err != nil {
		w.WriteHeader(http.StatusBadRequest)
		err := json.NewEncoder(w).Encode(map[string]string{"error": fmt.Sprint(err)})
		if err != nil {
			log.Println(err)
		}
		return
	}

	for k, v := range headers {
		w.Header().Set(k, strings.Join(v, ","))
	}

	w.WriteHeader(http.StatusOK)
	if err := json.NewEncoder(w).Encode(details); err != nil {
		log.Println(err)
	}
}

func getForwardHeaders(req *http.Request) http.Header {
	inComingHeaders := map[string]bool{
		// All applications should propagate x-request-id. This header is
		// included in access log statements and is used for consistent trace
		// sampling and log sampling decisions in Istio.
		"x-request-id": true,

		// Lightstep tracing header. Propagate this if you use lightstep tracing
		// in Istio (see
		// https://istio.io/latest/docs/tasks/observability/distributed-tracing/lightstep/)
		// Note: this should probably be changed to use B3 or W3C TRACE_CONTEXT.
		// Lightstep recommends using B3 or TRACE_CONTEXT and most application
		// libraries from lightstep do not support x-ot-span-context.
		"x-ot-span-context": true,

		// Datadog tracing header. Propagate these headers if you use Datadog
		// tracing.
		"x-datadog-trace-id":          true,
		"x-datadog-parent-id":         true,
		"x-datadog-sampling-priority": true,

		// W3C Trace Context. Compatible with OpenCensusAgent and Stackdriver Istio
		// configurations.
		"traceparent": true,
		"tracestate":  true,

		// Cloud trace context. Compatible with OpenCensusAgent and Stackdriver Istio
		// configurations.
		"x-cloud-trace-context": true,

		// Grpc binary trace context. Compatible with OpenCensusAgent nad
		// Stackdriver Istio configurations.
		"grpc-trace-bin": true,

		// b3 trace headers. Compatible with Zipkin, OpenCensusAgent, and
		// Stackdriver Istio configurations.
		"x-b3-traceid":      true,
		"x-b3-spanid":       true,
		"x-b3-parentspanid": true,
		"x-b3-sampled":      true,
		"x-b3-flags":        true,

		// Application-specific headers to forward.
		"end-user":   true,
		"user-agent": true,
	}

	headers := http.Header{}
	for k, h := range req.Header {
		// Need to strconv to lower
		if inComingHeaders[strings.ToLower(k)] {
			headers[k] = h
		}
	}
	return headers
}

type bookDetails struct {
	ID        int    `json:"id"`
	Author    string `json:"author"`
	Year      string `json:"year"`
	Type      string `json:"type"`
	Pages     int    `json:"pages"`
	Publisher string `json:"publisher"`
	Language  string `json:"language"`
	ISBN10    string `json:"ISBN-10"`
	ISBN13    string `json:"ISBN-13"`
}

// TODO: provide details on different books.
func getBookDetails(id int, headers http.Header) (bookDetails, error) {
	enableExternalBookService := os.Getenv("ENABLE_EXTERNAL_BOOK_SERVICE")
	if enableExternalBookService == "true" {
		log.Println("Enable external book service")
		// the ISBN of one of Comedy of Errors on the Amazon
		// that has Shakespeare as the single author
		isbn := "0486424618"
		return fetchDetailsFromExternalService(isbn, id, headers)
	}
	return bookDetails{
		ID:        id,
		Author:    "William Shakespeare",
		Year:      "1595",
		Type:      "paperback",
		Pages:     200,
		Publisher: "PublisherA",
		Language:  "English",
		ISBN10:    "1234567890",
		ISBN13:    "123-1234567890",
	}, nil
}

type Details struct {
	TotalItems int    `json:"totalItems"`
	Items      []Item `json:"items"`
}

type Item struct {
	V volumeInfo `json:"volumeInfo"`
}

type volumeInfo struct {
	Title         string                `json:"title"`
	Publisher     string                `json:"publisher"`
	Authors       []string              `json:"authors"`
	PublishedDate string                `json:"publishedDate"`
	Industry      []industryIdentifiers `json:"industryIdentifiers"`
	PageCount     int                   `json:"pageCount"`
	PrintType     string                `json:"printType"`
	Language      string                `json:"language"`
}

type industryIdentifiers struct {
	Type       string `json:"type"`
	Identifier string `json:"identifier"`
}

func fetchDetailsFromExternalService(isbn string, id int, headers http.Header) (bookDetails, error) {
	var (
		uri string
		tr  http.Transport
	)
	// DO_NOT_ENCRYPT is used to configure the details service to use either
	// HTTP (true) or HTTPS (false, default) when calling the external service to
	// retrieve the book information.
	enableEncrypt := os.Getenv("DO_NOT_ENCRYPT")
	if enableEncrypt == "true" {
		// TODO
		log.Println("Encrypt mode")
		uri = fmt.Sprintf("https://www.googleapis.com/books/v1/volumes?q=isbn:%s", isbn)
		// Unless this environment variable is set to true, the app will use TLS (HTTPS)
		// to access external services.
		tr.TLSClientConfig.InsecureSkipVerify = false
	} else {
		uri = fmt.Sprintf("https://www.googleapis.com/books/v1/volumes?q=isbn:%s", isbn)
	}

	client := http.Client{Transport: &tr, Timeout: 5 * time.Second}

	req, err := http.NewRequestWithContext(context.Background(), http.MethodGet, uri, nil)
	if err != nil {
		return bookDetails{}, err
	}
	req.Header = headers

	response, err := client.Do(req)
	if err != nil {
		return bookDetails{}, err
	}

	data, err := ioutil.ReadAll(response.Body)
	if err != nil {
		return bookDetails{}, err
	}

	var res = Details{}
	err = json.Unmarshal(data, &res)
	if err != nil {
		return bookDetails{}, err
	}

	if res.TotalItems < 1 {
		return bookDetails{}, nil
	}

	book := res.Items[0].V
	language := "English"
	switch book.Language {
	case "en":
	default:
		language = "unknown"
	}

	printType := "paperback"
	switch book.PrintType {
	case "BOOK":
	default:
		printType = "unknown"
	}

	isbn10 := getISBN(book, "ISBN_10")
	isbn13 := getISBN(book, "ISBN_13")

	return bookDetails{
		ID:        id,
		Author:    book.Authors[0],
		Year:      book.PublishedDate,
		Type:      printType,
		Pages:     book.PageCount,
		Publisher: book.Publisher,
		Language:  language,
		ISBN10:    isbn10,
		ISBN13:    isbn13,
	}, nil
}

func getISBN(book volumeInfo, isbnType string) string {
	var arr []industryIdentifiers
	for _, v := range book.Industry {
		if v.Type == isbnType {
			arr = append(arr, v)
		}
	}
	return arr[0].Identifier
}
