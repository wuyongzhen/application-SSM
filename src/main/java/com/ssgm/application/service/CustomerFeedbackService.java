package com.ssgm.application.service;

import com.github.pagehelper.Page;
import com.ssgm.application.entity.CustomerFeedback;

/**
 * CustomerFeedbackService层接口
 *
 * @author wuyongzhen
 * @Date 2018-3-11 20:00
 */
public interface CustomerFeedbackService {

    /**
     * @Author By:Wu Yongzhen
     * @Description 查询反馈系统列表
     * @Date 14:12 2018/3/12
     */
    Page<CustomerFeedback> findList(String parameter, int pageNum, int pageSize);

    /**
    * @Author By:Wu Yongzhen
    * @Description 添加反馈系统数据
    * @Date 15:55 2018/3/14
    */
    int insertCustomerFeedback(CustomerFeedback CustomerFeedback);

    /**
    * @Author By:Wu Yongzhen
    * @Description 保存反馈系统备注信息
    * @Date 15:55 2018/3/14
    */
    void saveRemark(CustomerFeedback parameters);
}
