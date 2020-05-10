package com.sq.modules.bi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 普通卡
 * </p>
 *
 * @author zhongxunan
 * @since 2019-05-13
 */
@Data
public class OrdinaryCard implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 统计日期
     */
    private String createTime;

    /**
     * 面值
     */
    private BigDecimal faceValue;

    /**
     * 普通卡总数
     */
    private Long totalCount;

    /**
     * 改面值卡的占比
     */
    private Double rate;

}
