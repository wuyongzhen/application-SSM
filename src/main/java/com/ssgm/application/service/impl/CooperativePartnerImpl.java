package com.ssgm.application.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ssgm.application.dao.CooperativePartnerMapper;
import com.ssgm.application.entity.CooperativePartner;
import com.ssgm.application.service.CooperativePartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * CooperativePartnerService接口的实现
 *
 * @author wuyongzhen
 * @Date 2018-3-11 20:00
 */
@Transactional
@Service("cooperativePartnerService")
public class CooperativePartnerImpl implements CooperativePartnerService {
    /**
     * 因为没有在spring的文件中显式的声明userService实现类，直接通过getBean得到 userService 会找不到
     * 需要显式配置 @Service ("cooperativePartnerService")，指定bean的名称
     * 相当与<bean id="cooperativePartnerService" class="com.ssgm.application.service.impl.cooperativePartnerService"></bean>
     */
    @Autowired
    private CooperativePartnerMapper cooperativePartnerMapper;

    public CooperativePartner getById(Integer id) {
        return this.cooperativePartnerMapper.selectByPrimaryKey(id);
    }

    public Page<CooperativePartner> findList(String parameter, int pageNum, int pageSize) {
        Page<CooperativePartner> page = PageHelper.startPage(pageNum, pageSize);
        this.cooperativePartnerMapper.findList(parameter);
        return page;
    }

    /**
     * @Author By:Wu Yongzhen
     * @Description
     * @Date 13:49 2018/3/14
     */
    public int insertCooperativePartner(CooperativePartner CooperativePartner) {
        CooperativePartner.setCreationTime(new Date());
        CooperativePartner.setDelState((byte) 0);
        return this.cooperativePartnerMapper.insert(CooperativePartner);
    }

    public void saveRemark(CooperativePartner parameters) {
        this.cooperativePartnerMapper.updateByPrimaryKeySelective(parameters);
    }
}
