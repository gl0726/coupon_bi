package com.sq.modules.rechargeable.service.impl;

import com.sq.common.utils.Constant;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.common.utils.PageUtils;
import com.sq.common.utils.Query;

import com.sq.modules.rechargeable.dao.AdvertisementMarDao;
import com.sq.modules.rechargeable.entity.AdvertisementMarEntity;
import com.sq.modules.rechargeable.service.AdvertisementMarService;


@Service("advertisementMarService")
public class AdvertisementMarServiceImpl extends ServiceImpl<AdvertisementMarDao, AdvertisementMarEntity> implements AdvertisementMarService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AdvertisementMarEntity> page = this.page(
                new Query<AdvertisementMarEntity>().getPage(params),
                new QueryWrapper<AdvertisementMarEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public boolean save(AdvertisementMarEntity entity,Long userId){
        entity.setCreateTime(new Date());
        entity.setCreateId(userId);
        entity.setEnable(Constant.ENABLE_VALID);
        return super.save(entity);
    }

}