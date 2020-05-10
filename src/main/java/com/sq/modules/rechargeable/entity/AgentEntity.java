package com.sq.modules.rechargeable.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * 代理商表
 * 
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-04-17 16:34:11
 */
@Data
@TableName("b_agent")
public class AgentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	private Long id;
	/**
	 * 公司姓名
	 */
	private String name;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * bdid
	 */
	private Long bdId;
	/**
	 * bd名称
	 */
	private String bdName;
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
