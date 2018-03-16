package com.ssgm.application.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ssgm.application.dao.PartnerMapper;
import com.ssgm.application.entity.Partner;
import com.ssgm.application.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author By: Wu Yongzhen
 * @Description 合伙人操作实现类
 * @Data 10:34 2018/3/16
 * @Modified By:
 **/
@Transactional
@Service("partnerService")
public class PartnerImpl implements PartnerService {
    @Autowired
    private PartnerMapper PartnerMapper;

    public Partner getById(Integer id) {
        return this.PartnerMapper.selectByPrimaryKey(id);
    }

    public Page<Partner> findList(String parameter, int pageNum, int pageSize) {
        Page<Partner> page = PageHelper.startPage(pageNum, pageSize);
        this.PartnerMapper.findList(parameter);
        return page;
    }

    /**
     * @Author By:Wu Yongzhen
     * @Description
     * @Date 13:49 2018/3/14
     */
    public int insertPartner(Partner Partner) {
        Partner.setCreateTime(new Date());
        Partner.setDel((byte) 0);
        return this.PartnerMapper.insert(Partner);
    }

    public void saveRemark(Partner parameters) {
        this.PartnerMapper.updateByPrimaryKeySelective(parameters);
    }
}
