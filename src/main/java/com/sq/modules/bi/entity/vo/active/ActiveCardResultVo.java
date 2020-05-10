package com.sq.modules.bi.entity.vo.active;

import lombok.Data;

import java.io.Serializable;

/**
 * 激活卡数 返回结果
 */
@Data
public class ActiveCardResultVo implements Serializable {

    private static final long serialVersionUID = 3568211203498085157L;

    //昨日
    private ActiveCardVo lastDay;
    //近七天
    private ActiveCardVo last7day;
    //近30天
    private ActiveCardVo last30day;
    //月
    private ActiveCardVo lastMonth;
}
