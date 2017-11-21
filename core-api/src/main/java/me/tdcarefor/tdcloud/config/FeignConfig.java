package me.tdcarefor.tdcloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

/**
 * 类: FeignConfig<br/>
 * 描述: 调用其它微服务时,带上oAuth2的accessToken <br>
 * 作者: tzw <br>
 * 时间: 2016 16/8/31 上午3:36 <br>
 */
@Configuration
public class FeignConfig {
  @Autowired
  private OAuth2ClientContext oAuth2ClientContext;
  @Autowired
  private OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails;

  //    @Bean
//    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
//        return new BasicAuthRequestInterceptor("user", "password");
//    }
  @Bean
  public OAuth2FeignRequestInterceptor oAuth2FeignRequestInterceptor() {
    return new OAuth2FeignRequestInterceptor(oAuth2ClientContext, oAuth2ProtectedResourceDetails);
  }
}
