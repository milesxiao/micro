package me.tdcarefor.tdcloud.open.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.tdcarefor.tdcloud.open.model.WxAppInfoModel;
import me.tdcarefor.tdcloud.open.query.WxAppInfoQueryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类: WxAppInfoController<br/>
 * 描述:  <br>
 * 作者: xsx <br>
 * 时间: 2016 16/5/18 上午2:16 <br>
 */
@RestController
@Api(tags = "微信app信息接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/wxapps")
public class WxAppInfoController {

  private final static Logger logger = LoggerFactory.getLogger(WxAppInfoController.class);

  @Autowired
  private WxAppInfoQueryRepository wxAppInfoQueryRepository;

  @ApiOperation(value = "根据id查找", notes = "根据id查找微信app信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public WxAppInfoModel queryById(@PathVariable("id") String id) {
    return wxAppInfoQueryRepository.findById(id);
  }
}
