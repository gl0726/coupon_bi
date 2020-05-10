package com.sq.modules.bi.enums;

/**
 * <p>
 *      各大平台枚举类
 * </p>
 *
 * @author gl
 * @since 2019-08-08
 */
public enum PlatformEnum {
    PlatformAll("all"),PlatformGP("gp"),PlatformTB("tb"),PlatformTM("tm"),PlatformJD("jd"),PlatformPDD("pdd");
    private final String type;
    PlatformEnum(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
