FROM 172.22.50.227/system_containers/oraclejdk:8u231
MAINTAINER tmf tmf@tenxcloud.com

ENV PROFILE=test \
 NACOS_ADDR=spring-cloud-discovery.test-project:8848 \
 NACOS_NAMESPACE=fb18e7db9d32 \
 NACOS_GROUP=DEFAULT_GROUP \
 SERVICE_NAME=demo \
 CLUSTER_ID=test \
 CONFIG_GROUP=DEFAULT_GROUP \
 PAAS_NS=test-project \
 SENTINEL_DASHBOARD_ADDR=sentinel-zengbang-dashboard.test-project:8080 \
 SENTINEL_PORT=8888 \
 SENTINEI_IP=localhost \
 NACOS_CONFIG_FILE_EXTENSION=yml \
 MASTER_BACKUP_ENABLED=true \
 MASTER_BACKUP_UPDATE_PERIOD=10 \
 NACOS_CLUSTER_ENABLED=true\
 SENTINEL_ENABLED=true\
 NACOS_USERNAME=nacos \
 NACOS_PASSWORD=nacos \
 ENDPOINTS_INCLUDE=* \
 ENDPOINTS_BASE_PATH=/ \
 JVM="-Xms256m -Xmx1g -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Djava.security.egd=file:/dev/./urandom"


ADD *.jar /app/app.jar
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

WORKDIR "/app"

EXPOSE 8401
CMD java -jar /app/app.jar