# 开发指南
为了方便模块化开发，将项目用maven的module功能进行组织。
## 各模块说明
* microservices 模块

基于Axon的微服务框架，包含了框架基础功能的说明。详细说明可参考 [这里](https://coding.net/t/tdcare/p/tdnis/git/blob/master/microservices/README.md)

* doctor-advice 模块

医嘱管理 微服务，此模块是单独部署的最小功能单元。一般以DDD中的一个 **边界上下文** 来作为微服务的边界。这是一个spring-mvc项目，会打包为一个war项目。其它微服务的开发可以参考此模块进行。

* core-api 模块

微服务之间，依据业务需求，有时会通过API-GateWay、消息中间件等进行协同工作。这时，协作的微服务之间会相互依赖，为解决循环依赖的问题。需要抽象出一个中间模块，此模块包含微服务的公共部份。一般包含DTO、命令、领域事件等内容。同时将一些各模块都会用到基本功能也可以放入到此模块中。

