package com.sq.modules.customer.entity.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AccountRechargeVo implements Serializable {
    private static final long serialVersionUID = 6423580112875610031L;
    private String rechargeTime;
    private List<RechargeDetailVo> detailVo;
}


