package com.ssgm.application.web;

import com.github.pagehelper.Page;
import com.ssgm.application.entity.CooperativePartner;
import com.ssgm.application.entity.Mail;
import com.ssgm.application.service.CooperativePartnerService;
import com.ssgm.application.util.MailOperation;
import com.ssgm.application.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author By: Wu Yongzhen
 * @Description 合作商申请Controller
 * @Data 14:09 2018/3/12
 * @Modified By:
 **/
@Controller
@RequestMapping("/cooperation")
public class CooperativePartnerController {
    @Autowired
    private CooperativePartnerService cooperativePartnerService;

    MailOperation operation = new MailOperation();

    /**
     * @Author By:Wu Yongzhen
     * @Description 跳转至合作商管理页面
     * @Date 9:39 2018/3/16
     */
    @RequestMapping("skipCooperation")
    public String skipCooperation() {
        return "cooperativePartner";
    }

    /**
     * @Author By:Wu Yongzhen
     * @Description 合作商展示数据，包括列表，分页，条件查询，时间查询
     * @Date 9:40 2018/3/16
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map<String, Object> findList(
            @RequestParam(value = "parameter", defaultValue = "") String parameter,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<CooperativePartner> page = cooperativePartnerService.findList(parameter, pageNum, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageData", page);
        map.put("number", page.getTotal());
        return map;
    }

    /**
     * @Author By:Wu Yongzhen
     * @Description 合作商申请存入数据库
     * @Date 9:41 2018/3/16
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(CooperativePartner parameters) throws Exception {
        cooperativePartnerService.insertCooperativePartner(parameters);
        StringBuffer sb = new StringBuffer();
        String[] project = {"网路宝（盛世云+无线WI-FI监管）项目", "网路神警上网行为审计项目（大型场所）", "特征码采集（卡扣）项目", "食品安全快检技术项目"};
        String string = "";
        String[] len = parameters.getIntention().split(",");
        for (int i = 0; i < len.length; i++) {
            string = string + project[Integer.parseInt(len[i]) - 1] + "; ";
        }
        String str = String.format(Mail.SUBJECT_HZS_FORMAT, parameters.getCompanyName(), parameters.getRegisteredCapital(), parameters.getBusiness(), parameters.getCompanyAddress(), parameters.getPrincipal(), parameters.getDuty(), parameters.getMobile(), parameters.getEmail(), string, parameters.getInspect() == 1 ? "是" : "否");
        sb.append("<!DOCTYPE>" + "<div bgcolor='#f1fcfa'   style='border:1px solid #d9f4ee; font-size:14px; line-height:22px; color:#005aa0;padding-left:1px;padding-top:5px;   padding-bottom:5px;'><span style='font-weight:bold;'>温馨提示：</span>"
                + "<div style='width:950px;font-family:arial;'>新的合作商申请信息，详情请登录管理平台查看：<br/><h2 style='color:green'>"
                + str + "</h2><br/>本邮件由系统自动发出，请勿回复。<br/>感谢您的使用。<br/>北京盛世光明软件股份有限公司</div>"
                + "</div>");
//        调用短信接口
        operation.sendMail(Mail.USER, Mail.PASSWORD, Mail.HOST, Mail.FROM, Mail.FROM_TO,
                Mail.SUBJECT_HZS, sb.toString());
    }

    /**
     * @Author By:Wu Yongzhen
     * @Description 添加备注
     * @Date 9:41 2018/3/16
     */
    @RequestMapping(value = "/saveRemark", method = RequestMethod.GET)
    public void saveRemark(CooperativePartner parameters) {
        cooperativePartnerService.saveRemark(parameters);
    }
}
