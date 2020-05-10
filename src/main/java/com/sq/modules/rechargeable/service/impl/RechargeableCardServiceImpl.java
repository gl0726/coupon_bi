package com.sq.modules.rechargeable.service.impl;

import com.sq.common.exception.RRException;
import com.sq.common.utils.ServiceCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.common.utils.PageUtils;
import com.sq.common.utils.Query;

import com.sq.modules.rechargeable.dao.RechargeableCardDao;
import com.sq.modules.rechargeable.entity.RechargeableCardEntity;
import com.sq.modules.rechargeable.service.RechargeableCardService;


@Service("rechargeableCardService")
public class RechargeableCardServiceImpl extends ServiceImpl<RechargeableCardDao, RechargeableCardEntity> implements RechargeableCardService {

    @Autowired
    private RechargeableCardDao rechargeableCardDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RechargeableCardEntity> page = this.page(
                new Query<RechargeableCardEntity>().getPage(params),
                new QueryWrapper<RechargeableCardEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public boolean selectByCordNoAndcardPwd(String cardNo, String cardPwd, BigDecimal faceValue) {
        //验证参数
        if(StringUtils.isBlank(cardNo)){
            //卡号为空
            throw new RRException(ServiceCode.DB_PARAM_NULL);
        }
        if(StringUtils.isBlank(cardPwd)){
            //密码为空
            throw new RRException(ServiceCode.DB_PARAM_NULL);
        }
        if(faceValue==null){
            //面值为空
            throw new RRException(ServiceCode.DB_PARAM_NULL);
        }

        Map params =new HashMap(3);
        params.put("cardNo",cardNo);
        params.put("cardPwd",cardPwd);
        params.put("faceValue",faceValue);

        List<RechargeableCardEntity> list = rechargeableCardDao.selectByCordNoAndcardPwd(params);

        return list.size() ==0 ? false : true;
    }

    @Override
    public int updateByCardNo(String CardNo) {
        return rechargeableCardDao.updateActivity(CardNo);
    }

}