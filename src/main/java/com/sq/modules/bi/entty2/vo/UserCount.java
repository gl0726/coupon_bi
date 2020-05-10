package com.sq.modules.bi.entty2.vo;

import com.sq.modules.bi.entty2.Ranking.Platform;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * BI2.0 用户账户余额人数统计
 * </p>
 *
 * @author gl
 * @since 2018-08-13
 */
@Data
public class UserCount implements Serializable {
    private static final long serialVersionUID = 1L;

    //0元
    private int balance0;
    //1-50元
    private int balance50;
    //51-100元
    private int balance100;
    //101-200元
    private int balance200;
    //200元以上
    private int balance300;

}
