package com.sq.modules.bi.entty2.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * BI2.0 用户账户余额为0元趋势分析-临时表
 * </p>
 *
 * @author gl
 * @since 2018-08-13
 */
@Data
public class TrendAnalysisTwo implements Serializable {
    private static final long serialVersionUID = 1L;
    //create_time
    private String createTime;
    //日期
    private String day;
    //总数
    private int total;

}
