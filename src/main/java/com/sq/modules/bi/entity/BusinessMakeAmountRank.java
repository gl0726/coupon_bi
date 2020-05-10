package com.sq.modules.bi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* <p>
    * 定制卡排行榜(以生成金额生成排名)
    * </p>
*
* @author zhongxunan
* @since 2019-05-14
*/
@Data
public class BusinessMakeAmountRank implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 统计日期
            */
    private String createTime;

            /**
            * 公司id
            */
    private Long companyId;

            /**
            * 公司名称
            */
    private String name;

            /**
            * 各公司生成卡总金额
            */
    private BigDecimal companyValue;

            /**
            * 各公司激活卡总金额
            */
    private BigDecimal companyActivatedValue;

            /**
            * 各公司兑换的总金额(消费金额)
            */
    private BigDecimal comanyRechargeValue;

            /**
            * 排名
            */
    private Integer rowNum;


}
