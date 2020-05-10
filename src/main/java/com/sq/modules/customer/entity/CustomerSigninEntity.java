package com.sq.modules.customer.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lq
 * @date 2019\4\17 0017 11:42
 */

@Data
@TableName("b_customer_signin")
public class CustomerSigninEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    @ApiModelProperty(hidden = true)
    private Long id;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户Id")
    @NotNull(message = "用户Id不能为空")
    private Long customerId;
    /**
     * 签到日期
     */
    @ApiModelProperty(hidden = true)
    private Date siginDate;
    /**
     * 连续签到次数
     */
    @ApiModelProperty(hidden = true)
    private Long continuitySigin;
    /**
     * 奖励金额
     */
    @ApiModelProperty(hidden = true)
    private BigDecimal rewardAmount;
    /**
     * 创建时间
     */
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Date updateTime;
    /**
     * 创建人Id
     */
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Long createId;
    /**
     * 修改人Id
     */
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Long updateId;
    /**
     * 备注
     */
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private String remarks;
    /**
     * 版本
     */
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Long version;
    /**
     * 有效性(0：失效,1：有效)
     */
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Integer enable;

}

