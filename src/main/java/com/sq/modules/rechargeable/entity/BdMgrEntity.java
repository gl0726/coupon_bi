package com.sq.modules.rechargeable.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * BD管理
 *
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 15:20:45
 */
@Data
@TableName("b_bd_mgr")
public class BdMgrEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 隶属公司<参数管理>
     */
    private Integer company;
    /**
     * 隶属公司id
     */
    @TableField(exist = false)
    private String companyKey;
    /**
     * 隶属公司名称
     */
    @TableField(exist = false)
    private String companyName;
    /**
     * 电话号码
     */
    private String phone;
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
