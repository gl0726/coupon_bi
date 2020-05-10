package com.sq.modules.customer.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.common.utils.PageUtils;
import com.sq.common.utils.Query;

import com.sq.modules.customer.dao.CustomerWealthDao;
import com.sq.modules.customer.entity.CustomerWealthEntity;
import com.sq.modules.customer.service.CustomerWealthService;


@Service("customerWealthService")
public class CustomerWealthServiceImpl extends ServiceImpl<CustomerWealthDao, CustomerWealthEntity> implements CustomerWealthService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CustomerWealthEntity> page = this.page(
                new Query<CustomerWealthEntity>().getPage(params),
                new QueryWrapper<CustomerWealthEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public CustomerWealthEntity getByCustomerId(Long customerId) {
        return getOne(new QueryWrapper<CustomerWealthEntity>().eq("customer_id", customerId));
    }

    @Override
    public Boolean update(CustomerWealthEntity customerWealthEntity) {
        return update(customerWealthEntity, new QueryWrapper<CustomerWealthEntity>()
                .eq("id", customerWealthEntity.getId()));
    }

}