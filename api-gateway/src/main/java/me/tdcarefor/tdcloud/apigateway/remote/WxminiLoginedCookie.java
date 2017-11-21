package me.tdcarefor.tdcloud.apigateway.remote;

import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
public class WxminiLoginedCookie {

  private Logger logger = Logger.getLogger(WxminiLoginedCookie.class);

  @Value("${restTemplate.readTimeout}")
  private int readTimeout;

  @Value("${restTemplate.connectTimeout}")
  private int connectTimeout;

  @Value("${restTemplate.login.username}")
  private String userName;

  @Value("${restTemplate.login.password}")
  private String password;

  @Value("${restTemplate.tdcloud.serviceUri}")
  private String restServiceUri;

  public WxminiLoginedCookie() {

  }

  public BasicCookieStore login(String username, String password) {

    MyRestTemplate myRestTemplate = new MyRestTemplate(readTimeout, connectTimeout);

    /** 模拟浏览器用户登录认证开始... **/
    String url = restServiceUri + "/uaa/login";
    HttpHeaders uaaLoginHeaders = new HttpHeaders();
    uaaLoginHeaders.set("Accept-Charset", "utf-8");
    uaaLoginHeaders.set("Content-type", "application/x-www-form-urlencoded");// 设置编码

    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("username", username);
    map.add("password", password);

    HttpEntity<MultiValueMap<String, String>> uaaLoginEntity = new HttpEntity<>(map,
        uaaLoginHeaders);

    ResponseEntity<String> uaaLoginResponse = myRestTemplate
        .exchange(url, HttpMethod.POST, uaaLoginEntity, String.class);
    if (uaaLoginResponse.getHeaders().getLocation() != null) {
      String resLocation = uaaLoginResponse.getHeaders().getLocation().toString();
      if (resLocation.contains("?error") || resLocation.contains("uaa/login")) {
        logger.error("认证失败！用户名或密码错误");
        return null;
      }
    } else if (StringUtils.isNotEmpty(uaaLoginResponse.getBody()) && uaaLoginResponse.getBody()
        .contains("用户登录")) {
      logger.error("认证失败！用户名或密码错误");
      return null;
    }
    /** 模拟浏览器用户登录认证结束. **/

    /** 模拟浏览器用户授权认证开始... **/
    String uaaOauthLocation = restServiceUri + "/uaa/oauth/authorize";
    HttpHeaders uaaOauthHeaders = new HttpHeaders();
    uaaOauthHeaders.set("Accept-Charset", "utf-8");
    uaaOauthHeaders.set("Content-type", "application/x-www-form-urlencoded");// 设置编码

    map = new LinkedMultiValueMap<>();
    map.add("user_oauth_approval", "true");
    HttpEntity uaaOauthEntity = new HttpEntity(map, uaaOauthHeaders);

    ResponseEntity<String> uaaOauthResponse = myRestTemplate
        .exchange(uaaOauthLocation, HttpMethod.POST, uaaOauthEntity, String.class);
    if (uaaOauthResponse.getHeaders().getLocation() != null) {
      String resLocation = uaaOauthResponse.getHeaders().getLocation().toString();
      if (resLocation.contains("?error") || resLocation.contains("uaa/login")) {
        logger.error("认证失败！用户名或密码错误");
        return null;
      }
    } else if (StringUtils.isNotEmpty(uaaOauthResponse.getBody()) && uaaOauthResponse.getBody()
        .contains("用户登录")) {
      logger.error("认证失败！用户名或密码错误");
      return null;
    } else {
      logger.debug("用户[" + username + "]登录成功！");
    }
    /** 模拟浏览器用户授权认证结束. **/

    return myRestTemplate.getBasicCookieStore();
  }
}
