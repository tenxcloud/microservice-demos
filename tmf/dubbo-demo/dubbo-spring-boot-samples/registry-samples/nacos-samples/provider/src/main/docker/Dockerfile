FROM 172.22.50.227/system_containers/oraclejdk:8u231

MAINTAINER zhangdalei zhangdalei@tenxcloud.com

ENV PROFILE=test \
 NACOS_ADDR=spring-cloud-discovery.test-project:8848 \
 NACOS_NAMESPACE=fb18e7db9d32 \
 NACOS_GROUP=DEFAULT_GROUP \
 CONFIG_GROUP=DEFAULT_GROUP \
 SERVICE_NAME=demo \
 PAAS_NS=test-project \
 SENTINEL_DASHBOARD_ADDR=sentinel-zengbang-dashboard.test-project:8080 \
 SENTINEL_PORT=8888 \
 SENTINEI_IP=localhost \
 NACOS_CONFIG_FILE_EXTENSION=yml \
 NACOS_USERNAME=nacos \
 NACOS_PASSWORD=nacos \
 JVM="-Xms256m -Xmx1g -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Djava.security.egd=file:/dev/./urandom -Ddubbo.provider.port=20880"

ADD *.jar /app/app.jar
WORKDIR "/app"

EXPOSE 8082
EXPOSE 20880
CMD ["java","-jar", "/app/app.jar"]