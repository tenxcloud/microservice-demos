FROM 172.22.50.227/system_containers/oraclejdk:8u231

ENV PROFILE=test \
    JVM="-Xms256m -Xmx1g -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Djava.security.egd=file:/dev/./urandom"


ADD *.jar /app/app.jar

WORKDIR "/app"

EXPOSE 8080

CMD java -jar /app/app.jar
