package com.sq.modules.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.common.utils.PageUtils;
import com.sq.modules.customer.entity.CustomerSigninEntity;
import com.sq.modules.customer.entity.vo.SigninStatisticsVo;


import java.util.Map;

/**
 * @author lq
 * @date 2019\4\17 0017 14:31
 */
public interface CustomerSigninService extends IService<CustomerSigninEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Boolean saveSigin(Long customerId);

    SigninStatisticsVo findSigninStatistics(Long customerId);
}

