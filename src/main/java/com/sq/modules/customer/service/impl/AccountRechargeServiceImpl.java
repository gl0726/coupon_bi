package com.sq.modules.customer.service.impl;

import com.sq.common.exception.RRException;
import com.sq.common.utils.Constant;
import com.sq.common.utils.ServiceCode;
import com.sq.modules.customer.entity.CustomerWealthEntity;
import com.sq.modules.customer.entity.vo.AccountRechargeVo;
import com.sq.modules.customer.entity.vo.RechargeDetailVo;
import com.sq.modules.customer.service.CustomerWealthService;
import com.sq.modules.rechargeable.entity.RechargeableCardEntity;
import com.sq.modules.rechargeable.entity.RechargeableMgrEntity;
import com.sq.modules.rechargeable.service.RechargeableCardService;
import com.sq.modules.rechargeable.service.RechargeableMgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.common.utils.PageUtils;
import com.sq.common.utils.Query;

import com.sq.modules.customer.dao.AccountRechargeDao;
import com.sq.modules.customer.entity.AccountRechargeEntity;
import com.sq.modules.customer.service.AccountRechargeService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("accountRechargeService")
public class AccountRechargeServiceImpl extends ServiceImpl<AccountRechargeDao, AccountRechargeEntity> implements AccountRechargeService {
    @Resource
    AccountRechargeDao accountRechargeDao;
    @Autowired
    RechargeableCardService rechargeableCardService;
    @Autowired
    CustomerWealthService customerWealthService;
    @Autowired
    RechargeableMgrService rechargeableMgrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Query query = new Query();
        IPage page = query.getPage(params);
        if (!params.containsKey("customerId") || params.get("customerId").equals("")) {
            throw new RRException(ServiceCode.DB_SERVICE_CUSTOMER_ID_CANNOT_BLANK);
        }
        Long wealthId = accountVerify(params.get("customerId").toString()).getId();

        PageUtils pageUtils = new PageUtils(accountRechargeDao.selectPageByVo(page, wealthId));
        List<AccountRechargeVo> list = (List<AccountRechargeVo>) pageUtils.getList();
        for (AccountRechargeVo accountRechargeVo: list){
            List<RechargeDetailVo> rechargeDetailVos = accountRechargeDao.selectRechargeDatail(accountRechargeVo.getRechargeTime(),wealthId);
            accountRechargeVo.setDetailVo(rechargeDetailVos);
        }
        pageUtils.setList(list);
        return pageUtils;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int recharge(String cardNo, String pwd, String customerId) {

        //验证账户的有效性
        CustomerWealthEntity customerWealthEntity = accountVerify(customerId);

        //校验充值卡是否有效
        RechargeableCardEntity rechargeableCard = cardVerify(cardNo, pwd);

        //查询并校验充值卡的面额
        RechargeableMgrEntity rechargeableMgr = findFaceValue(rechargeableCard);

        //充值，修改用户账户余额
        updateCustomerWealth(customerWealthEntity, rechargeableMgr);

        //保存充值记录
        saveRechargeEntity(cardNo,customerWealthEntity.getId(), rechargeableMgr);

        //修改充值卡的激活状态
        rechargeableMgr.setUpdateTime(new Date());
        updateRechargeableCard(rechargeableCard);
        return Constant.ENABLE_VALID;
    }

    /**
     * 修改充值卡的激活状态
     *
     * @param rechargeableCard
     */
    private void updateRechargeableCard(RechargeableCardEntity rechargeableCard) {
        rechargeableCard.setActivationState(Constant.ENABLE_VALID);
        boolean id = rechargeableCardService.update(rechargeableCard, new QueryWrapper<RechargeableCardEntity>()
                .eq("id", rechargeableCard.getId()));
        if (!id) {
            throw new RRException(ServiceCode.DB_SERVICE_RECHARGE_FAILURE);
        }
    }

