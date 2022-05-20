# 本地环境运行
## 设置ENV
```
PROFILE=test;NACOS_ADDR=localhost:8848;NACOS_NAMESPACE=tmf;NACOS_GROUP=dev;NACOS_CONFIG_ENABLED=false;PAAS_NS=tmf;SERVICE_NAME=opentracing-demo-c;PAAS_TENANT=tmf;MYSQL_HOST=localhost;MYSQL_PORT=3306;REDIS_HOST=localhost;REDIS_PORT=6379;REDIS_PASSWORD=;RABBITMQ_HOST=localhost;RABBITMQ_PORT=5672;RABBITMQ_USERNAME=guest;RABBITMQ_PASSWORD=guest;MYSQL_PASSWORD=123456;MYSQL_USERNAME=root;SW_AGENT_NAME=opentracing-demo-c;PAAS_VERSION=v1;INSTANCE_NAME=localhost:18083;CLUSTER_ID=cid-123456;SW_AGENT_COLLECTOR_BACKEND_SERVICES=192.168.5.35:11800;DB_NAME=tmf;MESH_SWITCHER_MESH=false;DEPLOY_TYPE=container
```
## 设置java的启动参数
```
-javaagent:/Users/zhangdalei/git/gitlab.tenxcloud.com/zhangdalei/skywalking/skywalking-agent/skywalking-agent.jar -Dserver.port=18083 
```
## 部署wiki
http://wiki.tenxcloud.com/pages/viewpage.action?pageId=21266882

## tmf自动注入的环境变量，如下，可以不在dockerfile中申明
```
CLUSTER_ID
PAAS_TENANT
PAAS_NS
NACOS_ADDR
NACOS_NAMESPACE
SERVICE_NAME(SW_AGENT_NAME 最新的agent修改了环境变量名称，如果用老的agent用该环境变量)
PAAS_VERSION
SW_AGENT_COLLECTOR_BACKEND_SERVICES
INSTANCE_NAME
```



