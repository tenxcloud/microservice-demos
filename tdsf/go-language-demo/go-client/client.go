package main

import (
	"fmt"
	"github.com/SkyAPM/go2sky"
	httpPlugin "github.com/SkyAPM/go2sky/plugins/http"
	"github.com/SkyAPM/go2sky/reporter"
	"github.com/gin-gonic/gin"
	"io/ioutil"
	"log"
	"net/http"
	"os"
)
/**
skywalking测试
 */
func main() {
	//r, err := reporter.NewGRPCReporter("192.168.2.142:31082")
	//TODO 测试使用域名上报数据失败，需要IP才行
	r, err := reporter.NewGRPCReporter(os.Getenv("OAP_SERVER"))
	if err != nil {
		log.Fatalf("new reporter error %v \n", err)
		return
	}

	tracer, err := go2sky.NewTracer("go-client", go2sky.WithReporter(r))
	if err != nil {
		log.Fatalf("create tracer error %v \n", err)
	}

	client, err := httpPlugin.NewClient(tracer,
		httpPlugin.WithClientTag("paas.namespace", os.Getenv("PAAS_NAMESPACE")),
		httpPlugin.WithClientTag("paas.tenant", os.Getenv("PAAS_TENANT")),
		httpPlugin.WithClientTag("paas.version", os.Getenv("PAAS_VERSION")),
		httpPlugin.WithClientTag("nacos.namespace", os.Getenv("NACOS_NAMESPACE")),
		httpPlugin.WithClientTag("nacos.group", os.Getenv("NACOS_GROUP")),
		httpPlugin.WithClientTag("agent.instance_name", os.Getenv("INSTANCE_NAME")),
		httpPlugin.WithClientTag("agent.cluster_id", os.Getenv("CLUSTER_ID")),
		httpPlugin.WithClientTag("paas.clusterid", os.Getenv("CLUSTER_ID")))

	if err != nil {
		log.Fatalf("create client error %v \n", err)
	}
	// call server
	request, err := http.NewRequest("GET", fmt.Sprintf("http://go-server:8082"), nil)
	//request, err := http.NewRequest("GET", fmt.Sprintf("http://127.0.0.1:8082"), nil)
	if err != nil {
		log.Fatalf("unable to create http request: %+v\n", err)
	}
	g := gin.Default()
	g.GET("/", func(c *gin.Context) {
		res, err := client.Do(request)
		if err != nil {
			log.Fatalf("unable to do http request: %+v\n", err)
		}
		defer res.Body.Close()
		if res.StatusCode == http.StatusOK {
			bodyBytes, err := ioutil.ReadAll(res.Body)
			if err != nil {
				log.Fatalf("unable to do http request: %+v\n", err)
			}
			bodyString := string(bodyBytes)
			c.String(res.StatusCode, bodyString)
		}
	})

	_ = g.Run("0.0.0.0:8081")
}
