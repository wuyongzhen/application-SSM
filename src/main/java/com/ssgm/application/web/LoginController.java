package com.ssgm.application.web;

import com.ssgm.application.entity.User;
import com.ssgm.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
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
     * @Description 登录管理平台，存入session
     * @Date 14:09 2018/3/15
     */
    @RequestMapping("login")
    @ResponseBody
    public String login(HttpServletRequest Request, User user) {
        User User = userService.selectByPrimaryKey(user);
        if (!isEmpty(User)) {
            Request.getSession().setAttribute("user", User);
            String url;
            //判断角色权限，返回响应的跳转页面
            switch (User.getRole()) {
                case 1:
                    url = "/cooperation/skipCooperation";//合作商管理员跳转
                    break;
                case 2:
                    url = "/partner/skipPartner";//合伙人管理员跳转
                    break;
                case 3:
                    url = "/customerFeedback/skipFeedback";//反馈系统管理员跳转
                    break;
                case 4:
                    url = "/announcement/skipAnnouncementPage";//公告系统管理员跳转
                    break;
                default:
                    url = "/login/skipLoginPage";//非正常权限
                    break;
            }
            return url;
        }
        return "error";
    }

    @RequestMapping("loginOut")
    public String loginOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //false代表：不创建session对象，只是从request中获取。
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "/login/skipLoginPage";
        }
        session.removeAttribute("user");
        return "/login/skipLoginPage";
    }
}
