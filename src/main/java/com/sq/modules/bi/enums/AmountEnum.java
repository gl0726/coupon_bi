package com.sq.modules.bi.enums;

/**
 * <p>
 *      金额数量枚举类
 * </p>
 *
 * @author gl
 * @since 2019-08-16
 */
public enum AmountEnum {
    money("money"),number("number");
    private final String type;
    AmountEnum(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
