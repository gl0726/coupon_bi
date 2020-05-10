package com.sq.modules.customer.cache.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sq.common.exception.RRException;
import com.sq.common.utils.Constant;
import com.sq.common.utils.HttpKit;
import com.sq.common.utils.ServiceCode;
import com.sq.modules.customer.cache.CustomerCacheService;
import com.sq.modules.customer.cache.CustomerThirdpartyCacheService;
import com.sq.modules.customer.entity.CustomerThirdpartyEntity;
import com.sq.modules.customer.service.CustomerThirdpartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Classname customerThirdpartyCacheServiceImpl
 * @Description TODO
 * @Date 2019\4\3 0003 14:09
 * @Created by WenJunChi
 */
@Service
public class CustomerThirdpartyCacheServiceImpl implements CustomerThirdpartyCacheService {
    @Autowired
    private CustomerThirdpartyService customerThirdpartyService;


    @Override
    @Cacheable(value = "customerThirdpartyEntityByUserId", key = "#id", unless = "#result eq null")
    public List<CustomerThirdpartyEntity> getOneByUserId(Long id) {
        List<CustomerThirdpartyEntity> customer_id = customerThirdpartyService.list(new QueryWrapper<CustomerThirdpartyEntity>().eq(Constant.ENABLE, Constant.ENABLE_VALID).eq("customer_id", id));
        return customer_id;
    }

    @Cacheable(value = "customerThirdpartyEntityBySourceId", key = "#sourceId.toUpperCase()", unless = "#result eq null")
    @Override
    public CustomerThirdpartyEntity getOneBySourceId(String sourceId, Integer partyId) {
        CustomerThirdpartyEntity customerThirdpartyEntity = customerThirdpartyService.getOne(new QueryWrapper<CustomerThirdpartyEntity>().eq(Constant.ENABLE, Constant.ENABLE_VALID)
                .eq("party_id", partyId)
                .eq("source_id", sourceId));
        return customerThirdpartyEntity;
    }


    @CachePut(value = "CustomerThirdpartyEntity", key = "#customerThirdpartyEntity.id", unless = "#customerEntity eq null")
    @Override
    public CustomerThirdpartyEntity save(CustomerThirdpartyEntity customerThirdpartyEntity) {
        //先查询
        //先查询
        int count = this.customerThirdpartyService.count(new QueryWrapper<CustomerThirdpartyEntity>().
                eq(Constant.ENABLE, Constant.ENABLE_VALID).eq("source_id", customerThirdpartyEntity.getSourceId()).
                eq(Constant.ENABLE, Constant.ENABLE_VALID).eq("party_id", customerThirdpartyEntity.getPartyId()));
        if (count > 0) {
            throw new RRException("第三方信息已经存在{}", ServiceCode.DB_SERVICE_DATA_OVERVALUED.getCode());
        }
        boolean save = this.customerThirdpartyService.save(customerThirdpartyEntity);
        if (!save) {
            throw new RRException(ServiceCode.DB_SERVICE_OPTIMISATIC_LOCK);
        }
        return customerThirdpartyEntity;
    }
}
