FROM 192.168.1.52/system_containers/oraclejdk:8u231

MAINTAINER sparkxiong shi.xiong@tenxcloud.com

ENV PROFILE=test \
    NACOS_ADDR=spring-cloud-discovery.system-tmf-sharedns:8848 \
    NACOS_NAMESPACE=public \
    NACOS_GROUP=DEFAULT_GROUP \
    K8S_NAMESPACE=deafult \
    PAAS_NA=deafult \
    NACOS_USERNAME=nacos \
    NACOS_PASSWORD=nacos \
    MESH_SWITCHER_MESH=false\
    DEPLOY_TYPE=container\
    MESH_SVC_NAME=nacos-dubbo-demo-provider\
    JVM="-Xms256m -Xmx1g -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Djava.security.egd=file:/dev/./urandom"

ADD *.jar /app/app.jar

WORKDIR "/app"

EXPOSE 20880

CMD java -jar /app/app.jar