package me.tdcarefor.tdcloud.apigateway.controller;

import feign.Feign;
import feign.FeignException;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.zuul.ZuulConfiguration;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 类: ApiGatewayController<br/>
 * 描述:  <br>
 * 作者: tzw <br>
 * 时间: 2016 16/8/23 下午8:13 <br>
 */
@Controller
public class ApiGatewayController {

  private Logger logger = Logger.getLogger(ApiGatewayController.class);

  @Autowired
  private ZuulConfiguration zuulConfiguration;//获取zuul的配置信息

  @Value("${proxy.ip}")
  private String proxyIp;

  @Autowired
  private OAuth2FeignRequestInterceptor oAuth2FeignRequestInterceptor;

  @Autowired
  private LoadBalancerClient loadBalancerClient;

  @RequestMapping("/")
  public String index(ModelMap modelMap) {
    modelMap.put("username", SecurityContextHolder.getContext().getAuthentication().getName());
    return "index";
  }

  @RequestMapping("/api")
  public String api(ModelMap modelMap, HttpServletRequest httpServletRequest) {
    List<Route> services = zuulConfiguration.routeLocator().getRoutes();

    modelMap.put("username", SecurityContextHolder.getContext().getAuthentication().getName());
    modelMap.put("routes", services);
    modelMap.put("zuulUrl", proxyIp);
    return "logined";
  }

  //各微服务的版本号
//  @RequestMapping("/apis")
//  public @ResponseBody
//  List<Map<String, String>> apis() {
//    List<Map<String, String>> apis = new ArrayList<>();
//    List<Route> services = zuulConfiguration.routeLocator().getRoutes();
//    SwaggerParser swaggerParer = new SwaggerParser();
//    String serviceName = "";
//    for (Route route : services) {
//      try {
//        serviceName = route.getId();
//        if (serviceName.equals("configserver") || serviceName.equals("uaa")) {
//          continue;
//        }
//        String apiUrl = loadBalancerClient.choose(serviceName).getUri().toString();
//        SwaggerApi api = Feign.builder().requestInterceptor(oAuth2FeignRequestInterceptor)
//            .target(SwaggerApi.class, apiUrl);
//        Swagger swagger = swaggerParer.parse(api.getApiDocs());
//        String apiVersion = swagger.getInfo().getVersion();
//        Map<String, String> apiMap = new HashMap<>();
//        apiMap.put("serviceName", serviceName);
//        apiMap.put("version", apiVersion);
//        apis.add(apiMap);
//      } catch (Exception e) {
//        Map<String, String> apiMap = new HashMap<>();
//        apiMap.put("serviceName", serviceName);
//        if (serviceName.equals("uaa") && e instanceof FeignException) {
//          FeignException feignException = (FeignException) e;
//          if (feignException.status() == 404) {
//            apiMap.put("version", "1.0.0");
//          } else {
//            apiMap.put("version", null);
//          }
//        } else {
//          apiMap.put("version", null);
//        }
//        apis.add(apiMap);
//      }
//    }
//    return apis;
//  }

  @RequestMapping("/logined")
  public String logined(ModelMap modelMap, HttpServletRequest httpServletRequest) {
    return "redirect:/webui/";
  }

}
