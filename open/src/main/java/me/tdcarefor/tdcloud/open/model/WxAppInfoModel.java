package me.tdcarefor.tdcloud.open.model;

import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import me.tdcarefor.tdcloud.model.IdAndHospitalInfoModel;

/**
 * Created by xsx on 17/3/2.
 */

@MappedSuperclass
public class WxAppInfoModel extends IdAndHospitalInfoModel {

    @ApiModelProperty(value ="授权方昵称")
    private String nickName;

    @ApiModelProperty(value ="授权方头像")
    private String headImg;

    @ApiModelProperty(value ="授权方公众号类型，0代表订阅号，1代表由历史老帐号升级后的订阅号，2代表服务号")
    private String serviceTypeInfo;

    @ApiModelProperty(value ="授权方认证类型，-1代表未认证，0代表微信认证，1代表新浪微博认证，2代表腾讯微博认证，3代表已资质认证通过但还未通过名称认证，4代表已资质认证通过、还未通过名称认证，但通过了新浪微博认证，5代表已资质认证通过、还未通过名称认证，但通过了腾讯微博认证")
    private String verifyTypeInfo;

    @ApiModelProperty(value ="授权方公众号的原始ID")
    private String userName;

    @ApiModelProperty(value ="公众号的主体名称")
    private String principalName;

    @ApiModelProperty(value ="用以了解以下功能的开通状况（0代表未开通，1代表已开通）：\n"
        + " open_store:是否开通微信门店功能\n"
        + " open_scan:是否开通微信扫商品功能\n"
        + " open_pay:是否开通微信支付功能\n"
        + " open_card:是否开通微信卡券功能\n"
        + " open_shake:是否开通微信摇一摇功能")
    @Column(columnDefinition="TEXT")
    private String businessInfo;

    @ApiModelProperty(value ="授权方公众号所设置的微信号，可能为空")
    private String alias;

    @ApiModelProperty(value ="二维码图片的URL，开发者最好自行也进行保存")
    private String qrcodeUrl;

    @ApiModelProperty(value ="授权方appid")
    private String appId;

    @ApiModelProperty(value ="授权方公众号的授权信息")
    @Column(columnDefinition="TEXT")
    private String funcInfo;

    @ApiModelProperty(value ="app类型(1:微信公众号 2:微信小程序)")
    private Integer appType;

    @ApiModelProperty(value ="授权方的access token")
    private String authorizerAccessToken;

    @ApiModelProperty(value ="授权方的access token过期时间")
    private Date authorizerAccessTokenExpiredTime;

    @ApiModelProperty(value ="授权方的refresh access token")
    private String authorizerRefreshAccessToken;

    @ApiModelProperty(value ="记录标记(0:删除 1:正常)")
    private Integer status;

    @ApiModelProperty(value ="记录创建时间")
    private Date created;

    @ApiModelProperty(value ="记录更新时间")
    private Date updated;

    public Date getAuthorizerAccessTokenExpiredTime() {
        return authorizerAccessTokenExpiredTime;
    }

    public void setAuthorizerAccessTokenExpiredTime(Date authorizerAccessTokenExpiredTime) {
        this.authorizerAccessTokenExpiredTime = authorizerAccessTokenExpiredTime;
    }

    public String getAuthorizerRefreshAccessToken() {
        return authorizerRefreshAccessToken;
    }

    public void setAuthorizerRefreshAccessToken(String authorizerRefreshAccessToken) {
        this.authorizerRefreshAccessToken = authorizerRefreshAccessToken;
    }

    public String getAuthorizerAccessToken() {
        return authorizerAccessToken;
    }

    public void setAuthorizerAccessToken(String authorizerAccessToken) {
        this.authorizerAccessToken = authorizerAccessToken;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getFuncInfo() {
        return funcInfo;
    }

    public void setFuncInfo(String funcInfo) {
        this.funcInfo = funcInfo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getServiceTypeInfo() {
        return serviceTypeInfo;
    }

    public void setServiceTypeInfo(String serviceTypeInfo) {
        this.serviceTypeInfo = serviceTypeInfo;
    }

    public String getVerifyTypeInfo() {
        return verifyTypeInfo;
    }

    public void setVerifyTypeInfo(String verifyTypeInfo) {
        this.verifyTypeInfo = verifyTypeInfo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getBusinessInfo() {
        return businessInfo;
    }

    public void setBusinessInfo(String businessInfo) {
        this.businessInfo = businessInfo;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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
}
