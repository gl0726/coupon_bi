package com.sq.modules.rechargeable.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.multipart.MultipartFile;

/**
 * 广告位管理
 * 
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 15:20:45
 */
@Data
@TableName("b_advertisement_mar")
public class AdvertisementMarEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	private Long id;
	/**
	 * 广告位
	 */
	private String advertising;
	/**
	 * 类型  0 ：用户主页 1 ：更多省钱
	 */
	private  Integer type;
	/**
	 * 公司id
	 */
	private Long companyId;
	/**
	 * 图片地址
	 */
	private String image;
	/**
	 * 链接地址
	 */
	private String linkaddress;
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
