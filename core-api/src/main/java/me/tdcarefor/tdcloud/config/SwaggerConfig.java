package me.tdcarefor.tdcloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by tzw on 16/4/26.
 */
//Swagger配置文件
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${api.version}")
    private String version;
    @Bean
    public Docket customDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("me.tdcarefor"))
                .build()
                ;

    }
    private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo(
                "提灯智慧护理接口平台",
				"API说明",
                version,
				"http://tdcarefor.me",
                new Contact("", "", ""),
                "服务协议",
                "http://tdcarefor.me"
                );
		return apiInfo;
	}
}
