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

    @RequestMapping("skipCooperation")
    public String tt() {
        return "cooperativePartner";
    }

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


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map<String, Object> add(@RequestBody CooperativePartner parameters) {
        cooperativePartnerService.insertCooperativePartner(parameters);
        System.out.println(parameters);
        return null;
    }

    @RequestMapping(value = "/saveRemark", method = RequestMethod.GET)
    public void saveRemark(CooperativePartner parameters) {
        cooperativePartnerService.saveRemark(parameters);
    }
}
