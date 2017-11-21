package me.tdcarefor.tdcloud.open.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.tdcarefor.tdcloud.open.model.HospitalModel;
import me.tdcarefor.tdcloud.open.query.HospitalEntity;
import me.tdcarefor.tdcloud.open.query.HospitalQueryRepository;
import me.tdcarefor.tdcloud.remotert.TdCloudClientRT;
import me.tdcarefor.tdcloud.uaa.security.CustomUserDetails;
import me.tdcarefor.tdcloud.uaa.security.LoginedUserDetails;
import me.tdcarefor.tdcloud.user.model.UserModel;
import me.tdcarefor.tdcloud.user.remote.UserInfo;
import me.tdcarefor.util.JacksonUtil;
import me.tdcarefor.util.ResultDTO;
import org.apache.commons.lang.StringUtils;
import org.axonframework.domain.IdentifierFactory;
import org.hsqldb.lib.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类: HospitalController<br/>
 * 描述:  <br>
 * 作者: xsx <br>
 * 时间: 2016 16/5/18 上午2:16 <br>
 */
@RestController
@Api(tags = "医院信息接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/hospitals")
public class HospitalController {

  private final static Logger logger = LoggerFactory.getLogger(HospitalController.class);

  @Autowired
  private HospitalQueryRepository hospitalQueryRepository;

//  @Autowired
//  private UserInfo userInfo;

  @Autowired
  private TdCloudClientRT tdCloudClientRT;

  @ApiOperation(value = "添加医院信息", notes = "添加医院信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResultDTO<String> create(@RequestBody HospitalEntity command) {
    String id = IdentifierFactory.getInstance().generateIdentifier();
    command.setId(id);
    hospitalQueryRepository.save(command);
    return new ResultDTO<>(1, "添加成功", id);
  }

  @ApiOperation(value = "根据id查找", notes = "根据id查找医院信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public HospitalModel queryById(@PathVariable("id") String id) {

    // 判断用户是否有权限查看就诊人的账单信息
    CustomUserDetails loginedUser = LoginedUserDetails.getLoginedUserDetails();

    String userId = "";
    if (loginedUser == null) {
      userId = "0010fbe0-7f1e-41d1-b2be-22ddfe20f3b97";
    } else {
      userId = loginedUser.getUserId();
    }
    logger.debug("登录用户为：" + userId);

    UserModel user = tdCloudClientRT.queryUserById(userId);
    logger.debug("获取用户信息：" + JacksonUtil.beanToJson(user));

    return hospitalQueryRepository.findById(id);
  }

  @ApiOperation(value = "查找所有医院信息", notes = "查找所有医院信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
  @RequestMapping(value = "", method = RequestMethod.GET)
  public List<HospitalEntity> queryAll() {
    List<HospitalEntity> resultList = null;
    Iterable<HospitalEntity> result = hospitalQueryRepository.findAll();
    if (result != null && result.iterator() != null) {
      resultList = new ArrayList<>();
      Iterator<HospitalEntity> it = result.iterator();
      while (it.hasNext()) {
        resultList.add(it.next());
      }
    }

    return resultList;
  }

  @ApiOperation(value = "更新医院信息信息", notes = "更新医院信息信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
  @RequestMapping(value = "/{id}", method = RequestMethod.POST)
  public ResultDTO<String> update(@PathVariable("id") String id,
      @RequestBody HospitalEntity updateCommand) {
    if (hospitalQueryRepository.exists(id)) {
      updateCommand.setId(id);
      hospitalQueryRepository.save(updateCommand);
      return new ResultDTO<>(1, "更新成功");
    } else {
      return new ResultDTO<>(0, "更新失败,数据不存在.");
    }
  }

  @ApiOperation(value = "通过id删除医院信息", notes = "通过id删除医院信息", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE)
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResultDTO<String> delete(@PathVariable("id") String id) {
    if (hospitalQueryRepository.exists(id)) {
      hospitalQueryRepository.delete(id);
      return new ResultDTO<>(1, "删除成功");
    } else {
      return new ResultDTO<>(0, "删除失败,数据不存在.");
    }
  }
}
