package com.sq.modules.rechargeable.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 调拨记录表
 * 
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-04-19 15:41:25
 */
@Data
@TableName("b_transfer_record")
public class TransferRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	private Long id;
	/**
	 * 开始序列号
	 */
	private String startSeq;
	/**
	 * 结束系列号
	 */
	private String endSeq;
	/**
	 * bdid
	 */
	private Long bdId;
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
