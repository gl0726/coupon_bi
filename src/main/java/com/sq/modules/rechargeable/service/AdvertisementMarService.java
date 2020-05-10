package com.sq.modules.rechargeable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.common.utils.PageUtils;
import com.sq.modules.rechargeable.entity.AdvertisementMarEntity;

import java.util.Map;

/**
 * 广告位管理
 *
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 15:20:45
 */
public interface AdvertisementMarService extends IService<AdvertisementMarEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean save(AdvertisementMarEntity entity,Long userId);
}

