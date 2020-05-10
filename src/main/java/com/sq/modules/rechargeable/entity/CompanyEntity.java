package com.sq.modules.rechargeable.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 企业表
 * 
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-04-17 11:04:18
 */
@Data
@TableName("b_company")
public class CompanyEntity implements Serializable {
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
	 * 管理id
	 */
	private Long mgrId;
	/**
	 * 号段
	 */
	private String number;
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
