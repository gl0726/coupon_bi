package com.sq.modules.customer.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sq.modules.customer.entity.AccountConsumeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sq.modules.customer.entity.vo.AccountConsumeVo;
import com.sq.modules.customer.entity.vo.ConsumeDetailVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 抵扣记录
 * 
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 15:26:53
 */
@Mapper
public interface AccountConsumeDao extends BaseMapper<AccountConsumeEntity> {

    IPage<AccountConsumeVo> selectPageByVo(IPage page, @Param("customerId") String customerId);

    List<ConsumeDetailVo> selectConsumeDatail(@Param("dissipateDate") String dissipateDate, @Param("customerId") String customerId);
	
}
