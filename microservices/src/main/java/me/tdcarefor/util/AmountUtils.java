package me.tdcarefor.util;

import java.math.BigDecimal;


/**
 * com.util.AmountUtils
 * @description  金额元分之间转换工具类
 * @author zcm0708@sina.com
 * @2012-2-7下午12:58:00
 */
public class AmountUtils {

  /**金额为分的格式 */
  public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";

  /**
   * 将分为单位的转换为元并返回金额格式的字符串 （除100）
   *
   * @param amount
   * @return
   * @throws Exception
   */
  public static String changeF2Y(Long amount) {
    int flag = 0;
    String amString = amount.toString();
    if(amString.charAt(0)=='-'){
      flag = 1;
      amString = amString.substring(1);
    }
    StringBuffer result = new StringBuffer();
    if(amString.length()==1){
      result.append("0.0").append(amString);
    }else if(amString.length() == 2){
      result.append("0.").append(amString);
    }else{
      String intString = amString.substring(0,amString.length()-2);
      for(int i=1; i<=intString.length();i++){
        if( (i-1)%3 == 0 && i !=1){
          result.append(",");
        }
        result.append(intString.substring(intString.length()-i,intString.length()-i+1));
      }
      result.reverse().append(".").append(amString.substring(amString.length()-2));
    }
    if(flag == 1){
      return "-"+result.toString();
    }else{
      return result.toString();
    }
  }

  /**
   * 将元为单位的转换为分 （乘100）
   *
   * @param amount
   * @return
   */
  public static Long changeY2F(Float amount){
    return BigDecimal.valueOf(amount).multiply(new BigDecimal(100)).longValue();
  }

//  public static void main(String[] args) {
//    System.out.println("元转成分：" + AmountUtils.changeY2F(new Float(15.42)));
//    System.out.println("分转成元：" + AmountUtils.changeF2Y(new Long(987)));
//  }
}
