/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2022 TenxCloud. All Rights Reserved.
 * 2022-05-30  @author zhao.laihe
 */

package main

import (
	"fmt"
	"log"
	"net/http"
	"os"

	"github.com/SkyAPM/go2sky"
	httpPlugin "github.com/SkyAPM/go2sky/plugins/http"
	"github.com/SkyAPM/go2sky/reporter"
)

const (
	instanceNameFmt = "%s::%s"
	serviceNameFmt  = "%s@@%s@@%s::%s"
)

func getSkyHandler() (func(http.Handler) http.Handler, error) {
	serviceName, err := setServiceName()
	if err != nil {
		log.Printf("[SKY] set service name failed: %s", err)
		return nil, err
	}

	_, err = setInstanceName()
	if err != nil {
		log.Printf("[SKY] set instance name failed: %s", err)
		return nil, err
	}

	r, err := reporter.NewGRPCReporter(os.Getenv("OAP_SERVER"))
	if err != nil {
		log.Printf("[SKY] create grpc reporter failed: %s", err)
		return nil, err
	}

	tracer, err := go2sky.NewTracer(serviceName, go2sky.WithReporter(r))
	if err != nil {
		log.Printf("[SKY] create tracer failed: %s", err)
		return nil, err
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
		log.Printf("[SKY] create server middleware failed: %s", err)
		return nil, err
	}
	return sm, nil
}

func setInstanceName() (string, error) {
	passVersion := os.Getenv("PAAS_VERSION")
	podID := os.Getenv("HOSTNAME")

	instanceName := fmt.Sprintf(instanceNameFmt, passVersion, podID)

	// set SW_AGENT_INSTANCE_NAME for skywalking
	err := os.Setenv("SW_AGENT_INSTANCE_NAME", instanceName)
	if err != nil {
		return "", err
	}
	return instanceName, nil
}

func setServiceName() (string, error) {
	clusterID := os.Getenv("CLUSTER_ID")
	passTenant := os.Getenv("PAAS_TENANT")
	k8sNamespace := os.Getenv("K8S_NAMESPACE")
	instanceName := os.Getenv("INSTANCE_NAME")

	serviceName := fmt.Sprintf(serviceNameFmt, passTenant, clusterID, k8sNamespace, instanceName)

	// set SW_AGENT_NAME for skywalking
	err := os.Setenv("SW_AGENT_NAME", serviceName)
	if err != nil {
		return "", err
	}

	// set SW_AGENT_COLLECTOR_BACKEND_SERVICES for skywalking
	err = os.Setenv("SW_AGENT_COLLECTOR_BACKEND_SERVICES", os.Getenv("OAP_SERVER"))
	if err != nil {
		return "", err
	}
	return serviceName, nil
}
