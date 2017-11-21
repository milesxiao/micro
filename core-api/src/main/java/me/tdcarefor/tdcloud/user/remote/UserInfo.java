package me.tdcarefor.tdcloud.user.remote;

import io.swagger.annotations.ApiOperation;
import me.tdcarefor.tdcloud.user.model.UserModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 类: UserInfo<br/>
 * 描述:  <br>
 * 作者: tzw <br>
 * 时间: 2016 16/8/30 下午11:40 <br>
 */
@FeignClient(value = "user")
public interface UserInfo {

    @ApiOperation(value = "根据id查找", notes = "根据id查找用户", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public UserModel queryUserById(@PathVariable("id") String id);

    @ApiOperation(value = "根据username查找", notes = "根据username查找", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/users/query", method = RequestMethod.GET)
    public UserModel queryUserByUsername(@RequestParam(value = "userName") String userName);

}
