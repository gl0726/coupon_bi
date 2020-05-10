package com.sq.modules.customer.cache.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sq.common.exception.RRException;
import com.sq.common.utils.Constant;
import com.sq.common.utils.R;
import com.sq.common.utils.RedisUtils;
import com.sq.common.utils.ServiceCode;
import com.sq.modules.customer.cache.CustomerCacheService;
import com.sq.modules.customer.cache.CustomerThirdpartyCacheService;
import com.sq.modules.customer.entity.CustomerEntity;
import com.sq.modules.customer.entity.CustomerThirdpartyEntity;
import com.sq.modules.customer.service.CustomerService;
import com.sq.modules.customer.service.CustomerThirdpartyService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Classname 基础数据缓存到redis
 * @Description
 * @Date 2019\4\3 0003 8:53
 * @Created by WenJunChi
 */

@Service
public class CustomerCacheImpl implements CustomerCacheService {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerThirdpartyService customerThirdpartyService;
    @Autowired
    CustomerThirdpartyCacheService customerThirdpartyCacheService;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    RedisTemplate redisTemplate;
    private final static String CACHE_KEY = "customer";
    final static String REDIS_KEY_BY_MOBILE = "customerByMobile::";
    final static String REDIS_KEY_BY_SOURCEID = "customerBySourceId::";

    @Override
    @CachePut(value = CACHE_KEY, key = "#customerEntity.id", unless = "#customerEntity eq null")
    public CustomerEntity save(CustomerEntity customerEntity) {
        if (StringUtils.isBlank(customerEntity.getMobile()) || null == (customerEntity.getId())) {
            throw new RRException(ServiceCode.DB_PARAM_NULL);
        }
        String salt = RandomStringUtils.randomAlphanumeric(20);
        customerEntity.setPassword(new Sha256Hash(customerEntity.getPassword(), salt).toHex());
        customerEntity.setSalt(salt);
        customerEntity.setEnable(Constant.ENABLE_VALID);
        customerEntity.setCreateTime(new Date());
        customerEntity.setUpdateTime(new Date());
        customerService.saveCustomer(customerEntity);
        return customerEntity;
    }

    @Override
    @CachePut(value = CACHE_KEY, key = "#customerEntity.id", unless = "#customerEntity eq null")
    public CustomerEntity update(CustomerEntity customerEntity) {
        customerEntity.setUpdateTime(new Date());
        boolean save = customerService.update(customerEntity, new QueryWrapper<CustomerEntity>().eq("id", customerEntity.getId()));
        if (save) {
            return customerEntity;
        }
        throw new RRException(ServiceCode.DB_SERVICE_OPTIMISATIC_LOCK);


    }

    @Override
    @CacheEvict(value = CACHE_KEY, key = "#customerEntity.id", condition = "#result eq null")
    public CustomerEntity delete(CustomerEntity customerEntity) {
        CustomerEntity queryCustomer = selectOneById(customerEntity.getId());
        if (queryCustomer == null) {
            throw new RRException(ServiceCode.DB_SERVICE_SPEAKER_NOT_EXISTED);
        }
        queryCustomer.setUpdateTime(new Date());
        queryCustomer.setEnable(Constant.ENABLE_INVALID);
        return this.update(customerEntity);
    }

    @Override
    @Cacheable(value = CACHE_KEY, key = "#id", unless = "#result eq null")
    public CustomerEntity selectOneById(Long id) {
        CustomerEntity queryCustomer = customerService.getOne(new QueryWrapper<CustomerEntity>().eq(Constant.ENABLE, Constant.ENABLE_VALID).eq("id", id));
        List<CustomerThirdpartyEntity> customerThirdpartyEntity = this.customerThirdpartyService.list(new QueryWrapper<CustomerThirdpartyEntity>().eq("customer_id", queryCustomer.getId()).eq(Constant.ENABLE, Constant.ENABLE_VALID));
        queryCustomer.setCustomerThirdpartyEntitys(customerThirdpartyEntity);
        return queryCustomer;
    }

    @Override
    public List<CustomerEntity> selectList() {
        return customerService.list(new QueryWrapper<CustomerEntity>().eq(Constant.ENABLE, Constant.ENABLE_VALID));
    }

    @Override
    @Cacheable(value = "customerBySourceId", key = "#sourceId", unless = "#result eq null")
    public CustomerEntity getBySourceId(Integer partyId, String sourceId) {
        CustomerThirdpartyEntity customerThirdparty = this.customerThirdpartyCacheService.getOneBySourceId(sourceId, partyId);
        if (customerThirdparty == null) {
            throw new RRException(ServiceCode.DB_SERVICE_SPEAKER_NOT_EXISTED);
        }
        CustomerEntity customerEntity = this.selectOneById(customerThirdparty.getCustomerId());

        return customerEntity;

    }

    @Cacheable(value = "customerByMobile", key = "#mobile", unless = "#result eq null")
    @Override
    public CustomerEntity getByMobile(String mobile) {
        CustomerEntity customer = this.customerService.getOne(new QueryWrapper<CustomerEntity>().eq("mobile", mobile).eq(Constant.ENABLE, Constant.ENABLE_VALID));
        if (customer == null) {
            throw new RRException(ServiceCode.DB_SERVICE_SPEAKER_NOT_EXISTED);
        }
        List<CustomerThirdpartyEntity> customerThirdpartyEntityList = this.customerThirdpartyCacheService.getOneByUserId(customer.getId());
        customer.setCustomerThirdpartyEntitys(customerThirdpartyEntityList);
        return customer;
    }

    @Override
    public CustomerEntity resetPassword(CustomerEntity customer) {
        /**
         * 因考虑缓存问题、所有修改密码时，必须清除Bymobile、openId的缓存
         */
        CustomerEntity byMobile = getByMobile(customer.getMobile());
        byMobile.setPassword(new Sha256Hash(customer.getPassword(), byMobile.getSalt()).toHex());
        this.update(byMobile);
        redisTemplate.delete(REDIS_KEY_BY_MOBILE + customer.getMobile());
        redisTemplate.delete(REDIS_KEY_BY_SOURCEID + customer.getOpenId());
        return byMobile;
    }
}
