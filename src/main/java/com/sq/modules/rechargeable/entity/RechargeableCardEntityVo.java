package com.sq.modules.rechargeable.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 充值卡
 * 
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 15:20:51
 */
@Data
@TableName("b_rechargeable_card")
public class RechargeableCardEntityVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	//private Long id;

	@ExcelColumn(value = "序列号", col = 0)
	private String sequence;
	/**
	 * 抬头
	 */
	@ExcelColumn(value = "抬头", col = 1)
	private String title;
	/**
	 * 面额
	 */
	@ExcelColumn(value = "面额", col = 2)
	private BigDecimal faceValue;

	/**
	 * 卡号
	 */
    @ExcelColumn(value = "卡号", col = 3)
	private String cardNo;
	/**
	 * 8位——数字+字母大小写（区分大小写）
	 */
    @ExcelColumn(value = "卡密", col = 4)
	private String cardPwd;
	/**
	 * 批次号
	 */
	@ExcelColumn(value = "批次号", col = 5)
	private String batchNo;
	/**
	 * bd姓名
	 */
    @ExcelColumn(value = "bd姓名", col = 6)
	private String bdName;
	/**
	 * 有效区间
	 */
	@ExcelColumn(value = "有效区间", col = 7)
	private String strDate;
	/**
	 * 时长
	 */
	@ExcelColumn(value = "时长", col = 8)
	private String durationTime;
	/**
	 * 激活状态
	 */
	@ExcelColumn(value = "激活状态 ", col = 9)
	private String activation;
	/**
	 * 1、30天 2、3个月 3、6个月 4 一年
	 */

    private Integer duration;

	/**
	 * 失效时间
	 */
	private Date failureTime;
	/**
	 * 生效时间
	 */
	private Date startTime;

	/**
	 * 激活状态(0：未激活,1：已激活)
	 */
	private Integer activationState;
	/**
	 * 开始时间
	 */
	@ExcelColumn(value = "创建时间 ", col = 10)
	private String createTime;

}
