package com.sq.modules.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.common.utils.PageUtils;
import com.sq.modules.customer.entity.AccountSecurityEntity;

import java.util.Map;

/**
 * 账户安全表
 *
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 10:26:11
 */
public interface AccountSecurityService extends IService<AccountSecurityEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

