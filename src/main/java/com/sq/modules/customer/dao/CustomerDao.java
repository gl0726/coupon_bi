package com.sq.modules.customer.dao;

import com.sq.modules.customer.entity.CustomerEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员基础信息表
 * 
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 10:26:11
 */
@Mapper
public interface CustomerDao extends BaseMapper<CustomerEntity> {
	
}
