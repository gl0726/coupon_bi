package com.sq.modules.customer.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 抵扣记录
 * 
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 15:26:53
 */
@Data
@TableName("b_account_consume")
public class AccountConsumeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty(hidden = true)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long id;
	/**
	 * 抵扣金额
	 */
	@ApiModelProperty(value = "抵扣金额")
	@NotNull(message = "抵扣金额不能为空")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private BigDecimal deductionAmount;
	/**
	 * 抵扣平台<参数管理>
	 */
	@ApiModelProperty(value = "抵扣平台")
	@NotBlank(message = "抵扣平台不能为空")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String deduction;
	/**
	 * 消费时间
	 */
	@ApiModelProperty(hidden = true)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date dissipateDate;
	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id")
	@NotNull(message = "用户id不能为空")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long customerId;
	/**
	 * 商品id
	 */
	@ApiModelProperty(value = "商品id")
	@NotBlank(message = "商品id不能为空")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String skuId;
	/**
	 * 商品名称
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String skuName;
	/**
	 * 商品图片
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String picUrl;
	/**
	 *原价
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private BigDecimal wlPrice;
	/**
	 *券后价
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private BigDecimal wlPriceAfter;
	/**
	 *电话
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String mobile;
	/**
	 * 抵扣券生效时间
	 */
	@ApiModelProperty(value = "抵扣券生效时间")
	@NotNull(message = "抵扣券生效时间不能为空")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date startTime;
	/**
	 * 抵扣券失效时间
	 */
	@ApiModelProperty(value = "抵扣券失效时间")
	@NotNull(message = "抵扣券失效时间不能为空")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date endTime;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(hidden = true)
	@JsonIgnore
	private Date createTime;
	/**
	 * 修改时间
	 */
	@ApiModelProperty(hidden = true)
	@JsonIgnore
	private Date updateTime;
	/**
	 * 创建人Id
	 */
	@ApiModelProperty(hidden = true)
	@JsonIgnore
	private Long createId;
	/**
	 * 修改人Id
	 */
	@ApiModelProperty(hidden = true)
	@JsonIgnore
	private Long updateId;
	/**
	 * 备注
	 */
	@ApiModelProperty(hidden = true)
	@JsonIgnore
	private String remarks;
	/**
	 * 版本
	 */
	@ApiModelProperty(hidden = true)
	@JsonIgnore
	private Long version;
	/**
	 * 有效性(0：失效,1：有效) 
	 */
	@ApiModelProperty(hidden = true)
	@JsonIgnore
	private Integer enable;
	/**
	 * 抵扣券的有效性(0：失效,1：有效)
	 */
	@TableField(exist = false)
	@ApiModelProperty(hidden = true)
	private Integer couponEnable;
}
