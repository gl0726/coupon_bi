package com.sq.modules.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.common.utils.PageUtils;
import com.sq.modules.customer.entity.AccountConsumeEntity;

import java.util.List;
import java.util.Map;

/**
 * 抵扣记录
 *
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 15:26:53
 */
public interface AccountConsumeService extends IService<AccountConsumeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    int deduction(AccountConsumeEntity accountConsume);

    PageUtils queryCouponList(Map<String, Object> params);
}

