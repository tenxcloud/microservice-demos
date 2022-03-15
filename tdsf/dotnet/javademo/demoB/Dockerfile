FROM openjdk:8-alpine as builde
COPY target/*.jar app.jar

FROM openjdk:8-alpine
ENV TZ=Asia/Shanghai
RUN apk add --no-cache tini
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY --from=builde app.jar ./
EXPOSE 80
ENV JAVA_OPTS="-server -Xmx512m -Xms512m -Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom -Duser.timezone=Asia/Shanghai "
ENTRYPOINT ["tini", "sh", "-c", "set -e && java $JAVA_OPTS -jar app.jar"]