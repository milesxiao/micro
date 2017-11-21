package me.tdcarefor.tdcloud.remotert;

import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.List;
import me.tdcarefor.tdcloud.user.model.UserModel;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class TdCloudClientRT {

  private Logger logger = Logger.getLogger(TdCloudClientRT.class);

  private MyRestTemplate myRestTemplate;

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

  private boolean loginSuccess = false;

  public TdCloudClientRT() {

  }

  public boolean isLoginSuccess() {
    return loginSuccess;
  }

  public void setLoginSuccess(boolean loginSuccess) {
    this.loginSuccess = loginSuccess;
  }

  public synchronized boolean login(String username, String password) {
    if (loginSuccess) {
      return true;
    }

    myRestTemplate = new MyRestTemplate(readTimeout, connectTimeout);

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
        return loginSuccess;
      }
    } else if (StringUtils.isNotEmpty(uaaLoginResponse.getBody()) && uaaLoginResponse.getBody()
        .contains("用户登录")) {
      logger.error("认证失败！用户名或密码错误");
      return loginSuccess;
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
        return loginSuccess;
      }
    } else if (StringUtils.isNotEmpty(uaaOauthResponse.getBody()) && uaaOauthResponse.getBody()
        .contains("用户登录")) {
      logger.error("认证失败！用户名或密码错误");
      return loginSuccess;
    } else {
      loginSuccess = true;
    }
    /** 模拟浏览器用户授权认证结束. **/

    return loginSuccess;
  }

  @ApiOperation(value = "根据id查找用户", notes = "根据id查找用户", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserModel queryUserById(String id) {
    if (!loginSuccess) {
      login(userName, password);
    }
    String uri =
        restServiceUri + "/user/users/" + id;
    try {
      return myRestTemplate.getForObject(uri, UserModel.class);
    } catch (Exception e) {
      // 如果同步时异常，则将登录标记设置为false
      loginSuccess = false;
      throw e;
    }
  }

  @ApiOperation(value = "根据username查找", notes = "根据username查找", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserModel queryUserByUsername(String userName) {
    if (!loginSuccess) {
      login(userName, password);
    }
    String uri =
        restServiceUri + "/user/users/query?userName=" + userName;
    try {
      return myRestTemplate.getForObject(uri, UserModel.class);
    } catch (Exception e) {
      // 如果同步时异常，则将登录标记设置为false
      loginSuccess = false;
      throw e;
    }
  }

//  @ApiOperation(value = "根据unionid和用户类别查找用户", notes = "根据unionid和用户类别查找用户", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
//  public List<UserModel> queryUserByUnionIdAndUserType(String unionid, Integer type) {
//    if (!loginSuccess) {
//      login(userName, password);
//    }
//    String uri =
//        restServiceUri + "/user/users/unionid/" + unionid + "?type=" + type;
//    try {
//      UserModel[] umArray = myRestTemplate.getForObject(uri, UserModel[].class);
//      if (umArray != null && umArray.length > 0) {
//        return Arrays.asList(umArray);
//      } else {
//        return null;
//      }
//    } catch (Exception e) {
//      // 如果同步时异常，则将登录标记设置为false
//      loginSuccess = false;
//      throw e;
//    }
//  }

//  @ApiOperation(value = "根据id查找就诊人", notes = "根据id查找就诊人", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
//  public PatientModel queryPatientById(String id) {
//    if (!loginSuccess) {
//      login(userName, password);
//    }
//    String uri =
//        restServiceUri + "/patient/patients/" + id;
//    try {
//      return myRestTemplate.getForObject(uri, PatientModel.class);
//    } catch (Exception e) {
//      // 如果同步时异常，则将登录标记设置为false
//      loginSuccess = false;
//      throw e;
//    }
//  }
//
//  @ApiOperation(value = "根据医院id和nisid查找就诊人", notes = "根据医院id和nisid查找就诊人", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
//  public PatientModel queryPatientByHospitalIdAndNisId(@PathVariable("hospitalId") String hospitalId,
//      @PathVariable("nisId") String nisId) {
//    if (!loginSuccess) {
//      login(userName, password);
//    }
//    String uri =
//        restServiceUri + "/patient/patients/hospitalid/" + hospitalId + "/nisid/" + nisId;
//    try {
//      return myRestTemplate.getForObject(uri, PatientModel.class);
//    } catch (Exception e) {
//      // 如果同步时异常，则将登录标记设置为false
//      loginSuccess = false;
//      throw e;
//    }
//  }
//
//  @ApiOperation(value = "更新就诊人信息", notes = "更新就诊人信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResultDTO<String> updatePatient(String hospitalId, String nisId,PatientModel update) {
//    if (!loginSuccess) {
//      login(userName, password);
//    }
//    String uri =
//        restServiceUri + "/patient/patients/sync/update?hospitalId=" + hospitalId + "&nisId=" + nisId;
//    try {
//      return myRestTemplate.postForObject(uri, update, ResultDTO.class);
//    } catch (Exception e) {
//      loginSuccess = false;
//      throw e;
//    }
//  }
//
//  @ApiOperation(value = "根据appid查找微信app信息", notes = "根据appid查找微信app信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
//  public WxAppInfoModel queryWxAppByAppId(String appid) {
//    if (!loginSuccess) {
//      login(userName, password);
//    }
//    String uri =
//        restServiceUri + "/baseinfo/wxapps/appid/" + appid;
//    try {
//      return myRestTemplate.getForObject(uri, WxAppInfoModel.class);
//    } catch (Exception e) {
//      // 如果同步时异常，则将登录标记设置为false
//      loginSuccess = false;
//      throw e;
//    }
//  }
//
//  @ApiOperation(value = "添加微信app信息", notes = "添加微信app信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResultDTO<String> createWxApp(WxAppInfoModel wxAppInfoModel) {
//    if (!loginSuccess) {
//      login(userName, password);
//    }
//    String uri = restServiceUri + "/baseinfo/wxapps";
//
//    try {
//      return myRestTemplate.postForObject(uri, wxAppInfoModel, ResultDTO.class);
//    } catch (Exception e) {
//      // 如果同步时异常，则将登录标记设置为false
//      loginSuccess = false;
//      throw e;
//    }
//  }
//
//  @ApiOperation(value = "更新微信app信息信息", notes = "更新微信app信息信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResultDTO<String> updateWxApp(String id, WxAppInfoModel updateModel) {
//    if (!loginSuccess) {
//      login(userName, password);
//    }
//    String uri =
//        restServiceUri + "/baseinfo/wxapps/" + id;
//    try {
//      return myRestTemplate.postForObject(uri, updateModel, ResultDTO.class);
//    } catch (Exception e) {
//      loginSuccess = false;
//      throw e;
//    }
//  }
//
//  @ApiOperation(value = "通过id删除信息", notes = "通过id删除微信app信息", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
//  public void deleteWxApp(String id) {
//    if (!loginSuccess) {
//      login(userName, password);
//    }
//    String uri =
//        restServiceUri + "/baseinfo/wxapps/" + id;
//    try {
//      myRestTemplate.delete(uri);
//    } catch (Exception e) {
//      loginSuccess = false;
//      throw e;
//    }
//  }
//
//  @ApiOperation(value = "通过id删除信息", notes = "通过id删除微信app信息", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
//  public void deleteWxAppByAppId(String appid) {
//    if (!loginSuccess) {
//      login(userName, password);
//    }
//    String uri =
//        restServiceUri + "/baseinfo/wxapps/appid/" + appid;
//    try {
//      myRestTemplate.delete(uri);
//    } catch (Exception e) {
//      loginSuccess = false;
//      throw e;
//    }
//  }
//
//  @ApiOperation(value = "根据hospitalid查找微信app信息", notes = "根据hospitalid查找微信app信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
//  public List<WxAppInfoModel> queryWxAppInfosByHospitalId(String hospitalId) {
//    if (!loginSuccess) {
//      login(userName, password);
//    }
//    String uri =
//        restServiceUri + "/baseinfo/wxapps/hospitalid/" + hospitalId;
//    try {
//      WxAppInfoModel[] waArray = myRestTemplate.getForObject(uri, WxAppInfoModel[].class);
//      if (waArray != null && waArray.length > 0) {
//        return Arrays.asList(waArray);
//      } else {
//        return null;
//      }
//    } catch (Exception e) {
//      // 如果同步时异常，则将登录标记设置为false
//      loginSuccess = false;
//      throw e;
//    }
//  }
//
//  @ApiOperation(value = "根据就诊人id添加授权信息(供同步就诊人接口使用)", notes = "根据就诊人id添加授权信息(供同步就诊人接口使用)", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResultDTO<String> createPateintPermissions(String patientId) {
//    if (!loginSuccess) {
//      login(userName, password);
//    }
//    String uri =
//        restServiceUri + "/user/permissions/createPermissionsByPatientId?patientId=" + patientId;
//    try {
//      return myRestTemplate.postForObject(uri, null, ResultDTO.class);
//    } catch (Exception e) {
//      loginSuccess = false;
//      throw e;
//    }
//  }
//
//  @ApiOperation(value = "根据就诊人id添加授权信息", notes = "根据就诊人信息id添加授权信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResultDTO<String> createPateintPermissionsByPatientId(String patientId) {
//    if (!loginSuccess) {
//      login(userName, password);
//    }
//    String uri =
//        restServiceUri + "/patient/rolepermissions/createPermissionsByPatientId?patientId=" + patientId;
//    try {
//      return myRestTemplate.postForObject(uri, null, ResultDTO.class);
//    } catch (Exception e) {
//      loginSuccess = false;
//      throw e;
//    }
//  }
//
//  @ApiOperation(value = "根据就诊人id添加授权信息(新接口，供同步就诊人信息接口使用)", notes = "根据就诊人信息id添加授权信息(新接口，供同步就诊人信息接口使用)", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResultDTO<String> initPateintPermissionsByPatientId(String patientId) {
//    if (!loginSuccess) {
//      login(userName, password);
//    }
//    String uri =
//        restServiceUri + "/patient/rolepermissions/initPermissionsByPatientId?patientId=" + patientId;
//    try {
//      return myRestTemplate.postForObject(uri, null, ResultDTO.class);
//    } catch (Exception e) {
//      loginSuccess = false;
//      throw e;
//    }
//  }
}
