package me.tdcarefor.tdcloud.apigateway.remote;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by xsx on 2017/9/1.
 */
public class MyRestTemplate extends RestTemplate {

  private BasicCookieStore basicCookieStore;

  public BasicCookieStore getBasicCookieStore() {
    return basicCookieStore;
  }

  public void setBasicCookieStore(BasicCookieStore basicCookieStore) {
    this.basicCookieStore = basicCookieStore;
  }

  public MyRestTemplate(int readTimeout, int connectTimeout) {
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    factory.setConnectTimeout(connectTimeout);
    factory.setReadTimeout(readTimeout);
    this.basicCookieStore = new BasicCookieStore();
    HttpClient httpClient = HttpClientBuilder.create()
        .setDefaultCookieStore(this.basicCookieStore)
        .setRedirectStrategy(new LaxRedirectStrategy())
        .build();
    factory.setHttpClient(httpClient);
    this.setRequestFactory(factory);
  }
}
