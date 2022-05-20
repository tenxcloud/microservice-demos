FROM 192.168.1.52/tenx_containers/oraclejdk:8u151

MAINTAINER zhangdalei zhangdalei@tenxcloud.com

ENV PROFILE=test \
    NACOS_GROUP=dev \
    NACOS_CONFIG_ENABLED=false \
    CONFIG_GROUP=DEFAULT_GROUP \
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
    MESH_SWITCHER_MESH=false \
    DEPLOY_TYPE=container \
    MASTER_BACKUP_ENABLED=false \
    MASTER_BACKUP_UPDATE_PERIOD=10 \
    NACOS_USERNAME=nacos \
    NACOS_PASSWORD=nacos \
    ROCKET_ADDR=192.168.4.173:9876 \
    NACOS_ADDR=spring-cloud-discovery.apm:8848 \
    NACOS_NAMESPACE=90076ad15eee \
    NACOS_USERNAME=mHf8qaurQZAI \
    NACOS_PASSWORD=XwWlqqL9upWv \
    SERVICE_NAME=middleware-demo \
    COMPONENT_NS=apm \
    CLUSTER_ID=CID-585ae638bd68 \
    PAAS_TENANT=zhangshuo \
    PAAS_NS=apm \

    JVM="-Xms256m -Xmx1g -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Djava.security.egd=file:/dev/./urandom"


ADD *.jar /app/app.jar
WORKDIR "/app"

EXPOSE 8080
CMD java -jar /app/app.jar


