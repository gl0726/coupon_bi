package com.sq.modules.customer.service.impl;

import com.sq.common.exception.RRException;
import com.sq.common.utils.Constant;
import com.sq.common.utils.ServiceCode;
import com.sq.modules.customer.dao.CustomerSigninDao;
import com.sq.modules.customer.entity.CustomerSigninEntity;
import com.sq.modules.customer.entity.vo.SigninStatisticsVo;
import com.sq.modules.customer.service.CustomerSigninService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.common.utils.PageUtils;
import com.sq.common.utils.Query;

import javax.annotation.Resource;


/**
 * @author lq
 * @date 2019\4\17 0017 14:31
 */
@Service("customerSigninService")
public class CustomerSigninServiceImpl extends ServiceImpl<CustomerSigninDao, CustomerSigninEntity> implements CustomerSigninService {
    @Resource
    CustomerSigninService customerSigninService;
    @Resource
    CustomerSigninDao customerSigninDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        System.out.println(params.get("customerId"));
        if (!params.containsKey("customerId") || params.get("customerId").equals("")){
            throw new RRException(ServiceCode.DB_SERVICE_CUSTOMER_ID_CANNOT_BLANK);
        }
        Long customerId = Long.valueOf(params.get("customerId").toString());
        IPage<CustomerSigninEntity> page = this.page(
                new Query<CustomerSigninEntity>().getPage(params),
                new QueryWrapper<CustomerSigninEntity>().eq("customer_id",customerId)
                        .orderByDesc("sigin_date")
        );

        return new PageUtils(page);
    }


    @Override
    public Boolean saveSigin(Long customerId) {
        //设置用户连续签到天数
        CustomerSigninEntity customerSigninEntity = setContinuitySigin(customerId);

        customerSigninEntity.setCustomerId(customerId);
        customerSigninEntity.setSiginDate(new Date());
        customerSigninEntity.setCreateTime(new Date());
        customerSigninEntity.setRewardAmount(new BigDecimal(BigDecimal.ROUND_HALF_DOWN));
        customerSigninEntity.setEnable(Constant.ENABLE_VALID);
        return save(customerSigninEntity);
    }

    /**
     * 设置连续签到天数
     * @param customerId
     * @return
     */
    private CustomerSigninEntity setContinuitySigin(Long customerId){
        CustomerSigninEntity signinEntity = new CustomerSigninEntity();
        CustomerSigninEntity customerSigninEntity = customerSigninService.getOne(new QueryWrapper<CustomerSigninEntity>()
                .eq("customer_id",customerId)
                .orderByDesc("sigin_date"));
        if (customerSigninEntity == null){
            signinEntity.setContinuitySigin(1l);
            return signinEntity;
        }

        //判断用户当日是否已签到
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (sdf.format(new Date()).equals(sdf.format(customerSigninEntity.getSiginDate()))){
            throw new RRException(ServiceCode.DB_SERVICE_CHECKED_IN);
        }

        //获取昨天的日期
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(calendar.DATE,-1);
        String date = sdf.format(calendar.getTime());

        //设置用户连续签到天数
        if (date.equals(sdf.format(customerSigninEntity.getSiginDate()))){
            signinEntity.setContinuitySigin(customerSigninEntity.getContinuitySigin()+1);
        } else {
            signinEntity.setContinuitySigin(1l);
        }
        return signinEntity;
    }


    @Override
    public SigninStatisticsVo findSigninStatistics(Long customerId) {
        SigninStatisticsVo signinStatisticsVo = new SigninStatisticsVo();
        //查询用户连续签到次数
        Long continuitySigin = findContinuitySigin(customerId);
        signinStatisticsVo.setContinuitySigin(continuitySigin);
        //查询用户签到累计获得奖金数
        BigDecimal  bonusesTotal= customerSigninDao.findBonusesTotal(customerId);
        signinStatisticsVo.setBonusesTotal(bonusesTotal);
        return signinStatisticsVo;
    }

    /**
     * 查询用户连续签到次数
     * @param customerId
     * @return
     */
    private Long findContinuitySigin(Long customerId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar yesterday = new GregorianCalendar();
        yesterday.setTime(new Date());
        yesterday.add(yesterday.DATE,-1);
        CustomerSigninEntity customerSigninEntity = customerSigninService.getOne(new QueryWrapper<CustomerSigninEntity>()
                .select("continuity_sigin")
                .eq("customer_id",customerId).eq("DATE_FORMAT(sigin_date, '%Y-%m-%d')",sdf.format(new Date()))
                .or().eq("DATE_FORMAT(sigin_date, '%Y-%m-%d')",sdf.format(yesterday.getTime()))
                .orderByDesc("sigin_date"));
        if (customerSigninEntity == null){
            return 0l;
        } else {
            return  customerSigninEntity.getContinuitySigin();
        }
    }

}