package com.sq.modules.customer.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 账户安全表
 * 
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 10:26:11
 */
@Data
@TableName("b_account_security")
public class AccountSecurityEntity implements Serializable {
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
	 * 证件类型
	 */
	private String cardType;
	/**
	 * 证件编号
	 */
	private String cardNo;
	/**
	 * 用户邮箱
	 */
	private String email;
	/**
	 * 邮箱是否已激活。y:已激活,n:未激活。默认n
	 */
	private String emailisActive;
	/**
	 * 客户绑定的手机号
	 */
	private String tel;
	/**
	 * 修改密码是否发送邮件
	 */
	private String isUpPwdSend;
	/**
	 * 修改手机是否发送邮件
	 */
	private String isUpTelSend;
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
