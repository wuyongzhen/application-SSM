package com.ssgm.application.util;

import com.ssgm.application.entity.User;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author By: Wu Yongzhen
 * @Description 拦截器
 * @Data 15:08 2018/3/16
 * @Modified By:
 **/

public class SecurityInterceptor implements HandlerInterceptor {
    private List<String> exceptUrls;

    public List<String> getExceptUrls() {
        return exceptUrls;
    }

    public void setExceptUrls(List<String> exceptUrls) {
        this.exceptUrls = exceptUrls;
    }

    public final static String SEESION_MEMBER = "user";

    /**
     * @Author By:Wu Yongzhen
     * @Description 拦截除登录以外所有请求
     * @Date 15:21 2018/3/16
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String requestUri = request.getRequestURI();
        if (requestUri.startsWith(request.getContextPath())) {
            requestUri = requestUri.substring(request.getContextPath().length(), requestUri.length());
        }
        //放行exceptUrls中配置的url
        for (String url : exceptUrls
                ) {
            if (url.equals(requestUri)) {
                return true;
            } else if (url.endsWith("/**")) {
                if (requestUri.startsWith(url.substring(0, url.length() - 3))) {
                    return true;
                }
            } else if (requestUri.startsWith(url)) {
                return true;
            }
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SEESION_MEMBER);
        if (StringUtils.isEmpty(user)) {
            //被拦截，重定向到login界面
            response.sendRedirect("/login/skipLoginPage");
            request.setAttribute("msg", "登录超时，请重新登录！");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}