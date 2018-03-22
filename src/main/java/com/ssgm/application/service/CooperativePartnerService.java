package com.ssgm.application.service;

import com.github.pagehelper.Page;
import com.ssgm.application.entity.CooperativePartner;

import java.util.List;

/**
 * CooperativePartnerService层接口
 *
 * @author wuyongzhen
 * @Date 2018-3-11 20:00
 */
public interface CooperativePartnerService {
    /**
     * @Author By:Wu Yongzhen
     * @Description 查询合作商列表
     * @Date 14:12 2018/3/12
     */
    Page<CooperativePartner> findList(String parameter, int pageNum, int pageSize);

    /**
    * @Author By:Wu Yongzhen
    * @Description 添加合作商数据
    * @Date 15:55 2018/3/14
    */
    int insertCooperativePartner(CooperativePartner CooperativePartner);

    /**
    * @Author By:Wu Yongzhen
    * @Description 保存合作商备注信息
    * @Date 15:55 2018/3/14
    */
    void saveRemark(CooperativePartner parameters);
}
