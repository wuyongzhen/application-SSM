package com.ssgm.application.service.impl;

import com.ssgm.application.entity.User;
import com.ssgm.application.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author By: Wu Yongzhen
 * @Description 用户登录实现类
 * @Data 15:13 2018/3/15
 * @Modified By:
 **/
@Transactional
@Service("userService")
public class UserImpl implements UserService {
    public User selectByPrimaryKey(User user) {
        return null;
    }
}
