package com.ssgm.application.dao;

import com.github.pagehelper.Page;
import com.ssgm.application.entity.CooperativePartner;
import com.ssgm.application.entity.Partner;

/**
 * @Author By:Wu Yongzhen
 * @Description 合伙人CRUD
 * @Date 10:30 2018/3/16
 */
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