package com.sq.modules.customer.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.sq.common.exception.RRException;
import com.sq.common.utils.Constant;
import com.sq.common.utils.ServiceCode;
import com.sq.modules.customer.dao.AccountConsumeDao;
import com.sq.modules.customer.entity.AccountConsumeEntity;
import com.sq.modules.customer.entity.CustomerWealthEntity;
import com.sq.modules.customer.entity.vo.AccountConsumeVo;
import com.sq.modules.customer.entity.vo.ConsumeDetailVo;
import com.sq.modules.customer.service.AccountConsumeService;
import com.sq.modules.customer.service.CustomerWealthService;
import com.sq.modules.sys.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.common.utils.PageUtils;
import com.sq.common.utils.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("accountConsumeService")
public class AccountConsumeServiceImpl extends ServiceImpl<AccountConsumeDao, AccountConsumeEntity> implements AccountConsumeService {
    @Resource
    AccountConsumeDao accountConsumeDao;
    @Autowired
    SysConfigService sysConfigService;

    @Autowired
    CustomerWealthService customerWealthService;

//    @Autowired
//    RedisTemplate redisTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        Query query = new Query();
        IPage page = query.getPage(params);
        if (!params.containsKey("customerId") || params.get("customerId").equals("")) {
            throw new RRException(ServiceCode.DB_SERVICE_CUSTOMER_ID_CANNOT_BLANK);
        }
        String wealthId = params.get("customerId").toString();
        PageUtils pageUtils = new PageUtils(accountConsumeDao.selectPageByVo(page, wealthId));
        List<AccountConsumeVo> accountConsumeVos = (List<AccountConsumeVo>) pageUtils.getList();
        for (AccountConsumeVo accountConsumeVo : accountConsumeVos) {
            List<ConsumeDetailVo> consumeDetailVos = accountConsumeDao.selectConsumeDatail(accountConsumeVo.getDissipateDate(), wealthId);
            consumeDetailVos = setTitle(consumeDetailVos);
            accountConsumeVo.setConsumeDetailVos(consumeDetailVos);
        }
        pageUtils.setList(accountConsumeVos);
        return pageUtils;
    }


    /**
     * 获取第三方平台名称
     * @param consumeDetailVos
     * @return
     */
    private List<ConsumeDetailVo> setTitle(List<ConsumeDetailVo> consumeDetailVos) {
        String value = sysConfigService.getValue("CAT_LIST");
        if (null == value){
            throw new RRException("找不到CAT_LIST");
        }
        List<List<Map<String, Object>>> userTags = (List<List<Map<String, Object>>>) JSONArray.parse(value);
        for (ConsumeDetailVo consumeDetailVo : consumeDetailVos) {
            userTags.forEach(list -> {
                list.forEach(map -> {
                    String cat_id = map.get("cat_id").toString();
                    if (cat_id.equals(consumeDetailVo.getDeduction())) {
                        consumeDetailVo.setTitle(map.get("title").toString());
                    }
                });
            });
        }
        return consumeDetailVos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deduction(AccountConsumeEntity accountConsume) {
        //校验用户账户
        CustomerWealthEntity customerWealth = customerVerify(accountConsume.getCustomerId(), accountConsume.getDeductionAmount());
        //更新账户余额
        updateCustomerWealth(accountConsume.getDeductionAmount(), customerWealth);
        //保存抵扣记录
        saveAccountConsume(accountConsume);
        return Constant.ENABLE_VALID;
    }


    /**
     * 优惠券查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryCouponList(Map<String, Object> params) {
        //校验customerId与type
        parameterVerify(params);
        int type = Integer.valueOf(String.valueOf(params.get("type")));
        long customerId = Long.valueOf(String.valueOf(params.get("customerId")));
        QueryWrapper<AccountConsumeEntity> queryWrapper = new QueryWrapper<AccountConsumeEntity>()
                .select("customer_id,deduction_amount,sku_name,pic_url,START_TIME,end_time")
                .eq("customer_id",customerId);
        //拼接筛选条件，0：查询已过期的，1:有效的，2:查询所有
        if (type == 0){
            queryWrapper = queryWrapper.lt("UNIX_TIMESTAMP(end_time)",System.currentTimeMillis()/1000);
        }
        if (type == 1){
            queryWrapper = queryWrapper.gt("UNIX_TIMESTAMP(end_time)",System.currentTimeMillis()/1000);
        }
        IPage<AccountConsumeEntity> page = this.page(
                new Query<AccountConsumeEntity>().getPage(params),
                queryWrapper
        );
        //设置优惠券状态,0:已过期，1：有效
        PageUtils pageUtils = new PageUtils(page);
        List<AccountConsumeEntity> list = (List<AccountConsumeEntity>) pageUtils.getList();
        list.forEach(accountConsumeEntity->{
            if (accountConsumeEntity.getEndTime().getTime() > System.currentTimeMillis()){
                accountConsumeEntity.setCouponEnable(Constant.ENABLE_VALID);
            }
            if (accountConsumeEntity.getEndTime().getTime() < System.currentTimeMillis()){
                accountConsumeEntity.setCouponEnable(Constant.ENABLE_INVALID);
            }
        });
        pageUtils.setList(list);
        return pageUtils;
    }


    /**
     * 校验查询参数
     * @param params
     */
    private void parameterVerify(Map<String,Object> params){
        if (!params.containsKey("customerId") || params.get("customerId").equals("")){
            throw new RRException(ServiceCode.DB_SERVICE_CUSTOMER_ID_CANNOT_BLANK);
        }
        if (!params.containsKey("type") || params.get("type").equals("")){
            throw new RRException(ServiceCode.DB_PARAM_NULL);
        }
    }

    /**
     * 保存抵扣记录
     *
     * @param accountConsume
     */
    private void saveAccountConsume(AccountConsumeEntity accountConsume) {
        accountConsume.setDissipateDate(new Date());
        accountConsume.setCreateTime(new Date());
        accountConsume.setEnable(Constant.ENABLE_VALID);
        boolean save = save(accountConsume);
        if (!save) {
            throw new RRException(ServiceCode.DB_SERVICE_DEDUCTION_FAILURE);
        }
    }

    /**
     * 更新账户余额
     *
     * @param deductionAmount
     * @param customerWealth
     */
    private void updateCustomerWealth(BigDecimal deductionAmount, CustomerWealthEntity customerWealth) {
        customerWealth.setAmount(customerWealth.getAmount().subtract(deductionAmount));
        Boolean update = customerWealthService.update(customerWealth);
        if (!update) {
            throw new RRException(ServiceCode.DB_SERVICE_DEDUCTION_FAILURE);
        }
    }


    /**
     * 校验用户账户
     *
     * @param customerId
     * @param deductionAmount
     * @return
     */
    private CustomerWealthEntity customerVerify(Long customerId, BigDecimal deductionAmount) {
        CustomerWealthEntity customerWealth = customerWealthService.getOne(new QueryWrapper<CustomerWealthEntity>()
                .eq("customer_id", customerId));
        if (customerWealth == null) {
            throw new RRException(ServiceCode.DB_SERVICE_ACCOUNT_NOT_EXIST);
        }
        if (!customerWealth.getEnable().equals(Constant.ENABLE_VALID)) {
            throw new RRException(ServiceCode.DB_SERVICE_INVALID_ACCOUNT);
        }
        if (customerWealth.getAmount().compareTo(deductionAmount) == -1) {
            throw new RRException(ServiceCode.DB_SERVICE_BALANCE_NOT_ENOUGH);
        }
        return customerWealth;
    }

}