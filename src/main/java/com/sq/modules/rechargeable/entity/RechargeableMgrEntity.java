package com.sq.modules.rechargeable.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 充值卡管理
 * 
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 15:20:46
 */
@Data
@TableName("b_rechargeable_mgr")
public class RechargeableMgrEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	private Long id;
	/**
	 * 批次号
	 */

	private String batchNumber;
	/**
	 * 抬头
	 */
	private String title;
	/**
	 * 面值
	 */
	private BigDecimal faceValue;
	/**
	 * 数量
	 */
	private Long count;
	/**
	 * 起始日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date startDate;
	/**
	 * 时长<参数管理>
	 */
	private Integer duration;
	/**
	 * BD
	 */
	private Long bdId;
	/**
	 * 创建时间
	 */
	@JsonIgnore
	private Date createTime;
	/**
	 * 修改时间
	 */
	@JsonIgnore
	private Date updateTime;
	/**
	 * 创建人Id
	 */
	@JsonIgnore
	private Long createId;
	/**
	 * 修改人Id
	 */
	@JsonIgnore
	private Long updateId;
	/**
	 * 备注
	 */
	@JsonIgnore
	private String remarks;
	/**
	 * 版本
	 */
	@JsonIgnore
	private Long version;
	/**
	 * 有效性(0：失效,1：有效) 
	 */
	@JsonIgnore
	private Integer enable;

}
