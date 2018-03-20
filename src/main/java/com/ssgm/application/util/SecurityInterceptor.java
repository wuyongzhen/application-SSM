package com.ssgm.application.util;

import com.ssgm.application.entity.User;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author By: Wu Yongzhen
 * @Description 拦截器
 * @Data 15:08 2018/3/16
 * @Modified By:
 **/

public class SecurityInterceptor implements HandlerInterceptor {
    public final static String SEESION_MEMBER = "user";

    /**
     * @Author By:Wu Yongzhen
     * @Description 拦截除登录以外所有请求
     * @Date 15:21 2018/3/16
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

          /*
        解决vue 前后台跨域问题
         */
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Credentials", "true"); //是否允许浏览器携带用户身份信息（cookie）

        String contextPath = request.getContextPath();
        String url = request.getServletPath().toString();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SEESION_MEMBER);
        if (StringUtils.isEmpty(user)) {
            //被拦截，重定向到login界面
            response.sendRedirect("/login/skipLoginPage");
            request.setAttribute("msg", "登录超时，请重新登录！");
            return false;
        }
        boolean role=true;
        switch (user.getRole()) {
            case 1:
                Object concat = url.concat("cooperation");
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
//        String result = url.substring().substring(strStart.length());


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}