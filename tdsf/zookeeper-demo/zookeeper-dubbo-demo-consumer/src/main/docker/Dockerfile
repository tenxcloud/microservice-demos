FROM 192.168.1.52/system_containers/oraclejdk:8u231

MAINTAINER sparkxiong shi.xiong@tenxcloud.com

ENV PROFILE="test" \
    K8S_NAMESPACE="default" \
    MESH_SWITCHER_MESH="false" \
    DEPLOY_TYPE="container" \
    MESH_SVC_NAME="zookeeper-dubbo-demo-consumer" \
    ZOOKEEPER_SERVER_URL="zookeeper.test" \
    ZOOKEEPER_SERVER_PORT=2181 \
    DUBBO_PROVIDER_PORT=20880 \
    CONSUMER_RETRIES=3 \
    JVM="-Xms256m -Xmx1g -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Djava.security.egd=file:/dev/./urandom"

ADD zookeeper-dubbo-demo-consumer-1.0-SNAPSHOT.jar /app/app.jar

WORKDIR "/app"

EXPOSE 18082

CMD java -jar /app/app.jar
