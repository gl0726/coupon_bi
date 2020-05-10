package com.sq.modules.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.common.utils.PageUtils;
import com.sq.modules.customer.entity.CustomerWealthEntity;

import java.util.Map;

/**
 * 客户财富
 *
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 10:26:11
 */
public interface CustomerWealthService extends IService<CustomerWealthEntity> {

    PageUtils queryPage(Map<String, Object> params);

    CustomerWealthEntity getByCustomerId(Long customerId);

    Boolean update(CustomerWealthEntity customerWealthEntity);
}

