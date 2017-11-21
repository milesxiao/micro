package me.tdcarefor.tdcloud.uaa.config;

import me.tdcarefor.tdcloud.uaa.security.MyLoginUrlAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 类: LoginConfiguration<br/>
 * 描述:  <br>
 * 作者: tzw <br>
 * 时间: 2016 16/6/14 下午11:33 <br>
 */
@Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)
@Configuration
public class LoginConfig extends WebSecurityConfigurerAdapter {

  @Value("${proxy.ip}")
  private String proxyIp;

  @Autowired
  @Qualifier("customUserDetailsService")
  private UserDetailsService userDetailsService;

  @Autowired
  public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers().permitAll()
        .and()
        .formLogin()
        .loginPage("/login")
        .usernameParameter("username")
        .passwordParameter("password")
        .permitAll().failureUrl("http://" + proxyIp + "/?error")
        .defaultSuccessUrl("http://" + proxyIp + "/logined", true)
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(new MyLoginUrlAuthenticationEntryPoint("/login"))
        .and()
        .csrf().disable()
        .logout().permitAll().logoutSuccessUrl("http://" + proxyIp)
        .and()
        .authorizeRequests().anyRequest().authenticated();
  }


  @Bean(name = "passwordEncoder")
  public PasswordEncoder passwordencoder() {
    return new BCryptPasswordEncoder();
  }

}
