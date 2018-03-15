package com.ssgm.application.service;

import com.ssgm.application.entity.User;

/**
 * @Author By: Wu Yongzhen
 * @Description 用户登录Service
 * @Data 15:10 2018/3/15
 * @Modified By:
 **/
public interface UserService {
    /**
     * @Author By:Wu Yongzhen
     * @Description 根据账号密码验证信息
     * @Date 15:12 2018/3/15
     */
    User selectByPrimaryKey(User user);
}
