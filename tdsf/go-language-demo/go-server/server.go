package main

import (
	"fmt"
	"github.com/SkyAPM/go2sky"
	httpPlugin "github.com/SkyAPM/go2sky/plugins/http"
	"github.com/SkyAPM/go2sky/reporter"
	"io"
	"log"
	"net/http"
	"os"
)

/**
skywalking测试
*/
func serveHTTP(w http.ResponseWriter, r *http.Request) {

}

type emptyHandler struct {
}

func (h emptyHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	fmt.Println("hello word")
	_, _ = io.WriteString(w, "go-server__"+os.Getenv("PAAS_VERSION"))
}

func main() {
	//r, err := reporter.NewGRPCReporter("192.168.2.142:31082")
	//TODO 测试使用域名上报数据失败，IP成功
	r, err := reporter.NewGRPCReporter(os.Getenv("OAP_SERVER"))
	if err != nil {
		log.Fatalf("new reporter error %v \n", err)
		return
	}
	tracer, err := go2sky.NewTracer("go-server", go2sky.WithReporter(r))
	if err != nil {
		log.Fatalf("create tracer error %v \n", err)
	}
	sm, err := httpPlugin.NewServerMiddleware(tracer,
		httpPlugin.WithServerTag("paas.namespace", os.Getenv("PAAS_NAMESPACE")),
		httpPlugin.WithServerTag("paas.tenant", os.Getenv("PAAS_TENANT")),
		httpPlugin.WithServerTag("paas.version", os.Getenv("PAAS_VERSION")),
		httpPlugin.WithServerTag("nacos.namespace", os.Getenv("NACOS_NAMESPACE")),
		httpPlugin.WithServerTag("nacos.group", os.Getenv("NACOS_GROUP")),
		httpPlugin.WithServerTag("agent.instance_name", os.Getenv("INSTANCE_NAME")),
		httpPlugin.WithServerTag("agent.cluster_id", os.Getenv("CLUSTER_ID")),
		httpPlugin.WithServerTag("paas.clusterid", os.Getenv("CLUSTER_ID")))
	if err != nil {
		log.Fatalf("create server middleware error %v \n", err)
	}
	http.HandleFunc("/", serveHTTP)
	_ = http.ListenAndServe("0.0.0.0:8082", sm(emptyHandler{}))

}
