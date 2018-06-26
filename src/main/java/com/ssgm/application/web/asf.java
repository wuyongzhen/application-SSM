package com.ssgm.application.web;

import com.ssgm.application.util.MailOperation;
import com.ssgm.application.util.RandomUtil;

/**
 * @Author By: Wu Yongzhen
 * @Description
 * @Data 17:13 2018/6/25
 * @Modified By:
 **/
public class asf {
    public static void main(String[] args) {

        MailOperation operation = new MailOperation();
        String user = "17600032017@163.com";
        String password = "w111111";
        String host = "smtp.163.com";
        String from = "17600032017@163.com";
        String to = "45473219@qq.com";// 收件人
        String subject = "111";
        //邮箱内容
        StringBuffer sb = new StringBuffer();
        String yzm = RandomUtil.generateString(6);
        sb.append("<!DOCTYPE>"+"<div bgcolor='#f1fcfa'   style='border:1px solid #d9f4ee; font-size:14px; line-height:22px; color:#005aa0;padding-left:1px;padding-top:5px;   padding-bottom:5px;'><span style='font-weight:bold;'>温馨提示：</span>"
                + "<div style='width:950px;font-family:arial;'>欢迎使用NET微活动，您的注册码为：<br/><h2 style='color:green'>"+yzm+"</h2><br/>本邮件由系统自动发出，请勿回复。<br/>感谢您的使用。<br/>杭州恩意替电子商务有限公司</div>"
                +"</div>");
        try {
            String res = operation.sendMail(user, password, host, from, to,
                    subject, sb.toString());
            System.out.println(res);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
