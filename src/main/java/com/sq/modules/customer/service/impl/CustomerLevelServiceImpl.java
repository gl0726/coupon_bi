package com.sq.modules.customer.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.common.utils.PageUtils;
import com.sq.common.utils.Query;

import com.sq.modules.customer.dao.CustomerLevelDao;
import com.sq.modules.customer.entity.CustomerLevelEntity;
import com.sq.modules.customer.service.CustomerLevelService;


@Service("customerLevelService")
public class CustomerLevelServiceImpl extends ServiceImpl<CustomerLevelDao, CustomerLevelEntity> implements CustomerLevelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CustomerLevelEntity> page = this.page(
                new Query<CustomerLevelEntity>().getPage(params),
                new QueryWrapper<CustomerLevelEntity>()
        );

        return new PageUtils(page);
    }

}