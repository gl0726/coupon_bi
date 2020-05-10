package com.sq.common.utils;

import cn.hutool.http.HttpUtil;

import com.alibaba.fastjson.JSONObject;

import com.sq.common.exception.RRException;

import java.util.Map;

/**
 * @author by WenJunChi
 * @Classname HttpKit
 * @Description Http请求操作
 * @Date 2019\3\9 0009 14:34
 */
public class HttpKit {
    public static JSONObject post(String urlString, Map<String, Object> paramMap) {
        String s = HttpUtil.post(urlString, paramMap);
        JSONObject jsonBra = JSONObject.parseObject(s);
        if (!jsonBra.containsKey("data")) {
            throw new RRException("返回参数有误,没有data值", ServiceCode.DB_SERVICE_SERVICE_ERROR.getCode());
        }
        Integer code = Integer.valueOf(jsonBra.get("code") == null ? "" : jsonBra.get("code").toString());
        if (code != ServiceCode.DB_RETURN.getCode()) {
            String msg = ((JSONObject) jsonBra.get("data")).get("message").toString();
            throw new RRException(msg, ServiceCode.DB_SERVICE_SERVICE_ERROR.getCode());
        }
        return (JSONObject) jsonBra.get("data");
    }

}
