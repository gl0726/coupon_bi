package com.sq.modules.bi.enums;

/**
 * <p>
 *      卡类型
 *      active      激活卡
 *      exchange    兑换卡
 * </p>
 *
 * @author zhongxunan
 * @since 2019-05-13
 */
public enum CardEnum {
    ACTIVE("active"),EXCHANGE("exchange");
    private final String type;
    CardEnum(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
