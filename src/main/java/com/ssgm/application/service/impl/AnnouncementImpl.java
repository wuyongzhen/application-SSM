package com.ssgm.application.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ssgm.application.dao.AnnouncementMapper;
import com.ssgm.application.entity.Announcement;
import com.ssgm.application.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author By: Wu Yongzhen
 * @Description 公告
 * @Data 11:18 2018/3/28
 * @Modified By:
 **/
@Transactional
@Service("announcementService")
public class AnnouncementImpl implements AnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public Page<Announcement> findList(String parameter, int pageNum, int pageSize) {
        Page<Announcement> page = PageHelper.startPage(pageNum, pageSize);
        this.announcementMapper.findList(parameter);
        return page;
    }

    @Override
    public int insertAnnouncement(Announcement Announcement) {
        return announcementMapper.insert(Announcement);
    }


    @Override
    public int delAnnouncement(Announcement announcement) {
        return announcementMapper.updateByPrimaryKeySelective(announcement);
    }
}
