package com.sq.modules.bi.entity;

import lombok.Data;
import java.io.Serializable;

/**
* <p>
    * 优惠券兑换次数商品排行榜表
    * </p>
*
* @author zhongxunan
* @since 2019-05-13
*/
@Data
public class ExchangedGoodsRank implements Serializable {

    private static final long serialVersionUID = 1L;

            /**
            * 统计日期
            */
    private String createTime;

            /**
            * 商品Id
            */
    private String skuId;

            /**
            * 商品名称
            */
    private String skuName;

            /**
            * 抵扣平台
            */
    private String deduction;

            /**
            * 每个商品id兑换次数
            */
    private Long skuCount;

            /**
            * 排行
            */
    private Integer rowNum;


}
