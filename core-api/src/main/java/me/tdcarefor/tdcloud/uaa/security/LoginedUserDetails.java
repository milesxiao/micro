package me.tdcarefor.tdcloud.uaa.security;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 类: LoginedUserDetails<br/>
 * 描述: 微服务中获取当前登陆用户信息 <br>
 * 作者: tzw <br>
 * 时间: 2016 16/9/19 上午1:17 <br>
 */
public class LoginedUserDetails {

    private static Logger logger = Logger.getLogger(LoginedUserDetails.class);

    public static CustomUserDetails getLoginedUserDetails(){
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof CustomUserDetails) {
                return  (CustomUserDetails) principal;
            }else {
                return null;
            }
        } catch (Exception e) {
            logger.error("获取当前登录用户信息异常！", e);
        }

        return null;
    }
}
