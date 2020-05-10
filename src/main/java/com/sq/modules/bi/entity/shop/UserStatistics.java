package com.sq.modules.bi.entity.shop;

import java.util.List;
import lombok.Data;

import java.io.Serializable;

/**
 * @author gl
 * @create 2020-04-28 17:12
 * 用户统计集合类
 */
@Data
public class UserStatistics implements Serializable {
    //注册用户量
    private List<OrderStatistics> regUserNum;
    //下单用户量
    private List<OrderStatistics> orderNserNum;
    //总用户量
    private List<OrderStatistics> allUserNum;

}
