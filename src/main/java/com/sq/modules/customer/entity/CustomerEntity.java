package com.sq.modules.customer.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;

/**
 * 会员基础信息表
 *
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 10:26:11
 */
@Data
@TableName("b_customer")
public class CustomerEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 用户账号
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;
    /**
     * 真实姓名
     */
    @JsonIgnore
    private String realName;
    /**
     * 性别 m:男;f:女;s:保密
     */
    @JsonIgnore
    private String sex;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String headPortrait;
    /**
     * 生日
     */
    @JsonIgnore
    private Date birthday;
    /**
     * 省
     */
    @JsonIgnore
    private String province;
    /**
     * 市编号
     */
    @JsonIgnore
    private String city;
    /**
     * 区
     */
    @JsonIgnore
    private String area;
    /**
     * 详细地址
     */
    @JsonIgnore
    private String address;
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
    @Version
    private Long version;
    /**
     * 有效性(0：失效,1：有效)
     */
    @JsonIgnore
    private Integer enable;
    /**
     * 用户第三方信息
     */
    @TableField(exist = false)
//    @JsonIgnore
            List<CustomerThirdpartyEntity> customerThirdpartyEntitys;
    /**
     * 用户账户信息
     */
    @TableField(exist = false)
    @JsonIgnore
    CustomerWealthEntity customerWealthEntity;

    @TableField(exist = false)
    private String openId;
    /**
     * 第三方参数ID
     */
    @TableField(exist = false)
    private Integer partyId;

    /**
     * 盐值
     */
    private String salt;



}
