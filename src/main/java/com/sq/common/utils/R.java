/**
 * Copyright (c) 2016-2019 湖南神雀网络科技有限公司 All rights reserved.
 * <p>
 * https://www.sq.com
 * <p>
 * 版权所有，侵权必究！
 */

package com.sq.common.utils;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author Mark Wenjunchi
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("code", 0);
        put("msg", "操作成功");
    }

    public static R error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static R error(ServiceCode dBServiceCode) {
        return error(dBServiceCode.getCode(), dBServiceCode.getMsg());
    }

    public static R ok(ServiceCode dBServiceCode) {
        return error(dBServiceCode.getCode(), dBServiceCode.getMsg());
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
