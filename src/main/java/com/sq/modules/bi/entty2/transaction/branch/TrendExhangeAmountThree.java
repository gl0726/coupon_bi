package com.sq.modules.bi.entty2.transaction.branch;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * BI2.0 各平台兑换、支付、佣金趋势分析-兑换金额-支付金额-预估佣金模型类-模型类-模型类
 * </p>
 *
 * @author gl
 * @since 2018-08-14
 */
@Data
public class TrendExhangeAmountThree implements Serializable {

    private static final long serialVersionUID = 1L;

    //时间
    private String theTime;
    //总数
    private int totalCount;


}
