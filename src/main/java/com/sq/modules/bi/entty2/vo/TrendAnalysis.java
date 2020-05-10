package com.sq.modules.bi.entty2.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * BI2.0 用户账户余额为0元趋势分析
 * </p>
 *
 * @author gl
 * @since 2018-08-13
 */
@Data
public class TrendAnalysis implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<TrendAnalysisTwo> balance;
    //开始日期
    private String beginDate;
    //结束日期
    private String endDate;

}
