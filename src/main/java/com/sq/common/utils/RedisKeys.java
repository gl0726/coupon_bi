/**
 * Copyright (c) 2016-2019 湖南神雀网络科技有限公司 All rights reserved.
 * <p>
 * https://www.sq.com
 * <p>
 * 版权所有，侵权必究！
 */

package com.sq.common.utils;

/**
 * Redis所有Keys
 *
 * @author Mark Wenjunchi
 */
public class RedisKeys {
    public final static String PHP_ACCESS_TOKEN = "php_access_token";

    public static String getSysConfigKey(String key) {
        return "sys:config:" + key;
    }

    public final static String BATCH_NUMBER_KEY = "BATCH_NUMBER_KEY";


}
