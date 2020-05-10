package com.sq.modules.customer.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 * 第三方平台信息
 *
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 10:26:11
 */
@Data
@TableName("b_customer_thirdparty")
public class CustomerThirdpartyEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;
    /**
     * 客户ID
     */
    private Long customerId;
    /**
     * 平台<参数管理>
     */
    private Integer partyId;
    /**
     * 第三方ID
     */
    private String sourceId;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String headImg;
    /**
     * 性别 m:男;f:女;s:保密
     */
    private String sex;
    /**
     * 区
     */
    private String area;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 省
     */
    private String province;
    /**
     * 市编号
     */
    private String city;
    /**
     * 详细地址
     */
    private String address;
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
    @Version
    private Long version;
    /**
     * 有效性(0：失效,1：有效)
     */
    private Integer enable;

}
