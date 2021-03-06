package com.sq.modules.bi.entty2.vo;

import com.sq.modules.bi.entty2.Ranking.*;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * BI2.0 商品兑换排行榜-数量
 * </p>
 *
 * @author gl
 * @since 2018-08-08
 */
@Data
public class ExchangeNumber implements Serializable {
    private static final long serialVersionUID = 1L;
    //全部
    private Platform all;
    //好品购
    private Platform gp;
    //淘宝
    private Platform tb;
    //天猫
    private Platform tm;
    //京东
    private Platform jd;
    //拼多多
    private Platform pdd;

}
