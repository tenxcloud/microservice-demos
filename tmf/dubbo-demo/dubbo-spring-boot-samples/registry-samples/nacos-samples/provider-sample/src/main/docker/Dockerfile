FROM 192.168.1.52/tenx_containers/oraclejdk:8u151

MAINTAINER zhangdalei zhangdalei@tenxcloud.com

ENV PROFILE=test \
 NACOS_ADDR=spring-cloud-discovery.test-project:8848 \
 NACOS_NAMESPACE=fb18e7db9d32 \
 NACOS_GROUP=DEFAULT_GROUP \
 CONFIG_GROUP=DEFAULT_GROUP \
 SERVICE_NAME=demo \
 PAAS_NS=test-project \
 MYSQL_HOST=192.168.2.102 \
 MYSQL_PORT=3306 \
 MYSQL_USERNAME=root \
 MYSQL_PASSWORD=123456 \
 MYSQL_DB=spring_cloud \
 REDIS_HOST=192.168.2.134 \
 REDIS_PORT=6379 \
 REDIS_PASSWORD="" \
 RABBITMQ_HOST=192.168.2.134 \
 RABBITMQ_PORT=5672 \
 RABBITMQ_USERNAME=guest \
 RABBITMQ_PASSWORD=guest \
 RABBITMQ_VIRTUAL_HOST=/ \
 SENTINEL_DASHBOARD_ADDR=sentinel-zengbang-dashboard.test-project:8080 \
 SENTINEL_PORT=8888 \
 SENTINEI_IP=localhost \
 NACOS_CONFIG_FILE_EXTENSION=yml \
 NACOS_USERNAME=nacos \
 NACOS_PASSWORD=nacos \
 ROCKET_ADDR=localhost:9876 \
 KAFKA_SERVER=192.168.90.131:9092 \
 JVM="-Xms256m -Xmx1g -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Djava.security.egd=file:/dev/./urandom -Ddubbo.provider.port=20880"

ADD *.jar /app/app.jar
WORKDIR "/app"

EXPOSE 8082
EXPOSE 20880
CMD ["java","-jar", "/app/app.jar"]