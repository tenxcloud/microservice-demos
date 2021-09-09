FROM 192.168.1.52/system_containers/oraclejdk:8u291

MAINTAINER sparkxiong shi.xiong@tenxcloud.com

ENV JVM="-Xms256m -Xmx1g -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Djava.security.egd=file:/dev/./urandom"

ADD eureka-server-1.0-SNAPSHOT.jar /app/app.jar

WORKDIR "/app"

EXPOSE 8761

CMD java -jar /app/app.jar