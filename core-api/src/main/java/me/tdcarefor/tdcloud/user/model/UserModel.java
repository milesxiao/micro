package me.tdcarefor.tdcloud.user.model;

import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import me.tdcarefor.tdcloud.model.IdAndHospitalInfoModel;

/**
 * Created by xsx on 17/3/2.
 */
@MappedSuperclass
public class UserModel extends IdAndHospitalInfoModel {

    @ApiModelProperty(value ="用户openid")
    private String openId;

    @ApiModelProperty(value ="用户名称")
    private String userName;

    @ApiModelProperty(value ="用户密码")
    private String password;

    @ApiModelProperty(value ="用户微信昵称")
    private String nickname;

    @ApiModelProperty(value ="用户手机号码")
    private String mobile;

    @ApiModelProperty(value ="用户性别")
    private Integer gender;

    @ApiModelProperty(value ="用户所属国家")
    private String country;

    @ApiModelProperty(value ="用户所属省份")
    private String province;

    @ApiModelProperty(value ="用户所属城市")
    private String city;

    @ApiModelProperty(value ="用户avatarUrl")
    private String avatarUrl;

    @ApiModelProperty(value ="用户unionid")
    private String unionId;

    @ApiModelProperty(value ="微信公众号或小程序appid")
    private String appId;

    @ApiModelProperty(value ="用户类别(0:平台用户 1:小程序用户 2:公众号用户)")
    @Column(name="userType",columnDefinition="int(11) default 1")
    private Integer userType;

    @ApiModelProperty(value ="记录创建日期")
    private Date created;

    @ApiModelProperty(value ="记录更新日期")
    private Date updated;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}