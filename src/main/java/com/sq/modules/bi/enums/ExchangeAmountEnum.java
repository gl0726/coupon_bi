package com.sq.modules.bi.enums;

/**
 * <p>
 *      兑换金额 时间段类型
 * </p>
 *
 * @author zhongxunan
 * @since 2019-05-13
 */
public enum  ExchangeAmountEnum {
    LASTDay("lastDay"),LAST7Day("last7day"),LAST30Day("last30day"),LASTMONTH("lastMonth");
    private final String type;
    ExchangeAmountEnum(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
