package me.tdcarefor.tdcloud.user.helper;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import me.tdcarefor.tdcloud.user.util.PKCS7EncoderUtil;
import sun.misc.BASE64Decoder;

/**
 * 对微信小程序用户加密数据的解密示例代码. 
 *
 * @ClassName WechatDataDecryptHelper
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author tf
 * @Date 2016年11月19日 下午2:56:36 
 * @version 1.0.0
 */
public class WechatDataDecryptHelper {

  /* 
   * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。 
   */

  /**
   * 对于官方加密数据（encryptData）解密说明如下： 加密数据解密算法 接口如果涉及敏感数据（如wx.getUserInfo当中的 
   * openId 和unionId ），接口的明文内容将不包含这些敏感数据。开发者如需要获取敏感数据，需要对接口返回的加密数据( 
   * encryptedData )进行对称解密。 解密算法如下： 对称解密使用的算法为 AES-128-CBC，数据采用PKCS#7填充。 
   * 对称解密的目标密文为 Base64_Decode(encryptedData), 对称解密秘钥 aeskey = 
   * Base64_Decode(session_key), aeskey 是16字节 对称解密算法初始向量 iv 会在数据接口中返回。 
   *
   * @Description (TODO这里用一句话描述这个方法的作用)
   * @param encryptedData
   *            加密内容 
   * @param sessionKey
   *            小程序登录sessionKey 
   * @param iv
   *            解密算法初始向量 iv 会在数据接口中返回。 
   * @param encodingFormat
   *            编码格式默认UTF-8 
   * @return 返回解密后的字符串
   * @throws Exception
   */
  public static String decrypt(String encryptedData, String sessionKey, String iv, String encodingFormat) throws Exception {
    try {
      Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
      BASE64Decoder base64Decoder = new BASE64Decoder();
      byte[] _encryptedData = base64Decoder.decodeBuffer(encryptedData);
      byte[] _sessionKey = base64Decoder.decodeBuffer(sessionKey);
      byte[] _iv = base64Decoder.decodeBuffer(iv);
      SecretKeySpec secretKeySpec = new SecretKeySpec(_sessionKey, "AES");
      IvParameterSpec ivParameterSpec = new IvParameterSpec(_iv);
      cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
      byte[] original = cipher.doFinal(_encryptedData);
      byte[] bytes = PKCS7EncoderUtil.decode(original);
      String originalString = new String(bytes, encodingFormat);
      return originalString;
    } catch (Exception ex) {
      return null;
    }
  }

//  public static void main(String[] args) throws Exception {
//    // 需要加密的字串
//    // String appid = "wx4f4bc4dec97d474b";
//    String sessionKey = "tiihtNczf5v6AKRyjwEUhQ==";
//
//    String encryptedData = "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZM" + "QmRzooG2xrDcvSnxIMXFufNstNGTyaGS"
//        + "9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+" + "3hVbJSRgv+4lGOETKUQz6OYStslQ142d"
//        + "NCuabNPGBzlooOmB231qMM85d2/fV6Ch" + "evvXvQP8Hkue1poOFtnEtpyxVLW1zAo6"
//        + "/1Xx1COxFvrc2d7UL/lmHInNlxuacJXw" + "u0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn"
//        + "/Hz7saL8xz+W//FRAUid1OksQaQx4CMs" + "8LOddcQhULW4ucetDf96JcR3g0gfRK4P"
//        + "C7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB" + "6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns"
//        + "/8wR2SiRS7MNACwTyrGvt9ts8p12PKFd" + "lqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYV"
//        + "oKlaRv85IfVunYzO0IKXsyl7JCUjCpoG" + "20f0a04COwfneQAGGwd5oa+T8yO5hzuy" + "Db/XcxxmK01EpqOyuxINew==";
//
//    String iv = "r7BXXKkLb8qrSNn05n0qiA==";
//    String deString = WechatDataDecryptHelper.getInstance().decrypt(encryptedData, sessionKey, iv, "utf-8");
//
//    ObjectMapper mapper = new ObjectMapper();
//
//    try {
//      System.out.println("before t=" + deString);
//      WechatUserInfoDTO user = mapper.readValue(deString, WechatUserInfoDTO.class);
//      System.out.println("obj=" + user);
//      String jsonInString = mapper.writeValueAsString(user);
//      System.out.println("after t=" + jsonInString);
//
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
}