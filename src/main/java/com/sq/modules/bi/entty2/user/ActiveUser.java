package com.sq.modules.bi.entty2.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户-注册用户数-各小时/日期封装类
 * @author gl
 * @since 2018-08-09
 */
@Data
public class ActiveUser implements Serializable {

    private static final long serialVersionUID = 1L;

    //昨日某小时
    private String theTime;
    //昨日某小时注册用户数
    private long totalCount;

}
