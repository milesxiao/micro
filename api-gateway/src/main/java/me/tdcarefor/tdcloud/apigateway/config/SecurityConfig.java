package me.tdcarefor.tdcloud.apigateway.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;

/**
 * @author Thibaud Leprêtre
 */
@Configuration
@EnableOAuth2Sso
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  @Primary
  public OAuth2ClientContextFilter dynamicOauth2ClientContextFilter() {
    return new DynamicOauth2ClientContextFilter();
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/uaa/**", "/open/**", "/login").permitAll()
        .anyRequest().authenticated()
        .and().csrf().disable()
        .logout().permitAll()
        .logoutSuccessUrl("/");
//        http.authorizeRequests().antMatchers("/uaa/**", "/login", "/wxlogin","/").permitAll()
//            .anyRequest().authenticated()
//            .and().csrf().disable()
//            .logout().permitAll()
//            .logoutSuccessUrl("https://"+ proxyIp + "/");
  }
}
