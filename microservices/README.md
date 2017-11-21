# 基于Axon 框架的微服务开发指南
## 1.DDD 领域驱动
领域驱动 设计 请参考 以下文章:
* [DDD领域驱动设计基本理论知识总结](http://www.cnblogs.com/netfocus/archive/2011/10/10/2204949.html)
* [DDD CQRS架构和传统架构的优缺点比较](http://www.cnblogs.com/netfocus/p/5184182.html)
* [快速理解聚合根、实体、值对象的区别和联系](http://www.cnblogs.com/netfocus/p/5145345.html)

## 2.CQRS 原理
* [IDDD 实现领域驱动设计－CQRS（命令查询职责分离）和EDA（事件驱动架构）](http://www.cnblogs.com/xishuai/p/iddd-cqrs-and-eda.html)

## 3.微服务
* [微服务架构的优势与不足](http://blog.daocloud.io/microservices-1/)


## 4.Docker
* [ Docker 与微服务](http://7xi8kv.com5.z0.glb.qiniucdn.com/%E8%B0%AD%E5%AE%97%E5%A8%81%20-%20Docker%20%E4%B8%8E%E5%BE%AE%E6%9C%8D%E5%8A%A1.pdf)

## 5.代码结构说明
以领域建模中的一个“边界上下文”，作为一个开发单元，也就是作为一个微服务。比如临床护理中的，“患者24小时工作项”，“医嘱分解”，“护理计划”，“记帐处理”，“护理文书”，“工作项提醒”等等。一个开发、部署单元（微服务）利用Maven来作依赖和构建管理，用DaoCloud提供的CI及打包工具来进行持续集成，生成可供生成环境使用的docker image 包。如何部署微服务请参考[部署项目的说明文档](https://coding.net/u/tdcarefor/p/nis-deploy/git/blob/master/README.md)。
### [pom.xml](https://coding.net/t/tdcare/p/tdnis/git/blob/master/microservices/pom.xml) 文件说明
项目依赖的库主要有以下几个：
* spring 相关框架: spring-mvc spring-jdbc spring-data-mongodb spring-data-jpa spring-beans spring-tx spring-context-support spring-aop  
* 数据库相关: mongo-java-driver hibernate-jpa-2.1-api hibernate-entitymanager hsqldb mysql-connector-java com.alibaba.druid 实现对于mongoDB mysql hsqldb数据库的访问，druid用于mysql数据库连接池，hibernate-jpa hibernate-entitymanager实现ORM框架。
* 消息队列: org.springframework.amqp.spring-rabbit基于spring的amap协议实现。用于访问RabbitMQ队列。
* 分布式事务: com.atomikos.transactions-jta 用于实现Axon框架的分布式事务控制，特别是Axon框架通过rabbitMQ队列作为EventBus实现时，一定要有分布式事务的支持。
* AOP相关：aspectjrt aspectjweaver 实现AOP功能。
* Axon相关框架：axon-core axon-mongo axon-amqp 参考官方 [文档](http://www.axonframework.org/docs/2.4/single.html)，但此官方文档写的不是很清楚，很多的功能需要结合源代码才能明白，源代码请访问 [这里](https://github.com/AxonFramework/AxonFramework)
* Swagger相关：io.springfox.springfox-swagger2 jackson-databind 这两个依赖实现基于Swagger的API文档平台。
* 其它依赖库：如测试相关、web相关等，具体请查看pom.xml 文件中dependencies 节中的内容。


### [daocloud.yml](https://coding.net/u/tdcarefor/p/nis/git/blob/master/daocloud.yml) [Dockerfile](https://coding.net/u/tdcarefor/p/nis/git/blob/master/Dockerfile) [Dockerfile.sec](https://coding.net/u/tdcarefor/p/nis/git/blob/master/Dockerfile.sec) 文件说明
这三个文件定义了基于Docker的持续构建平台，实现了提交代码-->提交测试-->单元测试-->自动构建的全流程自动化，详细说请参考 [这里](http://docs.daocloud.io/ci-image-build/daocloud-yml-2-0-preview)



### [dispatcher-servlet.xml](https://coding.net/u/tdcarefor/p/nis/git/blob/master/src/main/webapp/WEB-INF/dispatcher-servlet.xml) 文件说明
spring框架的初始配置，功能说请参考 [这里](http://jinnianshilongnian.iteye.com/blog/1602617)


### resources/[config.properties](https://coding.net/t/tdcare/p/tdnis/git/blob/dev/microservices/src/main/resources/config.properties) 文件说明
配置参数说明，此处的配置参数，可以通过操作系统的环境变量进行设置。
* linux 通过export 命令设置。例如：export MONGO.HOST=114.55.73.49 删除unset MONGO.HOST
* java  通过-D 参数进行设置。例如：java -Dmongo.host=114.55.73.49
* docker通过environment 进行设置。例如：在docker-compose 中
```   
 environment：
   mongo.host=114.55.73.49
```

### resources/META-INF/[persistence-infrastructure-context.xml](https://coding.net/t/tdcare/p/tdnis/git/blob/dev/microservices/src/main/resources/META-INF/spring/persistence-infrastructure-context.xml) 文件说明
JPA 规范指定的[配置文件](http://blog.csdn.net/philosophyatmath/article/details/37504067)。
 [JPA 不在 persistence.xml 文件中配置每个Entity实体类的2种解决办法](http://www.cnblogs.com/taven/archive/2013/10/04/3351841.html)。
 
### resources/META-INF/spring/[persistence-infrastructure-context.xml](https://coding.net/t/tdcare/p/tdnis/git/blob/dev/microservices/src/main/resources/META-INF/spring/persistence-infrastructure-context.xml) 文件说明
 持久化相关配置，配置数据库、消息队列等外部资源。详细参数说明请见文件中的注释。

### resources/META-INF/spring/[cqrs-infrastructure-context.xml](https://coding.net/t/tdcare/p/tdnis/git/blob/dev/microservices/src/main/resources/META-INF/spring/cqrs-infrastructure-context.xml) 文件说明
基于Axon框架的CQRS配置：commandBus、eventBus 等配置。

* **commandBus 的实现方式**

commandBus 可分为两种实现：一种是 [同步](http://www.axonframework.org/docs/2.4/single.html#d5e335)，二种是 [异步](http://www.axonframework.org/docs/2.4/single.html#d5e349)。目前使用简单的同步方案。

``` xml
<!-- commandBus 配置，指明了事务管理器txManager2,也可不设置，请参考官方文档，此配置使用的是简单同步commandBus
可使用异步方案，方法请参考官方文档。-->
    <axon:command-bus id="commandBus" transaction-manager="txManager2" >
        <axon:dispatchInterceptors>
            <bean class="org.axonframework.commandhandling.interceptors.BeanValidationInterceptor"/>
        </axon:dispatchInterceptors>
    </axon:command-bus>
```
    
* **commandGateway 配置**

``` xml
 <bean id="commandGateway" class="org.axonframework.commandhandling.gateway.CommandGatewayFactoryBean">
        <property name="commandBus" ref="commandBus"/>
    </bean>
```
commandGateway 用于所有命令的发送接口，详细参考 [官方文档](http://www.axonframework.org/docs/2.4/single.html#d5e224),使用方法可 [参考代码](https://coding.net/u/tdcarefor/p/nis/git/blob/master/src/main/java/me/tdcarefor/nis/server/web/rest/JsonTestController.java) 中的 createUser 方法。
    
* **eventBus 的实现方式**

``` xml
<axon:event-bus id="eventBus" terminal="terminal2"/>
```
没有指定terminal参数时会用默认的SimpleEventBusTerminal 来实现各个领域事件消费者在一个线程中同步调用，
使用这个方式时当其中一个消费者抛异常时，后边的消费者将不再执行。可以将terminal指定为下边定义的terminal2:

``` xml
<axon-amqp:terminal id="terminal2"
                           connection-factory="rabbitMQConnection"
                           transactional="true"
                           exchange-name="AxonEventBusExchange">
           <axon-amqp:default-configuration acknowledge="auto"
                                            transaction-size="1"
                                            transaction-manager="txManager2"/>
</axon-amqp:terminal>
```
指定为此对象后，领域事件将会发送到terminal2所指定的RabbitMQ中间件 一个名为AxonEventBusExchange的Exchanges中。领域事件的消费者需要通过配置 axon:cluster 来声明从哪里获得领域事件。
默认的配置如下，default="true" 表示，当消费者没有声明时，将使用此配置。
``` xml
  <!-- 定义领域事件分发处理器，只有当eventBus 指定了terminal 参数时才起作用。
   一个系统中可以定义多个分发处理器，<axon:selectors> 来指定分发器所对应的消费者（EventHandler），
   一般将此文件与 <axon:event-sourcing-repository> 定义的聚合根放在同一个文件中，
   方便查看。
   -->
    <axon:cluster id="myDefaultClusterEP" default="true">
        <axon:meta-data>
            <entry key="AMQP.Config">
                <axon-amqp:configuration queue-name="tdnisq1"/>
            </entry>
        </axon:meta-data>
        <!--<bean id="asynCluster" class="org.axonframework.eventhandling.async.AsynchronousCluster"  >-->
        <!--<constructor-arg name="identifier"  value="myDefaultCluster1"/>-->
        <!--<constructor-arg name="executor" ref="taskExecutor"/>-->
        <!--&lt;!&ndash;<constructor-arg name="transactionManager" ref="txManager2"/>&ndash;&gt;-->
        <!--<constructor-arg name="sequencingPolicy" ref="policyId"/>-->
        <!--</bean>-->
    </axon:cluster>
```
除上述默认配置外，还可以声明其它的cluster，例如：
       
``` xml
<axon:cluster id="usersEventProcessor" >
        <axon:meta-data>
            <entry key="AMQP.Config">
                <axon-amqp:configuration  queue-name="usersQueue"  />
            </entry>
        </axon:meta-data>
       <!-- selectors 表示哪些EventListener将用这个EventProcessor
      进行领域事件 分发，也就是usersQueue中的领域事件中通知到的消费者-->
        <axon:selectors >
            <axon:package  prefix="me.tdcarefor.nis.server.query.users"/>
            <axon:package prefix="me.tdcarefor.nis.server.domain.users" />
        </axon:selectors>

    </axon:cluster>
```
上述配置文件，表示一个名为usersEventProcessor 的cluster（Axon 3 以后将改名为Event Processor 事件处理器）这个事件处理器将从通过 axon-amqp:terminal id="terminal2" 所配置RabbitMQ中一叫usersQueue的队列中得到通知，也就是说usersQueue中的领域事件，将送达到 axon:selectors 所指定的消费者（EvetnListener）。另外，同一个事件处理器下的多个消费者可以异步多线程并行执行，配置方法见默认配置中，注释了的，asynCluster 部分 。原理及详细配置方法请参考 [官方文档](http://www.axonframework.org/docs/2.4/single.html#d5e1704)。其基本原理是用 [Disruptor](http://lmax-exchange.github.io/disruptor/) 作为多线程调度器，实现高性能、无锁的多线程资源共享。

* **分布式事务管理器**

``` xml
<!-- 分布式事务管理器，将领域事件保存到eventStore 发送到 消息队列 等需要有分布式事务支持，
 用于保证事务的完整性。此处用流行的atomikos做为分布式事务管理器。
 axon-amqp:default-configuration
 axon:command-bus
 等需要事务管理的地方可用此配置。
 -->
    <bean id="txManager2" class="org.springframework.transaction.jta.JtaTransactionManager">
        <property name="transactionManager">
            <bean class="com.atomikos.icatch.jta.UserTransactionManager" init-method="init" destroy-method="close">
                <property name="forceShutdown" value="true"/>
            </bean>
        </property>
        <property name="userTransaction">
            <bean class="com.atomikos.icatch.jta.UserTransactionImp"/>
        </property>
    </bean>
```

* **持久化管理器**

Axon 框架需要对聚合根、Saga管理器进行持久处理，聚合根的持久化用eventStore，Saga用saga-repository来定义，Axon支持多种持久化方案，如:文件、关系型数据库、Nosql数据库等。

``` xml
<!-- 不同环境下的eventStore saga持久化支持，聚合根 saga 持久化可支持多种方式：
file
jdbc
mongodb
测试环境下可以用hsqldb file, 正式环境可用mongodb及mysql
-->
    <beans profile="hsqldb">

        <axon:jdbc-event-store id="eventStore" data-source="hsdataSource" sql-schema="eventSqlSchema"/>

        <axon:jdbc-saga-repository id="sagaRepository" data-source="hsdataSource" sql-schema="sagaSqlSchema"/>

        <bean id="eventSqlSchema" class="org.axonframework.eventstore.jdbc.GenericEventSqlSchema"/>
        <bean id="sagaSqlSchema" class="org.axonframework.saga.repository.jdbc.HsqlSagaSqlSchema"/>
    </beans>
    <beans profile="mysql">

        <axon:jdbc-event-store id="eventStore" data-source="mysqldataSource" sql-schema="eventSqlSchema"/>

        <axon:jdbc-saga-repository id="sagaRepository" data-source="mysqldataSource" sql-schema="sagaSqlSchema"/>

        <bean id="eventSqlSchema" class="org.axonframework.eventstore.jdbc.GenericEventSqlSchema"/>
        <bean id="sagaSqlSchema" class="me.tdcarefor.axon.saga.repository.jdbc.MysqlSagaSqlSchema"/>
    </beans>
    <beans profile="mongodb mongodbAndMysql">
        <bean id="eventStore" class="org.axonframework.eventstore.mongo.MongoEventStore">
            <constructor-arg ref="mongoTemplate"/>
        </bean>

        <bean id="sagaRepository" class="org.axonframework.saga.repository.mongo.MongoSagaRepository">
            <constructor-arg ref="mongoSagaTemplate"/>
            <property name="resourceInjector">
                <bean class="org.axonframework.saga.spring.SpringResourceInjector"/>
            </property>
        </bean>
    </beans>

```
上述配置文件，应用profile定义了不同环境之下的持久化方案。

* **基它配置**

基它的如缓存、多任务执行器、快照等，请参考官方文档及代码。



### resources/META-INF/spring/[configuration-context.xml](https://coding.net/t/tdcare/p/tdnis/git/blob/dev/microservices/src/main/resources/META-INF/spring/configuration-context.xml) 文件说明
配置config.properties 文件，及javaconfig，配置支持。

### resources/META-INF/spring/[persistence-infrastructure-context.xml](https://coding.net/t/tdcare/p/tdnis/git/blob/dev/microservices/src/main/resources/META-INF/spring/persistence-infrastructure-context.xml) 文件说明
各种外部资源配置，如数据库，消息中间件等。用profile 来指定不同环境下的外部资源定义。


这些文件指定了系统中**聚合根**的对象的配置情况。下边以[users-context.xml](https://coding.net/t/tdcare/p/tdnis/git/blob/dev/user/src/main/resources/user-context.xml) 为例说明如何配置：

``` xml

 <?xml version="1.0" encoding="UTF-8"?>
<!--
  ~ Copyright (c) 2016.  tdcarefor.me. All rights reserved.
  ~
  -->
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:axon="http://www.axonframework.org/schema/core" xmlns:axon-amqp="http://www.axonframework.org/schema/amqp"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.axonframework.org/schema/core http://www.axonframework.org/schema/axon-core.xsd http://www.axonframework.org/schema/amqp http://www.axonframework.org/schema/axon-amqp.xsd">
    <!-- 用户仓库-->
    <axon:event-sourcing-repository id="userRepository"
                                    aggregate-type="me.tdcarefor.tdcloud.user.domain.UserAggregate"
                                    cache-ref="ehcache"
                                    event-bus="eventBus"
                                    event-store="eventStore">
        <axon:snapshotter-trigger event-count-threshold="50" snapshotter-ref="snapshotter"/>
    </axon:event-sourcing-repository>
    <!-- 科室仓库-->
    <axon:event-sourcing-repository id="departmentRepository"
                                    aggregate-type="me.tdcarefor.tdcloud.user.domain.DepartmentAggregate"
                                    cache-ref="ehcache"
                                    event-bus="eventBus"
                                    event-store="eventStore">
        <axon:snapshotter-trigger event-count-threshold="50" snapshotter-ref="snapshotter"/>
    </axon:event-sourcing-repository>
        <axon:cluster id="userEventProcessor"  >
            <axon:meta-data>
                <entry key="AMQP.Config">
                    <axon-amqp:configuration  queue-name="userQueue"   />
                </entry>
            </axon:meta-data>
            <!-- selectors 表示哪些EventListener将用这个EventProcessor
            进行领域事件 分发，也就是usersQueue中的领域事件中通知到的消费者-->
            <axon:selectors >
                <axon:package prefix="me.tdcarefor.tdcloud.user"/>
            </axon:selectors>
        </axon:cluster>
        <!--<axon:saga-manager id="sagaManager" saga-repository="sagaRepository" event-bus="eventBus">-->
            <!--<axon:types>-->
                <!--me.tdcarefor.tdcloud.doctoradvice.domain.saga.IVTSaga,-->
                <!--me.tdcarefor.tdcloud.doctoradvice.domain.saga.CycleSaga-->
            <!--</axon:types>-->
        <!--</axon:saga-manager>-->
</beans>

```

此 axon:event-sourcing-repository id="userRepository" 定义了聚合根 me.tdcarefor.tdcloud.user.domain.UserAggregate 为event-sourcing 聚合根 ，并且指定了，eventBus evetntStore cache 等参数。

### 基于Swagger 的API文档平台
[Swagger](http://swagger.io/)  是一个流行的支持 [Open API Initiative (OAI)](https://openapis.org/) 接口文档规范的文档平台。它主要包括以下几个部分：

* [SWAGGER UI](http://petstore.swagger.io/) 

用于API文档的查看、测试。
* [SWAGGER EDITOR](http://editor.swagger.io/)

API文档的在线编辑器，可在线使用，也可以单独部署。
* [SDK GENERATORS](http://editor.swagger.io/)

根据API文档生成各种语言、框架的程序文件。
* [Springfox](http://springfox.github.io/springfox/)

Springfox 是一个在spring-mvc中通过注解的方式，自动生成规范化（OAI）文档的组件。Springfox 主要提供了以下几个注解：
* @Api 用于标注类，一般标在Controller 类上。
* @ApiOperation 用于标注Controller 类中的方法。
* @ApiResponses 一般与@ApiOperation注解在一起，用于指明方法的返回值。
* @ApiModel 用于标注类，一般标在接口中的DTO类上。
* @ApiModelProperty 用于标注属性，一般标在接口返回DTO类的属性上。

[SwaggerConfig](https://coding.net/u/tdcarefor/p/nis/git/blob/master/src/main/java/me/tdcarefor/swagger/SwaggerConfig.java) 文件以javaConfig的方式，定义了Springfox 输出文档的参数。
[config.properties](https://coding.net/u/tdcarefor/p/nis/git/blob/master/src/main/resources/config.properties) 文件中配置了Springfox 输出host，主要用于解决存在代理服务器时Springfox 输出文档中baseUrl 主机名不正确。
**在开发中，通过Springfox提供的注解，对REST接口、接口中的DTO进行说明，就可以自动生成接口文档，方便前端开发人员进行调用与调试。**



### 源代码包结构说明
核心的包有以下几个：
* **me.tdcarefor.nis.server.domain**

用于组织核心的业务逻辑，一个子包为一个聚合根或相互关系很紧密几个聚合根。主要有聚合根、聚合根响应的命令、命令处理器、领域事件、领域事件消费者等。例如User包中：

聚合根：[User.java](https://coding.net/u/tdcarefor/p/nis/git/blob/master/src/main/java/me/tdcarefor/nis/server/domain/users/User.java)

命令定义：[CreateUserCommand.java](https://coding.net/u/tdcarefor/p/nis/git/blob/master/src/main/java/me/tdcarefor/nis/server/domain/users/CreateUserCommand.java) [CreateUserCommand.java](https://coding.net/u/tdcarefor/p/nis/git/blob/master/src/main/java/me/tdcarefor/nis/server/domain/users/CreateUserCommand.java)

领域事件定义：[UserAuthenticatedEvent.java](https://coding.net/u/tdcarefor/p/nis/git/blob/master/src/main/java/me/tdcarefor/nis/server/domain/users/UserAuthenticatedEvent.java) [UserCreatedEvent.java](https://coding.net/u/tdcarefor/p/nis/git/blob/master/src/main/java/me/tdcarefor/nis/server/domain/users/UserCreatedEvent.java)

命令处理器：[UserCommandHandler.java](https://coding.net/u/tdcarefor/p/nis/git/blob/master/src/main/java/me/tdcarefor/nis/server/domain/users/command/UserCommandHandler.java)

领域事件消费者：聚合根[User](https://coding.net/u/tdcarefor/p/nis/git/blob/master/src/main/java/me/tdcarefor/nis/server/domain/users/User.java) 中用 @EventHandler 标注的方法，就是一个消费者，其它的消费者有可能在其它的模块中（如query 中的 [UserListener.java](https://coding.net/u/tdcarefor/p/nis/git/blob/master/src/main/java/me/tdcarefor/nis/server/query/users/UserListener.java),也是User领域事件的消费者之一），或其它的微服务中。
其它实体或者值对象的定义：[UserId.java](https://coding.net/u/tdcarefor/p/nis/git/blob/master/src/main/java/me/tdcarefor/nis/server/domain/users/UserId.java) 等文件就属于此类对象。

* **me.tdcarefor.nis.server.service**

这里用于实现领域服务。


* **me.tdcarefor.nis.server.query**

这里定义了CQRS中Query的部份，所有与查询相关的内容都在这里实现。开发流程如下：

首先 根据业务需求定义好前端、数据库需要的DTO对象，如[UserEntry.java](https://coding.net/u/tdcarefor/p/nis/git/blob/master/src/main/java/me/tdcarefor/nis/server/query/users/UserEntry.java) 并按JPA，Swagger的要求完成此对象的开发。

第二步 根据JPA的要求定义查询仓库 如 [UserQueryRepository.java](https://coding.net/u/tdcarefor/p/nis/git/blob/master/src/main/java/me/tdcarefor/nis/server/query/users/repositories/UserQueryRepository.java)。如何实现，可参考网上相关内容。

第三步 开发消费者如[UserListener.java](https://coding.net/u/tdcarefor/p/nis/git/blob/master/src/main/java/me/tdcarefor/nis/server/query/users/UserListener.java)，依据领域设计，设计此消费者需要消费的领域事件。在消费者通过调用第二步中开发的接口完成查询库的更新操作。

以上三步就是典型的Query模块开发过程。

* **me.tdcarefor.nis.server.web**

此包中主要实现spring-mvc 中Controller 功能。接受前端传递过来的参数并且进行验证、返回业务处理的结果，此模块需要注入Query模块中的仓库，以及与领域对象打交道的commandGateway对象。在开发中，需要标注好各种注解，如Swagger的注解主要应用在此模块中。请参考[JsonTestController.java](https://coding.net/u/tdcarefor/p/nis/git/blob/master/src/main/java/me/tdcarefor/nis/server/web/rest/JsonTestController.java)

### Saga 技术

Saga 是指那些企业业务流程，需要跨应用、跨企业来完成某个事务，甚至在事务流程中还需要有手工操作的参与，这类事务的完成时间可能以分计，以小时计，甚至可能以天计。关于Saga的详细描述请参考[这里](http://www.cnblogs.com/netfocus/p/3149156.html)。Axon 框架实现了对Saga的支持，详细功能见[官方文档](http://www.axonframework.org/docs/2.4/single.html#sagas)。[BuyTradeManagerSaga.java](https://coding.net/u/tdcarefor/p/nis/git/blob/master/src/main/java/me/tdcarefor/nis/server/domain/orders/saga/BuyTradeManagerSaga.java) 实现了购买股票时的长事务流管理。  [SellTradeManagerSaga.java](https://coding.net/u/tdcarefor/p/nis/git/blob/master/src/main/java/me/tdcarefor/nis/server/domain/orders/saga/SellTradeManagerSaga.java) 实现了出售股票时的长事务流管理。

使用Saga的步骤：
* 继承 AbstractAnnotatedSaga抽象类，实现抽象方法。
* 使用@StartSaga 和 @SagaEventHandler 标注 handle（）方法，同时@SagaEventHandler 需要指定associationProperty 属性，用来指示 此长事务流的标识属性。当有这两个注解所标注的领域事件发生时，将启动一个Saga。
* @SagaEventHandler 标注 其它的领域事件消费者（其它的handle ()方法），表示有其它领域事件发生时，Saga将如何处理。
* @EndSaga 和 @SagaEventHandler 标注领域事件消费者 表示当这个领域事件发生时，将结束这个Saga。

**所有的@SagaEventHandler 都需要指定associationProperty属性，这个标记用于找到正确的Saga。**

Saga 的超时管理：

有时一个Saga启动后，再也没有收到@EndSaga所标注的领域事件，或者业务要求在多长时间后必须完成某项业务，此时可通一个定时器，来定时发送某个事件。以便Saga能正确处理业务，详细见[官方文档](http://www.axonframework.org/docs/2.4/single.html#d5e2006)。





