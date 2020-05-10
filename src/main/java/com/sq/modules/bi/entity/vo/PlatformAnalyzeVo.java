package com.sq.modules.bi.entity.vo;

import com.sq.modules.bi.entity.PlatformCouponExchange;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class PlatformAnalyzeVo implements Serializable {

    private static final long serialVersionUID = 3568211203498085115L;

    //金额
    private List<PlatformCouponExchange> amount;

    //人数
    private List<PlatformCouponExchange> person;

    //总人数
    private Long totalPerson;

    //总金额
    private BigDecimal totalAmount;

}
