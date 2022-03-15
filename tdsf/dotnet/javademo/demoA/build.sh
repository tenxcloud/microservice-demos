#打包
mvn clean package -DskipTests
#构建
docker build -t javademoa:v1 .