FROM 172.22.50.227/system_containers/oraclejdk:8u231


ENV PROFILE=test \
    PAAS_NS=default \
    MESH_SWITCHER_MESH=false \
    DEPLOY_TYPE=container \
    MESH_SVC_NAME="zk-dubbo-provider" \
    ZOOKEEPER_SERVER_URL="zookeeper.test" \
    ZOOKEEPER_SERVER_PORT=2181 \
    DUBBO_PROVIDER_PORT=20880 \
    JVM="-Xms256m -Xmx1g -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Djava.security.egd=file:/dev/./urandom"

ADD zk-dubbo-provider-1.0-SNAPSHOT.jar /app/app.jar

WORKDIR "/app"

EXPOSE 20880

CMD java -jar /app/app.jar
