package com.sq.modules.bi.entty2.user;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户-注册用户数-通用模型类
 * @author gl
 * @since 2018-08-09
 */
@Data
public class RegisteredUser implements Serializable {

    private static final long serialVersionUID = 1L;

    //开始结束时间
    private String dateBeginEnd;
    //昨日注册用户数总数
    private Long activieTotal;
    //上一日注册用户数总数
    private Long lastActivieTotal;
    //昨日上一日对比
    private String rat;
    //平均数 = (昨日总数 + 上一日总数/2) / (24小时/7日/30日/月数)
    private double avg;
    //昨日各个小时注册用户数
    private List<ActiveUser> currentRound;
    //上一日各个小时激活卡数
    private List<ActiveUser> lastRound;

}
