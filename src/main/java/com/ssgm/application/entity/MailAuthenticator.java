package com.ssgm.application.entity;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/**
 * @Author By: Wu Yongzhen
 * @Description 发件人账号密码
 * @Data 17:03 2018/6/25
 * @Modified By:
 **/

public class MailAuthenticator extends   Authenticator{

    public static String USERNAME = "wuyongzhen@ssgm.net";
    public static String PASSWORD = "zHfMECUZ9Qjww66u";

    public MailAuthenticator() {
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(USERNAME, PASSWORD);
    }

}