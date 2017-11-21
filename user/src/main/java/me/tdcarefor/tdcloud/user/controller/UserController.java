package me.tdcarefor.tdcloud.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import me.tdcarefor.tdcloud.uaa.security.CustomUserDetails;
import me.tdcarefor.tdcloud.uaa.security.LoginedUserDetails;
import me.tdcarefor.tdcloud.user.model.UserModel;
import me.tdcarefor.tdcloud.user.query.entity.UserEntity;
import me.tdcarefor.tdcloud.user.query.repository.UserQueryRepository;
import me.tdcarefor.util.CachedBeanCopier;
import me.tdcarefor.util.ResultDTO;
import org.axonframework.domain.IdentifierFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类: UserController<br/>
 * 描述:  <br>
 * 作者: xsx <br>
 * 时间: 2016 16/5/18 上午2:16 <br>
 */


@RestController
@Api(tags = "用户信息接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/users")
public class UserController {

  private final static Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserQueryRepository userQueryRepository;

  @ApiOperation(value = "添加用户", notes = "添加新用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResultDTO<String> create(@RequestBody UserModel userModel) {
    UserEntity entity = new UserEntity();
    String id = IdentifierFactory.getInstance().generateIdentifier();
    entity.setId(id);
    CachedBeanCopier.copy(userModel,entity);
    entity.setCreated(new Date());
    userQueryRepository.save(entity);

    return new ResultDTO<>(1, "添加成功", id);
  }

  @ApiOperation(value = "根据id查找", notes = "根据id查找用户", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public UserModel queryById(@PathVariable("id") String id) {
    return userQueryRepository.findById(id);
  }

  @ApiOperation(value = "根据username查找", notes = "根据username查找", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
  @RequestMapping(value = "/query", method = RequestMethod.GET)
  public UserModel queryByUsername(@RequestParam(value = "userName") String userName) {
    return userQueryRepository.findByUserName(userName);
  }

  @ApiOperation(value = "根据unionid和用户类别查找用户", notes = "根据unionid和用户类别查找用户", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
  @RequestMapping(value = "/unionid/{unionid}", method = RequestMethod.GET)
  public List<UserEntity> queryByUnionIdAndUserType(@PathVariable("unionid") String unionid,
      @RequestParam(value = "type") Integer type) {
    return userQueryRepository.findByUnionIdAndUserType(unionid, type);
  }

  @ApiOperation(value = "根据openid查找", notes = "根据openid查找用户", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
  @RequestMapping(value = "/openids/{openId}", method = RequestMethod.GET)
  public UserModel queryByopenId(@PathVariable("openId") String openId) {
    List<UserEntity> userList = userQueryRepository.findByOpenId(openId);
    return CollectionUtils.isEmpty(userList) ? null : userList.get(0);
  }

  @ApiOperation(value = "查找所有用户", notes = "查找所有用户", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
  @RequestMapping(value = "", method = RequestMethod.GET)
  public List<UserModel> queryAll() {
    List<UserModel> resultList = null;
    Iterable<UserEntity> result = userQueryRepository.findAll();
    if (result != null && result.iterator() != null) {
      resultList = new ArrayList<>();
      Iterator<UserEntity> it = result.iterator();
      while (it.hasNext()) {
        resultList.add(it.next());
      }
    }

    return resultList;
  }

  @ApiOperation(value = "更新用户信息", notes = "更新用户信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
  @RequestMapping(value = "/{id}", method = RequestMethod.POST)
  public ResultDTO<String> update(@PathVariable("id") String id,
      @RequestBody UserModel updateCommand) {
    if (userQueryRepository.exists(id)) {
      UserEntity entity = userQueryRepository.findById(id);
      entity.setUpdated(new Date());
      CachedBeanCopier.copy(updateCommand, entity);
      userQueryRepository.save(entity);

      return new ResultDTO<>(1, "更新成功");
    } else {
      return new ResultDTO<>(0, "更新失败,数据不存在.");
    }
  }

  @ApiOperation(value = "更新用户密码", notes = "更新用户密码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
  @RequestMapping(value = "/updatePassWord", method = RequestMethod.POST)
  public ResultDTO<String> updatePassWord(@RequestParam(value = "id") String id ,@RequestParam(value = "oldpwd") String oldpwd,@RequestParam(value = "newpwd") String newpwd,
      @RequestParam(value = "renewpwd") String renewpwd) {
    if(oldpwd==null || newpwd==null || renewpwd==null) {
      return new ResultDTO<>(0, "修改失败!参数不正确");
    }
    if(!newpwd.equals(renewpwd)) {
      return new ResultDTO<>(0, "两次输入的新密码不匹配");
    }

    if(userQueryRepository.exists(id)) {
      UserEntity userEntity = userQueryRepository.findById(id);
      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      //  String pwd = passwordEncoder.encode(newpwd);
      if(passwordEncoder.matches(oldpwd,userEntity.getPassword())) {
        userEntity.setPassword(newpwd);
        userEntity.setUpdated(new Date());
        userQueryRepository.save(userEntity);

        return new ResultDTO<>(1, "修改成功");
      } else {
        return new ResultDTO<>(0, "修改失败");
      }
    } else {
      return new ResultDTO<>(0, "用户不存在");
    }
  }

  @ApiOperation(value = "通过id删除用户", notes = "通过id删除用户", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResultDTO<String> delete(@PathVariable("id") String id) {
    if (userQueryRepository.exists(id)) {
      userQueryRepository.delete(id);
      return new ResultDTO<>(1, "删除成功");
    } else {
      return new ResultDTO<>(0, "删除失败,数据不存在.");
    }
  }

  @ApiOperation(value = "获取当前登录用户信息", notes = "获取当前登录用户信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
  @RequestMapping(value = "/logineduser", method = RequestMethod.GET)
  public UserModel getLoginedUser() {
    CustomUserDetails cud = LoginedUserDetails.getLoginedUserDetails();
    if (cud != null) {
      UserModel um = new UserModel();
      CachedBeanCopier.copy(cud, um);

      return um;
    } else {
      return null;
    }
  }

//  @ApiOperation(value = "解密微信接口返回的信息（针对一些用户的敏感信息）", notes = "解密微信接口返回的信息（针对一些用户的敏感信息）", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
//  @RequestMapping(value = "/decryptforwx", method = RequestMethod.POST)
//  public String decryptForWx(HttpServletRequest request,
//      @RequestParam(value = "encryptedData") String encryptedData,
//      @RequestParam(value = "iv") String iv,
//      @RequestParam(value = "accessToken") String accessToken) {
//    String encodingFormat = "utf-8";
//    String result = null;
//
//    logger.info("sessionKey from session=" + request.getSession().getAttribute(WechatConstants.NAME_SESSIONKEY));
//
//    try {
//      String sessionKey = loginCacheHelper.getSessionKey(accessToken);
//      logger.info("sessionKey from cache=" + sessionKey);
//      result = WechatDataDecryptHelper.decrypt(encryptedData, sessionKey, iv, encodingFormat);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//
//    return result;
//  }
}
