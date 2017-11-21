package me.tdcarefor.tdcloud.config;

/**
 * 类: JwtAccessTokenConverterConfig<br/>
 * 描述: 自定义Jwt转换,读取Jwt中的用户附加信息 <br>
 * 作者: tzw <br>
 * 时间: 2016 16/8/31 下午10:21 <br>
 */

import me.tdcarefor.tdcloud.uaa.security.CustomUserDetails;
import me.tdcarefor.tdcloud.user.model.UserModel;
import me.tdcarefor.util.BeanToMap;
import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;


@Configuration
public class JwtAccessTokenConverterConfig implements JwtAccessTokenConverterConfigurer {

  @Override
  public void configure(JwtAccessTokenConverter converter) {
    DefaultAccessTokenConverter accessTokenConverter = new CustomAccessTokenConverter();
    DefaultUserAuthenticationConverter userTokenConverter = new CustomUserAuthenticationConverter();
    // userTokenConverter.setUserDetailsService(customUserDetailsService);
    accessTokenConverter.setUserTokenConverter(userTokenConverter);
    converter.setAccessTokenConverter(accessTokenConverter);
  }



  protected  static class CustomAccessTokenConverter extends DefaultAccessTokenConverter{
    @Override
    public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
      return super.convertAccessToken(token, authentication);
    }

    @Override
    public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map) {
      return super.extractAccessToken(value, map);
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
      return super.extractAuthentication(map);
    }
  }

  protected static class CustomUserAuthenticationConverter extends DefaultUserAuthenticationConverter{
    private Collection<? extends GrantedAuthority> defaultAuthorities;

    //从jwt中读取用户信息
    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
      if (map.containsKey(USERNAME)) {
        Object principal = map.get(USERNAME);
        Collection<? extends GrantedAuthority> authorities = getAuthorities(map);
        Map<String,?> userModelMap= (Map<String, ?>) map.get("userModel");
        UserModel userModel= null;
        try {
          userModel=(UserModel)BeanToMap.convertMap(UserModel.class,userModelMap);
        } catch (Exception e) {
          e.printStackTrace();
        }

        CustomUserDetails user = new CustomUserDetails(userModel,authorities);
        authorities = user.getAuthorities();
        principal = user;

        return new UsernamePasswordAuthenticationToken(principal, "N/A", authorities);
      }
      return null;
    }

    @Override
    public void setDefaultAuthorities(String[] defaultAuthorities) {
      this.defaultAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils
          .arrayToCommaDelimitedString(defaultAuthorities));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
      if (!map.containsKey(AUTHORITIES)) {
        return defaultAuthorities;
      }
      Object authorities = map.get(AUTHORITIES);
      if (authorities instanceof String) {
        return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
      }
      if (authorities instanceof Collection) {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils
            .collectionToCommaDelimitedString((Collection<?>) authorities));
      }
      throw new IllegalArgumentException("Authorities must be either a String or a Collection");
    }
  }


}
