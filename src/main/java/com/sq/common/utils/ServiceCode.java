
package com.sq.common.utils;

/**
 * 自定义Code
 */
public enum ServiceCode {

    //RPC层调用错误码
    DB_SUCESS(0, "操作成功"),
    DB_RETURN(200, "服务返回正常"),
    DB_PARAM_NULL(300, "参数为空"),
    DB_SERVICE_OK(20100, "服务正常"),
    DB_SERVICE_UNKNOWN_ERROR(20101, "未知异常"),
    DB_SERVICE_AGENT_ERROR(20102, "DBServiceAgent异常"),
    DB_SERVICE_NETWORK_ERROR(20103, "网络异常"),
    DB_SERVICE_DBDAO_ERROR(20104, "返回数据库的具体异常信息"),
    DB_SERVICE_INVALID_FUNCTION(20105, "方法名不存在"),
    DB_SERVICE_INVALID_PARAMETER(20106, "方法参数错误"),
    DB_SERVICE_FUNCTION_NO_ACCESS(20107, "对此方法无访问权限"),
    DB_SERVICE_SPEAKER_NOT_EXISTED(20108, "数据库中没有要查询数据"),
    DB_SERVICE_SPEAKER_HAVE_NOT_VP(20109, "speaker中没有对应的voiceprint"),
    DB_SERVICE_DATA_OVERVALUED(20110, "返回数据比预期的多"),
    DB_SERVICE_SERVICE_ERROR(20111, "系统异常"),
    DB_SERVICE_OPTIMISATIC_LOCK(20112, "乐观锁"),

    DB_SERVICE_CARD_NOT_EXIST(20113,"卡号不存在"),
    DB_SERVICE_PASSWORD_ERROR(20114,"密码错误"),
    DB_SERVICE_ACCOUNT_NOT_EXIST(20115,"账户不存在"),
    DB_SERVICE_CARD_PAST_DUE(20116,"卡号已过期"),
    DB_SERVICE_CARD_ERROR(20117,"异常卡"),
    DB_SERVICE_RECHARGE_FAILURE(20118,"充值失败"),
    DB_SERVICE_CARD_ISUSED(20119,"该充值卡已被使用"),
    DB_SERVICE_ACCOUNT_ERROR(20120,"账户异常"),
    DB_SERVICE_BALANCE_NOT_ENOUGH(20121,"账户余额不足"),
    DB_SERVICE_DEDUCTION_FAILURE(20122,"抵扣失败"),
    DB_SERVICE_INVALID_ACCOUNT(20123,"无效的账户"),
    DB_SERVICE_CREATE_FAILURE(20124,"初始化账户信息失败"),
    DB_SERVICE_CHECKED_IN(20125,"已签到"),
    DB_SERVICE_CUSTOMER_ID_CANNOT_BLANK(20126,"用户id不能为空"),
    DB_SERVICE_USER_NOT_EXIST(20127,"用户不存在");


    private String msg;
    int code;

    private ServiceCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getCode() {
        return this.code;
    }

}
