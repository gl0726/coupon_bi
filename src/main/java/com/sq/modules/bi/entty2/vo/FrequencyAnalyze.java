package com.sq.modules.bi.entty2.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * BI2.0 兑换、支付频次人数分布
 * </p>
 *
 * @author gl
 * @since 2018-08-13
 */
@Data
public class FrequencyAnalyze implements Serializable {
    private static final long serialVersionUID = 1L;

    //兑换次数情况
    private List<FrequencyAnalyzeTwo> exchange;
    //支付次数情况
    private List<FrequencyAnalyzeTwo> pay;

}
