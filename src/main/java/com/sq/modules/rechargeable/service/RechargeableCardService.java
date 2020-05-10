package com.sq.modules.rechargeable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.common.utils.PageUtils;
import com.sq.modules.rechargeable.entity.RechargeableCardEntity;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 充值卡
 *
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 15:20:51
 */
public interface RechargeableCardService extends IService<RechargeableCardEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据卡号密码面值查询
     * @param cardNo
     * @param cardPwd
     * @param faceValue
     * @return
     */
    boolean selectByCordNoAndcardPwd(String cardNo, String cardPwd, BigDecimal faceValue);

    /**
     * 根据CardNo修改充值卡的激活状态
     * @param CardNo
     * @return
     */
    int  updateByCardNo(String CardNo);
}

