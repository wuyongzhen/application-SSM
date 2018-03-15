package com.ssgm.application.dao;

import com.github.pagehelper.Page;
import com.ssgm.application.entity.CooperativePartner;

import java.util.List;

public interface CooperativePartnerMapper {
    /**
     * @Author By:Wu Yongzhen
     * @Description 查询合作商列表
     * @Date 10:41 2018/3/13
     */
    Page<CooperativePartner> findList(String parameter);

    int deleteByPrimaryKey(Integer id);

    int insert(CooperativePartner record);

    int insertSelective(CooperativePartner record);

    CooperativePartner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CooperativePartner record);

    int updateByPrimaryKey(CooperativePartner record);
}