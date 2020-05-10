package com.sq.modules.bi.entty2.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 昨日上一日各小时平均数
 * @author gl
 * @since 2018-08-09
 */
@Data
public class ActiveUserTwo implements Serializable {

    private static final long serialVersionUID = 1L;

    //昨日某小时的
    private String theTime;
    //昨日某小时注册用户数
    private double avg;

}
