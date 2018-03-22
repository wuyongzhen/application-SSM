package com.ssgm.application.service;

import com.github.pagehelper.Page;
import com.ssgm.application.entity.Partner;

/**
 * @Author By: Wu Yongzhen
 * @Description 合伙人interface
 * @Data 10:31 2018/3/16
 * @Modified By:
 **/
public interface PartnerService {
    /**
     * @Author By:Wu Yongzhen
     * @Description 查询合伙人列表
     * @Date 10:32 2018/3/16
     */
    Page<Partner> findList(String parameter, int pageNum, int pageSize);


    /**
     * @Author By:Wu Yongzhen
     * @Description 添加合伙人数据
     * @Date 15:55 2018/3/14
     */
    int insertPartner(Partner Partner);

    /**
     * @Author By:Wu Yongzhen
     * @Description 保存备注信息
     * @Date 15:55 2018/3/14
     */
    void saveRemark(Partner Partner);
}
