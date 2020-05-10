package com.sq.modules.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.common.utils.PageUtils;
import com.sq.modules.customer.entity.CustomerThirdpartyEntity;

import java.util.Map;

/**
 * 第三方平台信息
 *
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 10:26:11
 */
public interface CustomerThirdpartyService extends IService<CustomerThirdpartyEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

