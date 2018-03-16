package com.ssgm.application.web;

import com.github.pagehelper.Page;
import com.ssgm.application.entity.Partner;
import com.ssgm.application.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author By: Wu Yongzhen
 * @Description Partner控制层
 * @Data 10:35 2018/3/16
 * @Modified By:
 **/
@Controller
@RequestMapping("partner")
public class PartnerController {
    @Autowired
    private PartnerService PartnerService;

    /**
     * @Author By:Wu Yongzhen
     * @Description 跳转至合伙人管理页面
     * @Date 11:47 2018/3/16
     */
    @RequestMapping("skipPartner")
    public String skipPartner() {
        return "partner";
    }

    /**
     * @Author By:Wu Yongzhen
     * @Description 合伙人展示数据，包括列表，分页，条件查询，时间查询
     * @Date 11:47 2018/3/16
     */

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map<String, Object> findList(
            @RequestParam(value = "parameter", defaultValue = "") String parameter,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<Partner> page = PartnerService.findList(parameter, pageNum, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageData", page);
        map.put("number", page.getTotal());
        return map;
    }

    /**
     * @Author By:Wu Yongzhen
     * @Description 合伙人申请存入数据库
     * @Date 11:47 2018/3/16
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map<String, Object> add(@RequestBody Partner parameters) {
        int i = PartnerService.insertPartner(parameters);
        Map map = new HashMap();
        map.put("status", i);
        return map;
    }

    /**
     * @Author By:Wu Yongzhen
     * @Description 添加备注
     * @Date 11:47 2018/3/16
     */
    @RequestMapping(value = "/saveRemark", method = RequestMethod.GET)
    public void saveRemark(Partner parameters) {
        PartnerService.saveRemark(parameters);
    }
}
