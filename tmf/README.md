#### demo里面有不同类型的项目，包括dubbo项目以及SpringCloud项目，可以用来测试微服务平台的相关功能，可以参考demo编写代码。

### dubbo-demo
dubbo-demo模块提供了一个dubbo应用接入微服务平台的样例，将dubbo-demo部署到微服务平台，就可以使用通过微服务平台对dubbo服务进行服务治理。

### nacos-demo
nacos-demo模块提供了dubbo和springcloud两个版本的生产者服务和消费者服务的样例，将服务部署到微服务平台，就可以使用服务间调用、服务治理等功能。

### opentracing-demo
opentracing-demo模块包含kafka,rabbitmq,redis,rocketmq,mysql等中间件，主要用来测试服务与中间件之间的链路，即验证中间件链路追踪功能。

### opentracing-demo-without-registry
和opentracing-demo功能相同，但不依赖注册中心。

### tmf-demo-simple
tmf-demo-simple模块提供了SpringCloud应用接入微服务平台的样例，只需要按照接入指南引入相关功能SDK，然后部署到微服务平台，就可以使用相关功能。比如熔断降级，跨集群调用，主备服务切换等功能。
