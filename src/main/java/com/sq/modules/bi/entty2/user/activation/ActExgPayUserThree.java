package com.sq.modules.bi.entty2.user.activation;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 激活/兑换/支付人数-模型类-模型类
 * @author gl
 * @since 2018-08-013
 */
@Data
public class ActExgPayUserThree implements Serializable {
    private static final long serialVersionUID = 1L;

    //昨日某小时
    private String theTime;
    //总数
    private int totalCount;


}