    /**
     * 充值，修改用户账户余额
     *
     * @param customerWealthEntity
     * @param rechargeableMgr
     */
    private void updateCustomerWealth(CustomerWealthEntity customerWealthEntity, RechargeableMgrEntity rechargeableMgr) {
        customerWealthEntity.setAmount(customerWealthEntity.getAmount().add(rechargeableMgr.getFaceValue()));
        boolean update = customerWealthService.update(customerWealthEntity);
        if (!update) {
            throw new RRException(ServiceCode.DB_SERVICE_RECHARGE_FAILURE);
        }
    }

    /**
     * 保存充值记录
     *
     * @param cardNo
     * @param wealthId
     * @param rechargeableMgr
     */
    private void saveRechargeEntity(String cardNo, Long wealthId, RechargeableMgrEntity rechargeableMgr) {
        AccountRechargeEntity rechargeEntity = new AccountRechargeEntity();
        rechargeEntity.setWealthId(wealthId);
        rechargeEntity.setRechargeCardno(Long.parseLong(cardNo));
        rechargeEntity.setRechargeAmount(rechargeableMgr.getFaceValue());
        rechargeEntity.setRechargeTime(new Date());
        rechargeEntity.setCreateTime(new Date());
        rechargeEntity.setEnable(Constant.ENABLE_VALID);
        boolean save = save(rechargeEntity);
        if (!save) {
            throw new RRException(ServiceCode.DB_SERVICE_RECHARGE_FAILURE);
        }
    }

    /**
     * 查询并校验充值卡的面额
     *
     * @param rechargeableCard
     * @return
     */
    private RechargeableMgrEntity findFaceValue(RechargeableCardEntity rechargeableCard) {
        RechargeableMgrEntity rechargeableMgr = rechargeableMgrService.getOne(new QueryWrapper<RechargeableMgrEntity>()
                .eq("id", rechargeableCard.getManagerId()));
        if (rechargeableMgr == null || rechargeableMgr.getEnable().equals(Constant.ENABLE_INVALID)) {
            throw new RRException(ServiceCode.DB_SERVICE_CARD_ERROR);
        }
        return rechargeableMgr;
    }


    /**
     * 校验充值卡
     *
     * @param cardNo
     * @return
     */
    private RechargeableCardEntity cardVerify(String cardNo, String pwd) {
        RechargeableCardEntity rechargeableCard = rechargeableCardService.getOne(new QueryWrapper<RechargeableCardEntity>()
                .eq("card_no", cardNo)
                .eq(Constant.ENABLE, Constant.ENABLE_VALID));
        if (rechargeableCard == null) {
            throw new RRException(ServiceCode.DB_SERVICE_CARD_NOT_EXIST);
        }
        if (null == rechargeableCard.getActivationState() || rechargeableCard.getActivationState().equals(Constant.ENABLE_VALID)) {
            throw new RRException(ServiceCode.DB_SERVICE_CARD_ISUSED);
        }
        if (!rechargeableCard.getCardPwd().equals(pwd)) {
            throw new RRException(ServiceCode.DB_SERVICE_PASSWORD_ERROR);
        }
        if (System.currentTimeMillis() > rechargeableCard.getFailureTime().getTime()) {
            throw new RRException(ServiceCode.DB_SERVICE_CARD_PAST_DUE);
        }
        return rechargeableCard;
    }


    /**
     * 校验用户账户
     *
     * @param customerId
     * @return
     */
    private CustomerWealthEntity accountVerify(String customerId) {
        CustomerWealthEntity customerWealthEntity = customerWealthService.getByCustomerId(Long.parseLong(customerId));

        if (customerWealthEntity == null) {
            throw new RRException(ServiceCode.DB_SERVICE_ACCOUNT_NOT_EXIST);
        }
        if (!customerWealthEntity.getEnable().equals(Constant.ENABLE_VALID)) {
            throw new RRException(ServiceCode.DB_SERVICE_INVALID_ACCOUNT);
        }
        return customerWealthEntity;
    }

}