package com.ssgm.application.dao;

import com.ssgm.application.entity.User;

public interface UserMapper {
    /**
     * @Author By:Wu Yongzhen
     * @Description 根据账号密码验证登录信息
     * @Date 9:39 2018/3/16
     */
    User selectByParameters(User user);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}