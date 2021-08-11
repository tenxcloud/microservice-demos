FROM golang as builder
MAINTAINER shixiong.tenxcloud.com

ENV K8S_NAMESPACE syy
ENV PAAS_NAMESPACE syy
ENV PAAS_TENANT syy
ENV PAAS_VERSION v1
ENV NACOS_GROUP DEFAULT_GROUP
ENV NACOS_NAMESPACE MESH
ENV INSTANCE_NAME ''
ENV CLUSTER_ID CID-585ae638bd68
ENV OAP_SERVER 192.168.2.142:31082

WORKDIR /workspace
# Copy the Go Modules manifests
COPY go.mod go.mod
COPY go.sum go.sum
# cache deps before building and copying source so that we don't need to re-download as much
# and so that source changes don't invalidate our downloaded layer
RUN go env -w GOPROXY=https://goproxy.cn,direct
RUN go mod download
# Copy the go source
COPY client.go client.go

EXPOSE 8081

CMD go run client.go
