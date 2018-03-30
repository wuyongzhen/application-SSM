package com.ssgm.application.service;

import com.github.pagehelper.Page;
import com.ssgm.application.entity.Announcement;

/**
 * @Author By: Wu Yongzhen
 * @Description 公告
 * @Data 11:14 2018/3/28
 * @Modified By:
 **/
public interface AnnouncementService {
    /**
     * @Author By:Wu Yongzhen
     * @Description 查询公告列表
     * @Date 14:12 2018/3/12
     */
    Page<Announcement> findList(String parameter, int pageNum, int pageSize);

    /**
     * @Author By:Wu Yongzhen
     * @Description 添加公告
     * @Date 15:55 2018/3/14
     */
    int insertAnnouncement(Announcement Announcement);

    /**
     * @Author By:Wu Yongzhen
     * @Description 删除公告
     * @Date 11:17 2018/3/28
     */
    int delAnnouncement(Announcement announcement);
}
