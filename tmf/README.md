> 方便测试和实施运维同事进行 demo 相关镜像的部署，在这个仓库对相关 demo 代码进行维护，并以分支名表示产品相应的版本
如 tmf-5.2.0 表示 tmf 的 v5.2.0


* tmf-demo-simple 服务治理相关的可以使用此 demo， 此 demo 不需要部署其它的中间件服务，如 服务详情下 sentinel 相关的； 服务配置，路由，限流等；也可以产生多层调用关系，但是调用里面不包含中间件等信息；
* opentracing-demo 模块中会引入中间件， mysql， redis， rabbitmq 