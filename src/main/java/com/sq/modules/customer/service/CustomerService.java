package com.sq.modules.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.common.utils.PageUtils;
import com.sq.modules.customer.entity.CustomerEntity;

import java.util.Map;

/**
 * 会员基础信息表
 *
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 10:26:11
 */
public interface CustomerService extends IService<CustomerEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存用户并放回
     *
     * @param customerEntity
     * @return
     */
    CustomerEntity saveCustomer(CustomerEntity customerEntity);


    Boolean resetHeadPortrait(String url,Long customerId);

    Boolean updateNickname(Long customerId,String nickname);
}

