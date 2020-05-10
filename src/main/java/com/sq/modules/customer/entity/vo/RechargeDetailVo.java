package com.sq.modules.customer.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class RechargeDetailVo implements Serializable {
    private static final long serialVersionUID = -8980210359187656060L;
    private String title;
    private BigDecimal rechargeAmount;
    private String cardNo;
}
