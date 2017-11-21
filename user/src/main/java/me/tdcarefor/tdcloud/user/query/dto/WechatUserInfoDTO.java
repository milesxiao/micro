package me.tdcarefor.tdcloud.user.query.dto;

import me.tdcarefor.tdcloud.user.model.UserModel;

/**
 * Created by miles on 2017/5/15.
 */
public class WechatUserInfoDTO extends UserModel {

  private Watermark watermark;

  public Watermark getWatermark() {
    return watermark;
  }
}
