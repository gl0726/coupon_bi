package com.sq.modules.bi.entity.shop;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gl
 * @create 2020-04-28 10:11
 */
@Data
public class ParameterTwo implements Serializable {
    //店铺唯一编码
    private String code;
    //店铺唯一ID
    private int id;
    //近7天 = day，近1个月 = month
    private String date;
    //统计时间开始 格式： yyyy-MM-dd
    private String start;
    //统计时间结束 格式： yyyy-MM-dd
    private String end;
}
