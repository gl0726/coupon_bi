package com.sq.modules.rechargeable.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 充值卡
 * 
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 15:20:51
 */
@Data
@TableName("b_rechargeable_card")
public class RechargeableCardEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	private Long id;
	/**
	 * 卡管理ID
	 */
	private Long managerId;
	/**
	 * 批次号
	 */
	private String batchNo;
	/**
	 * 序列号
	 */
	private String sequence;
	/**
	 * 卡号
	 */
	private String cardNo;
	/**
	 * 8位——数字+字母大小写（区分大小写）
	 */
	private String cardPwd;
	/**
	 * bd的id
	 */
	private Long  bdId;
	/**
	 * 激活状态(0：未激活,1：已激活) 
	 */
	private Integer activationState;
	/**
	 * 失效时间
	 */
	private Date failureTime;
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
