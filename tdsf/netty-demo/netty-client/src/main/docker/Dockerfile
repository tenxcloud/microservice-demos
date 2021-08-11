FROM 192.168.1.52/system_containers/oraclejdk:8u231

MAINTAINER sparkxiong shi.xiong@tenxcloud.com

ADD netty-client-1.0-SNAPSHOT.jar /app/app.jar

WORKDIR "/app"

EXPOSE 8081

CMD java -jar /app/app.jar