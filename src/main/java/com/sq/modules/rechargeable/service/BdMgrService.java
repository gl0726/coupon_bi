package com.sq.modules.rechargeable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.common.utils.PageUtils;
import com.sq.modules.rechargeable.entity.BdMgrEntity;

import java.util.Map;

/**
 * BD管理
 *
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 15:20:45
 */
public interface BdMgrService extends IService<BdMgrEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

