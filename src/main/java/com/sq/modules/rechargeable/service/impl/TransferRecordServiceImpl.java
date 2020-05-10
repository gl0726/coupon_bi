package com.sq.modules.rechargeable.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.common.utils.PageUtils;
import com.sq.common.utils.Query;

import com.sq.modules.rechargeable.dao.TransferRecordDao;
import com.sq.modules.rechargeable.entity.TransferRecordEntity;
import com.sq.modules.rechargeable.service.TransferRecordService;


@Service("transferRecordService")
public class TransferRecordServiceImpl extends ServiceImpl<TransferRecordDao, TransferRecordEntity> implements TransferRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TransferRecordEntity> page = this.page(
                new Query<TransferRecordEntity>().getPage(params),
                new QueryWrapper<TransferRecordEntity>()
        );

        return new PageUtils(page);
    }

}