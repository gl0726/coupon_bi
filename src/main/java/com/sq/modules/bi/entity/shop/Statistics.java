package com.sq.modules.bi.entity.shop;

import lombok.Data;
import java.util.List;
import java.io.Serializable;

/**
 * @author gl
 * @create 2020-04-28 10:48
 */
@Data
public class Statistics implements Serializable {

    //订单数据
    private List<OrderStatistics> order;
    //流水数据
    private List<OrderStatistics> runningWater;

}
