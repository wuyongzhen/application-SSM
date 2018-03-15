package com.ssgm.application.web;

import com.ssgm.application.entity.User;
import com.ssgm.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import java.util.Map;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * @Author By: Wu Yongzhen
 * @Description 登录相关方法
 * @Data 17:34 2018/3/13
 * @Modified By:
 **/
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * @Author By:Wu Yongzhen
     * @Description 跳转至登录页面
     * @Date 14:09 2018/3/15
     */
    @RequestMapping("skipLoginPage")
    public String skipLoginPage() {
        return "login";
    }

    /**
     * @Author By:Wu Yongzhen
     * @Description 登录管理平台，存入cookie
     * @Date 14:09 2018/3/15
     */
    @RequestMapping("login")
    public Map login(HttpSession response, User user) {
        Cookie roleCookie = new Cookie("role", user.getRole().toString());//将角色信息添加入cookie中
        Cookie nameCookie = new Cookie("name", user.getRole().toString());//将用户名添加入cookie中
        roleCookie.setMaxAge(3600 * 24 * 3);//设置其生命周期
        nameCookie.setMaxAge(3600 * 24 * 3);//设置其生命周期
//        response.addCookie(roleCookie);
//        response.addCookie(nameCookie);
        if (isEmpty(this.userService.selectByPrimaryKey(user))){
            return null;
        }
        return null;
    }
}
