package com.sq.modules.rechargeable.dao;

import com.sq.modules.rechargeable.entity.RechargeableCardEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 充值卡
 * 
 * @author wenjc
 * @email wenjc@sqqmall.com
 * @date 2019-03-30 15:20:51
 */
@Mapper
public interface RechargeableCardDao extends BaseMapper<RechargeableCardEntity> {
    /**
     * 根据卡号密码与面额查询
     * @param map
     * @return
     */
    List<RechargeableCardEntity> selectByCordNoAndcardPwd(Map<String ,Object> map);

    /**
     * 根据卡号修改激活状态
     * @param cardNo
     * @return
     */
    int updateActivity(String cardNo);
}
