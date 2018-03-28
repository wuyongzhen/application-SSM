package com.ssgm.application.dao;

import com.github.pagehelper.Page;
import com.ssgm.application.entity.Announcement;

public interface AnnouncementMapper {
    /**
     * @Author By:Wu Yongzhen
     * @Description 查询合作商列表
     * @Date 10:41 2018/3/13
     */
    Page<Announcement> findList(String parameter);

    int deleteByPrimaryKey(Integer id);

    int insert(Announcement record);

    int insertSelective(Announcement record);

    Announcement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Announcement record);

    int updateByPrimaryKey(Announcement record);
}