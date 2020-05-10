package com.sq.modules.bi.entty2.transaction;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 兑换/支付次数-模型类-模型类
 * @author gl
 * @since 2018-08-13
 */
@Data
public class ExgPayTimesThree implements Serializable {
    private static final long serialVersionUID = 1L;

    //昨日某小时
    private String theTime;
    //总数
    private int totalCount;

}
