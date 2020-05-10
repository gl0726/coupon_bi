package com.sq.modules.customer.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sq.modules.customer.entity.AccountRechargeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sq.modules.customer.entity.vo.AccountRechargeVo;
import com.sq.modules.customer.entity.vo.RechargeDetailVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 充值记录
 *
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 10:26:11
 */
@Mapper
public interface AccountRechargeDao extends BaseMapper<AccountRechargeEntity> {
    IPage<AccountRechargeVo> selectPageByVo(IPage page, @Param("wealthId") Long wealthId);

    List<RechargeDetailVo> selectRechargeDatail(@Param("rechargeTime") String rechargeTime, @Param("wealthId") Long wealthId);

}
