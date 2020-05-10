package com.sq.common.utils;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.sq.common.exception.RRException;
import com.sq.config.PropertiesFilesConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname tokenConfig
 * @Description 获取token
 * @Date 2019\3\9 0009 11:05
 * @Created by WenJunChi
 */
@Component
public class TokenUtil {
    @Autowired
    PropertiesFilesConfig propertiesFilesConfig;
    @Autowired
    RedisUtils redisUtils;

    /**
     * 获取token
     *
     * @param isFlag 为true真时，强制刷新token
     * @return
     */
    public String getToKen(boolean isFlag) {
        if (propertiesFilesConfig.isForcerefresh()) {
            return getRemoteToken();
        }
        String accessToken = redisUtils.get(RedisKeys.PHP_ACCESS_TOKEN);
        if (!StringUtils.isBlank(accessToken)) {
            return accessToken;
        }
        return getRemoteToken();
    }

    private String getRemoteToken() {
        String accessToken = "";
        try {
            Map param = new HashMap<>();
            param.put("username", propertiesFilesConfig.getUsername());
            param.put("password", propertiesFilesConfig.getPassword());
            JSONObject post = HttpKit.post(propertiesFilesConfig.getPhpUrl() + RemoteInterfaceName.getToken, param);
            accessToken = post.get("access_token") == null ? "" : post.get("access_token").toString();
            redisUtils.set(RedisKeys.PHP_ACCESS_TOKEN, accessToken);
        } catch (Exception e) {
            throw new RRException("获取ToKen失败" + e.getMessage(), ServiceCode.DB_SERVICE_SERVICE_ERROR.getCode());
        }
        return accessToken;
    }

}
