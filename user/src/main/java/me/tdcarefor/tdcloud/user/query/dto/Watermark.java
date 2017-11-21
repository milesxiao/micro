package me.tdcarefor.tdcloud.user.query.dto;

/**
 * Created by miles on 2017/5/15.
 */
public class Watermark {

  private Long timestamp;

  private String appid;

  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }
}
