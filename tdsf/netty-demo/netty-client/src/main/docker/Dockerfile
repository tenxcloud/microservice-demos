FROM 172.22.50.227/system_containers/oraclejdk:8u231


ADD netty-client-1.0-SNAPSHOT.jar /app/app.jar

WORKDIR "/app"

EXPOSE 8081

CMD java -jar /app/app.jar