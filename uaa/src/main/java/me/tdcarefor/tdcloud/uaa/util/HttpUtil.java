package me.tdcarefor.tdcloud.uaa.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.log4j.Logger;


/**
 * JSON数据处理类，用于接收各接口返回的JSON格式数据
 *
 * @category 各类微信公众号应用
 * @author zxj
 * @date 2013-11-26
 *
 */
public class HttpUtil {

  public static Logger log = Logger.getLogger(HttpUtil.class);

  /**
   * 采用GET请求方式获取远程接口数据，并转换成JSON对象
   *
   * 此方法仅适用于返回json格式数据的接口
   *
   *
   * @param urlstr
   * 		接口地址
   *
   * @return
   */
  public static String getRemoteData(String urlstr){

    StringBuffer buffer = new StringBuffer();

    try{

      URL url = new URL(urlstr);

      HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

      // 将返回的输入流转换成字符串
      InputStream inputStream = httpUrlConn.getInputStream();

      InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");

      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

      String str = null;

      while ((str = bufferedReader.readLine()) != null) {
        buffer.append(str);
      }

      log.info("获取远程数据("+urlstr+")->" + buffer.toString());

      bufferedReader.close();

      inputStreamReader.close();
      // 释放资源
      inputStream.close();
      inputStream = null;
      httpUrlConn.disconnect();
    } catch (ConnectException ce) {
      ce.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return buffer.toString();
  }

//  public static void main(String[] args) {
//    String result = "  {\n"
//        + "    \\\"access_token\\\":\\\"RRi63nymzsvrqbabVuhkLXe-Ci2S4n_Ec_UNPC2oUH2z3kaYMag4GeWaSWzhLHoWbeq5ZqqlIMvumFcxcPjwLM8djxvMeI72btlsOAjDrZw\\\",\n"
//        + "      \\\"expires_in\\\":7200,\n"
//        + "      \\\"refresh_token\\\":\\\"z3_QdGDI26cDwtu8nuYUyrdPDErarEvNX2jaOYlF23XGdS8uxrChjjBBUR6n_ducM01ks5wegNcnAUqwe_HnFHBVmIAdXK8FP26HVVKuo00\\\",\n"
//        + "      \\\"openid\\\":\\\"oxGM0wY4cPksBuAjaNugj7oXdpJs\\\",\n"
//        + "      \\\"scope\\\":\\\"snsapi_userinfo\\\",\n"
//        + "      \\\"unionid\\\":\\\"onm4r0mPVYeA_xdem8ggkyPuwECA\\\"\n"
//        + "  }";
//
//    result = result.replaceAll("\\\\", "");
//
//    WxLoginResult wxLoginResult = null;
//
//    if (StringUtils.isNotEmpty(result)) {
//      wxLoginResult = (WxLoginResult) JacksonUtil.jsonToBean(result, WxLoginResult.class);
//    }
//
//    System.out.println(wxLoginResult.getAccess_token());
//  }
}
