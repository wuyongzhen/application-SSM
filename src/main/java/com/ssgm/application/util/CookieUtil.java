package com.ssgm.application.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author By: Wu Yongzhen
 * @Description 对Cookie操作的工具类
 * @Data 14:07 2018/3/15
 * @Modified By:
 **/
public class CookieUtil {

    //从cookie数组中获取指定key的cookie
    public static Cookie getCookieFromCookies(Cookie[] cookies, String target) {
        Cookie cookie = null;
        for (Cookie c : cookies) {
            if (c.getName().equals(target)) {
                cookie = c;
            }
        }
        return cookie;
    }

    //删除所有cookie
    public static void removeAllCookies(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            cookie.setPath("/");
            resp.addCookie(cookie);
        }
    }

    //根据cookie名称移除cookie
    public static void removeCookieByName(String cookie_name, HttpServletRequest request, HttpServletResponse resp) {
        Cookie cookie;
        Cookie[] cookies;
        // 获取当前域名下的cookies，是一个数组
        cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if ((cookie.getName()).compareTo(cookie_name) == 0) {
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                }
            }
        }
    }


}
