package com.ssgm.application.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author By: Wu Yongzhen
 * @Description 错误页面
 * @Data 13:30 2018/3/15
 * @Modified By:
 **/
@Controller
public class ErrorController {
    @RequestMapping("error404")
    public String error404() {
        return "404";
    }

    @RequestMapping("error500")
    public String error500() {
        return "500";
    }
}
