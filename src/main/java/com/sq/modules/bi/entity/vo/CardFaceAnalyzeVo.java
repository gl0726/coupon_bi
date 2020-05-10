package com.sq.modules.bi.entity.vo;

import com.sq.modules.bi.entity.CustomCard;
import com.sq.modules.bi.entity.OrdinaryCard;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *      基本信息统计
 * </p>
 *
 * @author zhongxunan
 * @since 2019-05-12
 */
@Data
public class CardFaceAnalyzeVo implements Serializable {
    private static final long serialVersionUID = 3568211203498085115L;

    //普通卡
    private List<OrdinaryCard> normalCard;

    //定制卡
    private List<CustomCard> customCard;

    //普通卡总数
    private Long normalTotal;

    //定制卡总数
    private Long customerTotal;



}
