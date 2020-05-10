package com.sq.modules.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.common.utils.PageUtils;
import com.sq.modules.customer.entity.AccountRechargeEntity;

import java.util.Map;

/**
 * 充值记录
 *
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 10:26:11
 */
public interface AccountRechargeService extends IService<AccountRechargeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    int recharge(String cardNo, String pwd, String customerId);
}

