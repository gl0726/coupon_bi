package com.sq.modules.bi.entity.shop;

import lombok.Data;

/**
 * @author gl
 * @create 2020-04-28 17:06
 * 用户统计模板
 */
@Data
public class UserCollect implements OrderStatistics {
    //日期
    private String theDate;
    //注册用户量
    private int regUserNum;
    //下单用户量
    private int orderUserNum;
    //总用户量
    private int allUserNum;
}
