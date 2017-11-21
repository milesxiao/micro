package me.tdcarefor.tdcloud.apigateway.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author Thibaud Leprêtre
 */
class DynamicOauth2ClientContextFilter extends OAuth2ClientContextFilter {

    private Logger logger = Logger.getLogger(DynamicOauth2ClientContextFilter.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void redirectUser(UserRedirectRequiredException e, HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        String redirectUri = e.getRedirectUri();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(redirectUri);
        Map<String, String> requestParams = e.getRequestParams();
        for (Map.Entry<String, String> param : requestParams.entrySet()) {
            String key = param.getKey();
            String value = param.getValue();
//            if (StringUtils.isNotEmpty(value)) {
//                value = value.replaceAll("http://", "https://");
//                value = value.replaceAll(proxyIp, "wxmini.tdcarefor.me");
//            }
            builder.queryParam(key, value);
        }

        if (e.getStateKey() != null) {
            builder.queryParam("state", e.getStateKey());
        }

        String url = getBaseUrl(request) + builder.build().encode().toUriString();
//        // 将http请求强制转换为https请求
//        if (url.contains("http://")) {
//            url = url.replaceAll("http://", "https://");
//        }
//        logger.info("******** oauth redirect url:" + url);
        this.redirectStrategy.sendRedirect(request, response, url);
    }

    @Override
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    private String getBaseUrl(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String baseUrl = url.substring(0, url.length() - request.getRequestURI().length() + request.getContextPath().length());
        // 将http请求强制转换为https请求
//        if (baseUrl.contains("http://")) {
//            baseUrl = baseUrl.replaceAll("http://", "https://");
//        }
        return baseUrl;
    }
}
