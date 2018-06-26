package com.ssgm.application.entity;

/**
 * @Author By: Wu Yongzhen
 * @Description
 * @Data 17:35 2018/6/25
 * @Modified By:
 **/
public class Mail {
    public static String USER = "ssgmhr@163.com";
    public static String PASSWORD = "ssgm1105";
    public static String HOST = "smtp.163.com";
    public static String FROM = "ssgmhr@163.com";
    public static String FROM_TO = "wuyongzhen@ssgm.net";// 收件人
    public static String SUBJECT_HZS = "[盛世光明]合作商申请信息";// 合作商
    public static String SUBJECT_HHR = "[盛世光明]合伙人申请信息";// 合伙人
    public static String SUBJECT_FK = "[盛世光明]反馈与建议";// 反馈与建议
    public static String SUBJECT_HZS_FORMAT = "<br/><h2 style='color:green'>公司全称：%s<br/>注册资本：%s万<br/>主营业务：%s<br/>公司地址：%s<br/>负责人：%s<br/>职务：%s<br/>手机/座机：%s<br/>电子邮件：%s<br/>意向产品项目：%s<br/>是否申请北京总部考察：%s<br/></h2><br/>";// 合作商
    public static String SUBJECT_HHR_FORMAT = "<br/><h2 style='color:green'>姓名：%s<br/>年龄：%s<br/>手机/座机：%s<br/>籍贯：%s<br/>电子邮件：%s<br/>单位名称：%s<br/>职务：%s<br/>单位地址：%s<br/></h2><br/>";// 合伙人
    public static String SUBJECT_FK_FORMAT = "<br/><h2 style='color:green'>反馈类型：%s<br/>反馈内容：%s<br/>您的姓名：%s<br/>联系方式：%s<br/></h2><br/>";// 反馈与建议
}
