package com.sq.modules.rechargeable.dao;

import com.sq.modules.rechargeable.entity.TransferRecordEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 调拨记录表
 * 
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-04-19 15:41:25
 */
@Mapper
public interface TransferRecordDao extends BaseMapper<TransferRecordEntity> {
	
}
