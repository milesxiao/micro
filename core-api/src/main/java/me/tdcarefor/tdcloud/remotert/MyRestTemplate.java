package me.tdcarefor.tdcloud.remotert;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by xsx on 2017/9/1.
 */
public class MyRestTemplate extends RestTemplate {

  private List<String> cookies = null;

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

  private synchronized List<String> getCoookies() {
    return cookies;
  }

  private synchronized void setCoookies(List<String> cookies) {
    this.cookies = cookies;
  }

  public synchronized void resetCoookies() {
    this.cookies = null;
  }


  private void processHeaders(HttpHeaders headers) {
    final List<String> cookies = headers.get("Set-Cookie");
    if (cookies != null && !cookies.isEmpty()) {
      setCoookies(cookies);
    }
  }

  @Override
  protected <T extends Object> T doExecute(URI url, HttpMethod method,
      final RequestCallback requestCallback, final ResponseExtractor<T> responseExtractor)
      throws RestClientException {
    final List<String> cookies = getCoookies();

    return super.doExecute(url, method, new RequestCallback() {
      @Override
      public void doWithRequest(ClientHttpRequest chr) throws IOException {
        if (cookies != null) {
          for (String cookie : cookies) {
            chr.getHeaders().add("Cookie", cookie);
          }
        }
        requestCallback.doWithRequest(chr);
      }

    }, new ResponseExtractor<T>() {
      @Override
      public T extractData(ClientHttpResponse chr) throws IOException {
        processHeaders(chr.getHeaders());
        return responseExtractor.extractData(chr);
      }
    });
  }
}
