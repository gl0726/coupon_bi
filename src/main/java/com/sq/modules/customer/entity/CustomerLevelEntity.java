package com.sq.modules.customer.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 会员等级表
 * 
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 10:26:11
 */
@Data
@TableName("b_customer_level")
public class CustomerLevelEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	private Long id;
	/**
	 * 客户ID
	 */
	private Long customerId;
	/**
	 * 等级code
	 */
	private String code;
	/**
	 * 等级名称
	 */
	private String name;
	/**
	 * 达到等级的最小值
	 */
	private Integer minScore;
	/**
	 * 达到等级的最大值
	 */
	private Integer maxScore;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 创建人Id
	 */
	private Long createId;
	/**
	 * 修改人Id
	 */
	private Long updateId;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 版本
	 */
	private Long version;
	/**
	 * 有效性(0：失效,1：有效) 
	 */
	private Integer enable;

}
