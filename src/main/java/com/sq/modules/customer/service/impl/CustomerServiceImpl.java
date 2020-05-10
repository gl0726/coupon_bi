package com.sq.modules.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sq.common.exception.RRException;
import com.sq.common.utils.Constant;
import com.sq.common.utils.ServiceCode;
import com.sq.modules.customer.cache.CustomerThirdpartyCacheService;
import com.sq.modules.customer.entity.CustomerThirdpartyEntity;
import com.sq.modules.customer.entity.CustomerWealthEntity;
import com.sq.modules.customer.service.CustomerWealthService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.common.utils.PageUtils;
import com.sq.common.utils.Query;

import com.sq.modules.customer.dao.CustomerDao;
import com.sq.modules.customer.entity.CustomerEntity;
import com.sq.modules.customer.service.CustomerService;


@Service("customerService")
public class CustomerServiceImpl extends ServiceImpl<CustomerDao, CustomerEntity> implements CustomerService {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerWealthService customerWealthService;
    @Autowired
    CustomerThirdpartyCacheService customerThirdpartyCacheService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CustomerEntity> page = this.page(
                new Query<CustomerEntity>().getPage(params),
                new QueryWrapper<CustomerEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public CustomerEntity saveCustomer(CustomerEntity customerEntity) {
        boolean save = super.save(customerEntity);
        if (!save) {
            throw new RRException(ServiceCode.DB_SERVICE_OPTIMISATIC_LOCK);
        }
        /**
         * 初始化个人账户信息
         *
         */
        initCustomerWealth(customerEntity.getId());
        if (customerEntity.getPartyId() != null && !StringUtils.isBlank(customerEntity.getOpenId())) {
            CustomerThirdpartyEntity customerThirdpartyEntity = new CustomerThirdpartyEntity();
            customerThirdpartyEntity.setCustomerId(customerEntity.getId());
            customerThirdpartyEntity.setEnable(Constant.ENABLE_VALID);
            customerThirdpartyEntity.setPartyId(customerEntity.getPartyId());
            customerThirdpartyEntity.setSourceId(customerEntity.getOpenId());//平台sourceId，
            //**保存第三方信息
            this.customerThirdpartyCacheService.save(customerThirdpartyEntity);
        }

        return customerEntity;
    }

    /**
     * 更新头像
     * @param url
     * @param customerId
     * @return
     */
    @Override
    public Boolean resetHeadPortrait(String url,Long customerId) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customerId);
        customerEntity.setHeadPortrait(url);
        return customerService.update(customerEntity,new QueryWrapper<>());
    }

    /**
     * 更改昵称
     * @param customerId
     * @param nickname
     * @return
     */
    @Override
    public Boolean updateNickname(Long customerId, String nickname) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customerId);
        customerEntity.setNickname(nickname);
        return customerService.update(customerEntity,new QueryWrapper<>());
    }


    private void initCustomerWealth(Long id) {
        CustomerWealthEntity customerWealthEntity = new CustomerWealthEntity();
        customerWealthEntity.setAmount(BigDecimal.ZERO);
        customerWealthEntity.setEnable(Constant.ENABLE_VALID);
        customerWealthEntity.setCreateTime(new Date());
        customerWealthEntity.setPoints(BigDecimal.ZERO);
        customerWealthEntity.setCustomerId(id);
        customerWealthEntity.setRemarks("创建用户-初始化账户信息");
        boolean save = customerWealthService.save(customerWealthEntity);
        if (!save) {
            throw  new RRException(ServiceCode.DB_SERVICE_CREATE_FAILURE);
        }

    }
}