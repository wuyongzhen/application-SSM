package com.ssgm.application.dao;

import com.github.pagehelper.Page;
import com.ssgm.application.entity.CustomerFeedback;

public interface CustomerFeedbackMapper {
    /**
     * @Author By:Wu Yongzhen
     * @Description 查询反馈建议列表
     * @Date 10:41 2018/3/13
     */
    Page<CustomerFeedback> findList(String parameter);

    int deleteByPrimaryKey(Integer id);

    int insert(CustomerFeedback record);

    int insertSelective(CustomerFeedback record);

    CustomerFeedback selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerFeedback record);

    int updateByPrimaryKey(CustomerFeedback record);
}