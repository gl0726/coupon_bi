package com.sq.modules.customer.dao;

import com.sq.modules.customer.entity.CustomerSigninEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author lq
 * @date 2019\4\17 0017 14:31
 */
@Mapper
public interface CustomerSigninDao extends BaseMapper<CustomerSigninEntity> {

   BigDecimal findBonusesTotal(@Param("customerId") Long customerId);
	
}
