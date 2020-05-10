package com.sq.modules.rechargeable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.common.utils.PageUtils;
import com.sq.modules.rechargeable.entity.CompanyEntity;

import java.util.Map;

/**
 * 企业表
 *
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-04-17 11:04:18
 */
public interface CompanyService extends IService<CompanyEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils  queryPageCondition(Map<String, Object> params);

}

