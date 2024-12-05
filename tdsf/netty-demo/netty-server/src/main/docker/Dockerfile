FROM airdock/oraclejdk:1.8


ADD netty-server-1.0-SNAPSHOT.jar /app/app.jar

WORKDIR "/app"

EXPOSE 8082
EXPOSE 8090

CMD java -jar /app/app.jar