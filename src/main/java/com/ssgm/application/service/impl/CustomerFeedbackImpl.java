package com.ssgm.application.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ssgm.application.dao.CustomerFeedbackMapper;
import com.ssgm.application.entity.CustomerFeedback;
import com.ssgm.application.service.CustomerFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * CustomerFeedbackService接口的实现
 *
 * @author wuyongzhen
 * @Date 2018-3-11 20:00
 */
@Transactional
@Service("customerFeedbackService")
public class CustomerFeedbackImpl implements CustomerFeedbackService {
    @Autowired
    private CustomerFeedbackMapper customerFeedbackMapper;

    public Page<CustomerFeedback> findList(String parameter, int pageNum, int pageSize) {
        Page<CustomerFeedback> page = PageHelper.startPage(pageNum, pageSize);
        this.customerFeedbackMapper.findList(parameter);
        return page;
    }

    /**
     * @Author By:Wu Yongzhen
     * @Description
     * @Date 13:49 2018/3/14
     */
    public int insertCustomerFeedback(CustomerFeedback customerFeedback) {
        customerFeedback.setCreationTime(new Date());
        return this.customerFeedbackMapper.insert(customerFeedback);
    }

    public void saveRemark(CustomerFeedback parameters) {
        this.customerFeedbackMapper.updateByPrimaryKeySelective(parameters);
    }
}
