package com.sq.modules.bi.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 注册会员统计表
 * </p>
 *
 * @author zhongxunan
 * @since 2019-05-12
 */
@Data
public class DailyRegisteredUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 统计日期
     */
    private String createTime;

    /**
     * 注册会员总数
     */
    private Long totalCustomer;


}
