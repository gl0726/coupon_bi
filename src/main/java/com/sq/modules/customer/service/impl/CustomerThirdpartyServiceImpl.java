package com.sq.modules.customer.service.impl;

import com.sq.common.exception.RRException;
import com.sq.common.utils.Constant;
import com.sq.common.utils.ServiceCode;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.common.utils.PageUtils;
import com.sq.common.utils.Query;

import com.sq.modules.customer.dao.CustomerThirdpartyDao;
import com.sq.modules.customer.entity.CustomerThirdpartyEntity;
import com.sq.modules.customer.service.CustomerThirdpartyService;


@Service("customerThirdpartyService")
public class CustomerThirdpartyServiceImpl extends ServiceImpl<CustomerThirdpartyDao, CustomerThirdpartyEntity> implements CustomerThirdpartyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CustomerThirdpartyEntity> page = this.page(
                new Query<CustomerThirdpartyEntity>().getPage(params),
                new QueryWrapper<CustomerThirdpartyEntity>()
        );

        return new PageUtils(page);
    }


}