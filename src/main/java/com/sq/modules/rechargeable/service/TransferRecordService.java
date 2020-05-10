package com.sq.modules.rechargeable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.common.utils.PageUtils;
import com.sq.modules.rechargeable.entity.TransferRecordEntity;

import java.util.Map;

/**
 * 调拨记录表
 *
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-04-19 15:41:25
 */
public interface TransferRecordService extends IService<TransferRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

