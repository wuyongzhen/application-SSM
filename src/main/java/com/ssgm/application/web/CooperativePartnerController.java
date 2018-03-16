package com.ssgm.application.web;

import com.github.pagehelper.Page;
import com.ssgm.application.entity.CooperativePartner;
import com.ssgm.application.service.CooperativePartnerService;
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

    /**
     * @Author By:Wu Yongzhen
     * @Description 跳转至合作商管理页面
     * @Date 9:39 2018/3/16
     */
    @RequestMapping("skipCooperation")
    public String tt() {
        return "cooperativePartner";
    }

    /**
     * @Author By:Wu Yongzhen
     * @Description 合作商展示数据，包括列表，分页，条件查询，时间查询
     * @Date 9:40 2018/3/16
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map<String, Object> findById(
            @RequestParam(value = "parameter", defaultValue = "") String parameter,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<CooperativePartner> page = cooperativePartnerService.findList(parameter, pageNum, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pagestudentdata", page);
        map.put("number", page.getTotal());
        return map;
    }

    /**
     * @Author By:Wu Yongzhen
     * @Description 合作商申请存入数据库
     * @Date 9:41 2018/3/16
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map<String, Object> add(@RequestBody CooperativePartner parameters) {
        cooperativePartnerService.insertCooperativePartner(parameters);
        System.out.println(parameters);
        return null;
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
