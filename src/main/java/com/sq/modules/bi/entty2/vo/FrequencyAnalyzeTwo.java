package com.sq.modules.bi.entty2.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * BI2.0 兑换、支付频次人数分布-模型
 * </p>
 *
 * @author gl
 * @since 2018-08-13
 */
@Data
public class FrequencyAnalyzeTwo implements Serializable {
    private static final long serialVersionUID = 1L;

    //兑换/支付次数类型
    private String deductionCount;
    //各种兑换/支付次数的总人数
    private int totalCustomer;

}
