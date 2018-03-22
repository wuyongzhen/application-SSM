package com.ssgm.application.dao;

import com.github.pagehelper.Page;
import com.ssgm.application.entity.CustomerFeedback;
import com.ssgm.application.entity.Partner;

public interface PartnerMapper {
    /**
     * @Author By:Wu Yongzhen
     * @Description 查询合伙人列表
     * @Date 10:41 2018/3/13
     */
    Page<Partner> findList(String parameter);

    int deleteByPrimaryKey(Integer id);

    int insert(Partner record);

    int insertSelective(Partner record);

    Partner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Partner record);

    int updateByPrimaryKey(Partner record);
}