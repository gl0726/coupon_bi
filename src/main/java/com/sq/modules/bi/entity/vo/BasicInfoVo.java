package com.sq.modules.bi.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 基本信息统计
 * </p>
 *
 * @author zhongxunan
 * @since 2019-05-12
 */
@Data
public class BasicInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;
    //兑换金额
    private Long exchangeAmount;
    //人均兑换金额
    private Long exchangePerAvg;
    //激活卡数
    private Long activeTotal;
    //激活金额
    private Long activeAmount;
    //会员总余额
    private Long totalRemaining;
    //生成卡数
    private Long cardNum;
    //生成金额
    private Long cardAmount;
    //注册会员数
    private Long regUser;


    //BI2.0 10个新字段数据

    //激活人数
    private Long regUserRate;
    //兑换人数
    private Long exchangePer;

    //支付金额
    private Long payAmount;
    //支付人数
    private Long payUser;
    //客单价
    private Long payUnitPrice;
    //支付笔数和
    private Long payNum;
    //预估佣金
    private Long estAmount;
    //人均结算佣金
    private Long settleUserAmount;
    //结算佣金
    private Long settleAmount;
    //已结算订单
    private Long settleOrder;


}
