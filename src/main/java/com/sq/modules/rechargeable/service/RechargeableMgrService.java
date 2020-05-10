package com.sq.modules.rechargeable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.common.utils.PageUtils;
import com.sq.modules.rechargeable.entity.RechargeableCardEntityVo;
import com.sq.modules.rechargeable.entity.RechargeableMgrEntity;

import java.util.List;
import java.util.Map;

/**
 * 充值卡管理
 *
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 15:20:46
 */
public interface RechargeableMgrService extends IService<RechargeableMgrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 分页条件查询
     * @param params
     * @return
     */
    PageUtils queryPageCondition(Map<String, Object> params);

    int createRechargeable(RechargeableMgrEntity rechargeableMgr);



    /**
     * 导出excel的条件查询
     * @param activationState
     * @param bdId
     * @param batchNo
     * @return
     */
    List<RechargeableCardEntityVo> selectExcelVo ( Short activationState, Long  bdId, String batchNo);

    /**
     * 修改bdid
     * @param bdId
     * @param beginSequence
     * @param endSequence
     */
    void updateBd(Long bdId,String beginSequence,String endSequence,Long userId);

}

