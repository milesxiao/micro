package me.tdcarefor;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * 类: Application<br/>
 * 描述:  <br>
 * 作者: tzw <br>
 * 时间: 2016 16/5/17 下午3:26 <br>
 */


@SpringBootApplication
@EnableTurbine
@EnableEurekaServer
public class ServiceRegistryApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ServiceRegistryApplication.class).web(true).run(args);
    }

}