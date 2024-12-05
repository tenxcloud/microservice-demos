FROM airdock/oraclejdk:1.8


ADD netty-client-1.0-SNAPSHOT.jar /app/app.jar

WORKDIR "/app"

EXPOSE 8081

CMD java -jar /app/app.jar