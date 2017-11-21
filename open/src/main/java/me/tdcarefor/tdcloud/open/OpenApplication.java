package me.tdcarefor.tdcloud.open;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * 类: Application<br/>
 * 描述:  <br>
 * 作者: tzw <br>
 * 时间: 2016 16/5/17 下午3:26 <br>
 */


@SpringBootApplication(exclude={MongoDataAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})
@EnableEurekaClient
@EnableHystrix
@EnableHystrixDashboard
@EnableAspectJAutoProxy
//@EnableResourceServer
@ImportResource({"classpath*:META-INF/spring/*.xml",
        "classpath:*.xml"
})
@ComponentScan(basePackages = {"me.tdcarefor.tdcloud"})
@EnableFeignClients(basePackages = "me.tdcarefor.tdcloud")
public class OpenApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(OpenApplication.class).web(true).run(args);
    }

}