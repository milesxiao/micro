package me.tdcarefor.tdcloud.user.model.enums;

/**
 * Created by xsx on 2017/5/17.
 */
public enum PermissionType {

  SELF("content", 1), // 字段内容
  IMMEDIATE_FAMILY("function", 2); // 功能

  private String name;
  private int index;

  private PermissionType(String name, int index) {
    this.name = name;
    this.index = index;
  }

  public static String getName(int index) {
    for (PermissionType c : PermissionType
        .values()) {
      if (c.getIndex() == index) {
        return c.name;
      }
    }
    return null;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }
}
