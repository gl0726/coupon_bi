package com.sq.modules.bi.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *  激活金额
 * </p>
 *
 * @author zhongxunan
 * @since 2019-05-13
 */
@Data
public class ActiveCard implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 统计日期
     */
    private String createTime;

    /**
     * 统计的时间
     */
    private String theTime;

    /**
     * 统计的激活金额
     */
    private Long totalCount;


}
