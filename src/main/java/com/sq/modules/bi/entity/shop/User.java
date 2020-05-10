package com.sq.modules.bi.entity.shop;

import lombok.Data;

/**
 * @author gl
 * @create 2020-04-28 17:28
 * 用户统计类
 */
@Data
public class User implements OrderStatistics {
    //日期
    private String theDate;
    //数量
    private int counts;

    public User(String theDate, int counts) {
        this.theDate = theDate;
        this.counts = counts;
    }
}
