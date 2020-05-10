/**
 * Copyright (c) 2018 湖南神雀网络科技有限公司 All rights reserved.
 *
 * www.sqqmall.com
 *
 * 版权所有，侵权必究！
 */

package com.sq.datasource.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 多数据源
 *
 * @author Mark Wenjunchi
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicContextHolder.peek();
    }

}
