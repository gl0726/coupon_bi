package com.sq.modules.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.common.utils.PageUtils;
import com.sq.modules.customer.entity.CustomerLevelEntity;

import java.util.Map;

/**
 * 会员等级表
 *
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 10:26:11
 */
public interface CustomerLevelService extends IService<CustomerLevelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

