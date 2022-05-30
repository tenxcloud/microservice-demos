ARG BUILDER_IMAGE_NAME=golang
ARG BUILDER_IMAGE_TAG=1.18.2
ARG BASE_IMAGE_NAME=alpine
ARG BASE_IMAGE_TAG=3.15

FROM ${BUILDER_IMAGE_NAME}:${BUILDER_IMAGE_TAG} AS builder

COPY . /app
WORKDIR /app

ENV GO111MODULE=on
ENV GOPROXY=https://goproxy.cn
RUN CGO_ENABLED=0 GOOS=linux go build -ldflags "-w" -o /app/details -v main.go client.go

FROM ${BASE_IMAGE_NAME}:${BASE_IMAGE_TAG}

COPY --from=builder /app/details /app/details

ARG service_version
ENV SERVICE_VERSION ${service_version:-v1}
ARG enable_external_book_service
ENV ENABLE_EXTERNAL_BOOK_SERVICE ${enable_external_book_service:-false}

ENV K8S_NAMESPACE "demos"
ENV PAAS_NAMESPACE "demos"
ENV PAAS_TENANT "TENANT-2VSrqMr3gbUH"
ENV PAAS_VERSION "v1"
ENV INSTANCE_NAME "details"
ENV CLUSTER_ID "CID-d5e7166aea32"
ENV OAP_SERVER "192.168.90.225:34535"

EXPOSE 9080

CMD ["/app/details", "9080"]
