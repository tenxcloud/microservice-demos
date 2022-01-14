FROM 172.22.50.227/system_containers/oraclejdk:8u231

MAINTAINER sparkxiong shi.xiong@tenxcloud.com

ADD netty-server-1.0-SNAPSHOT.jar /app/app.jar

WORKDIR "/app"

EXPOSE 8082
EXPOSE 8090

CMD java -jar /app/app.jar