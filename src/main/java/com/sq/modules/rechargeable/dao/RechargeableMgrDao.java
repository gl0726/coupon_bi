package com.sq.modules.rechargeable.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sq.modules.rechargeable.entity.RechargeableCardEntityVo;
import com.sq.modules.rechargeable.entity.RechargeableMgrEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 充值卡管理
 * 
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 15:20:46
 */
@Mapper
public interface RechargeableMgrDao extends BaseMapper<RechargeableMgrEntity> {
    IPage<RechargeableCardEntityVo> selectPageVo(IPage page, @Param("activationState") Short activationState, @Param("bdId")Long  bdId, @Param("batchNo")String batchNo);

    /**
     * 用于导出excel
     * @param activationState
     * @param bdId
     * @param batchNo
     * @return
     */
    List <RechargeableCardEntityVo> selectPageVo(@Param("activationState") Short activationState, @Param("bdId")Long  bdId, @Param("batchNo")String batchNo);

    /**
     * 修改bdid
     * @param params
     */
    void updateBd(Map<String ,Object> params);
}
