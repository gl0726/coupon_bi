package com.sq.modules.rechargeable.service.impl;

import com.google.common.collect.Lists;
import com.sq.common.exception.RRException;
import com.sq.common.utils.*;
import com.sq.modules.rechargeable.entity.*;
import com.sq.modules.rechargeable.service.*;
import com.sq.modules.sys.redis.SysConfigRedis;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.sq.modules.rechargeable.dao.RechargeableMgrDao;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("rechargeableMgrService")
public class RechargeableMgrServiceImpl extends ServiceImpl<RechargeableMgrDao, RechargeableMgrEntity> implements RechargeableMgrService {

    private static Logger logger = LoggerFactory.getLogger(RechargeableMgrServiceImpl.class);

    //密码长度
    private static final int PWD_LENGTH = 8;

    private static final String MAKE_CARD_FAIL = "制卡失败,请重试!";

    private static final Integer CARD_PART_NUM = 5000;

    private static final String ACTIVITY_ABLE = "已激活";

    private static final String ACTIVITY_ENABLE = "未激活";
    //序列号前缀
    private static final String SUQUENCE_PREFIX = "SN";
    //序列号长度
    private static final int SUQUENCE_NUMLENGTH=12;
    //redis中的key
    private static  final String NUMBER="number";
    private static  final String CARD_NUM="cardNum";

    //number长度
    private static  final int LENGTH=3;
    @Autowired
    private RechargeableCardService rechargeableCardService;

    @Resource
    private RechargeableMgrDao rechargeableMgrDao;
    @Autowired
    private AsyncService asyncService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private TransferRecordService transferRecordService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RechargeableMgrEntity> page = this.page(
                new Query<RechargeableMgrEntity>().getPage(params),
                new QueryWrapper<RechargeableMgrEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageCondition(Map<String, Object> params) {
        IPage page = new Query<RechargeableCardEntityVo>().getPage(params);
        Short activationState = (Short) params.get("activationState");
        Long bdId = (Long) params.get("bdId");
        String batchNo = (String) params.get("batchNo");
        PageUtils pages = new PageUtils(rechargeableMgrDao.selectPageVo(page, activationState, bdId, batchNo));
        List list = pages.getList();
        pages.setList(parseList(list));
        return pages;
    }

    private List parseList(List list) {
        List newList = new ArrayList();
        for (Object entity : list
        ) {
            if(entity instanceof RechargeableCardEntityVo){
                RechargeableCardEntityVo entityVo= (RechargeableCardEntityVo) entity;
                entityVo.setStrDate(DateUtils.format(entityVo.getStartTime())+"至"+DateUtils.format(entityVo.getFailureTime()));
                entityVo.setActivation(entityVo.getActivationState()==0 ? ACTIVITY_ENABLE : ACTIVITY_ABLE);
                entityVo.setDurationTime(entityVo.getDuration() == 1 ? "30天" :entityVo.getDuration() == 2 ? "3个月" : entityVo.getDuration() == 3 ? "6个月" : "一年" );
                newList.add(entityVo);
            }
        }
        return newList;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public int createRechargeable(RechargeableMgrEntity rechargeableMgr) {
        //获取今天的批次
        String batchNumber = String.valueOf(getStartBatchNumber());
        rechargeableMgr.setBatchNumber(batchNumber);
        rechargeableMgr.setCreateTime(new Date());
        rechargeableMgr.setEnable(Constant.ENABLE_VALID);
        checheData(rechargeableMgr);
        //插入卡管理表
        boolean r = save(rechargeableMgr);
        if (!r) {
            throw new RRException(MAKE_CARD_FAIL);
        }

        //获取号段
        String numberStr=seqGenerator(NUMBER,LENGTH).substring(NUMBER.length());
        //获得要插入的卡集合
        List rechargeableCardEntityList = getParseCardList(rechargeableMgr,batchNumber,numberStr);

        List list = averageAssign(rechargeableCardEntityList, CARD_PART_NUM);
        //多线程异步插入
        for (int i = 0; i < list.size(); i++) {
            asyncService.executeAsyncSave((List) list.get(i));
        }
        //获得公司实体
        CompanyEntity companyEntity =getCompany(rechargeableMgr,numberStr);

        //插入公司表
        companyService.save(companyEntity);

        return rechargeableCardEntityList.size();
    }

    private CompanyEntity  getCompany(RechargeableMgrEntity rechargeableMgr,String numberStr){
        CompanyEntity companyEntity=new CompanyEntity();
        companyEntity.setName(rechargeableMgr.getTitle());
        companyEntity.setMgrId(rechargeableMgr.getId());
        companyEntity.setNumber(numberStr);
        companyEntity.setEnable(Constant.ENABLE_VALID);
        companyEntity.setCreateId(rechargeableMgr.getCreateId());
        companyEntity.setCreateTime(new Date());
        return companyEntity;
    }

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 获得要插入的卡集合
     * @param rechargeableMgr
     * @param batchNumber
     * @return
     */
    private List getParseCardList(RechargeableMgrEntity rechargeableMgr,String batchNumber,String numberStr){
        List<RechargeableCardEntity> rechargeableCardEntityList = new ArrayList<>();
        for (int i = 0; i < rechargeableMgr.getCount(); i++) {
            RechargeableCardEntity rechargeableCardEntity = new RechargeableCardEntity();
            rechargeableCardEntity.setManagerId(rechargeableMgr.getId());


            //序列号
            rechargeableCardEntity.setSequence(seqGenerator(SUQUENCE_PREFIX,SUQUENCE_NUMLENGTH));

            //批次
            rechargeableCardEntity.setBatchNo(batchNumber);
            rechargeableCardEntity.setCreateTime(new Date());
            rechargeableCardEntity.setCreateId(rechargeableMgr.getCreateId());
            //卡号
            rechargeableCardEntity.setCardNo(getCardNo(numberStr));
            //bd_id
            rechargeableCardEntity.setBdId(rechargeableMgr.getBdId());
            //未激活
            rechargeableCardEntity.setActivationState(Constant.ENABLE_INVALID);
            rechargeableCardEntity.setFailureTime(rechargeableMgr.getDuration() == 1 ? DateUtils.addDateDays(rechargeableMgr.getStartDate(), 30) :
                    rechargeableMgr.getDuration() == 2 ? DateUtils.addDateMonths(rechargeableMgr.getStartDate(), 3) :
                            rechargeableMgr.getDuration() == 3 ? DateUtils.addDateMonths(rechargeableMgr.getStartDate(), 6) :
                                    DateUtils.addDateYears(rechargeableMgr.getStartDate(), 1));
            ///失效时间 规则：1、30天 2、3个月 3、6个月 4 一年
            rechargeableCardEntity.setCardPwd(RandomUtil.getStringRandom(PWD_LENGTH));
            rechargeableCardEntity.setEnable(Constant.ENABLE_VALID);
            rechargeableCardEntityList.add(rechargeableCardEntity);
        }
        return rechargeableCardEntityList;
    }
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 获取自增序列号
     * @param prefix
     * @param numLength
     * @return
     */
    public String seqGenerator(String prefix,int numLength){
        String upperCode ="";
        String returnCode="";
        int Suffix;  //后缀数字 if (!StringUtil.isNullOrEmpty(upperCode)){ //有数据
        Long size=redisTemplate.opsForList().size(prefix);//查找 prefix 的key值的数据长度
        if(size>0){//有数据
            List leve =redisTemplate.opsForList().range(prefix,0,-1);//获取该key下面的所有值(-1 所有的值，；1下一个值
            upperCode=leve.get(leve.size()-1).toString();//返回最后一个值
            String sequence =upperCode.substring(prefix.length());//截取前缀开始的后面的数字
            Suffix=Integer.parseInt(sequence);
            Suffix++;//最后的序号加一
        }else{
            Suffix=1;//没有数据
        }
        returnCode=prefix+String.format("%0"+numLength+"d",Suffix);//后缀不够numLength长，前面补充0
        redisTemplate.opsForList().rightPush(prefix,returnCode);//存入Redis
        System.out.println(returnCode+"%%%");
        return  returnCode;
    }


    /**
     * 切割集合
     *
     * @param source
     * @param bccSize
     * @param <T>
     * @return
     */
    private <T> List<List<T>> averageAssign(List<T> source, int bccSize) {
        List<List<T>> result = Lists.newArrayList();
        int mod = source.size() % bccSize;
        int part = source.size() / bccSize;
        for (int i = 0; i < source.size(); i = i + bccSize) {
            int offset = source.size() - i < bccSize ? source.size() : i + bccSize;
            List<T> tmpList = source.subList(i, offset);
            result.add(tmpList);
        }

        return result;
    }


    @Override
    public List<RechargeableCardEntityVo> selectExcelVo(Short activationState, Long bdId, String batchNo) {
        return parseList(rechargeableMgrDao.selectPageVo(activationState, bdId, batchNo));
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateBd(Long bdId,String beginSequence,String endSequence,Long userId) {
        Map params=new HashMap(3);
        params.put("bdId",bdId);
        params.put("beginSequence",beginSequence);
        params.put("endSequence",endSequence);
        params.put("createId",userId);
        rechargeableMgrDao.updateBd(params);
        //封装调拨记录
        TransferRecordEntity entity=new TransferRecordEntity();
        entity.setStartSeq(beginSequence);
        entity.setEndSeq(endSequence);
        entity.setBdId(bdId);
        entity.setCreateTime(new Date());
        entity.setCreateId(userId);
        entity.setEnable(Constant.ENABLE_VALID);
        //增加调拨记录
        transferRecordService.save(entity);

    }

    /**
     * 获取卡号
     *
     * @param number
     * @return
     */
    public String getCardNo(String number) {

        return number+seqGenerator(CARD_NUM,9).substring(CARD_NUM.length());
    }
/*    public String getCardNo(String batchNumber) {
        return DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN_YY) + RandomUtil.getRandom(11) + batchNumber.substring(batchNumber.length() - 1, batchNumber.length());
    }*/

    private Long getStartBatchNumber() {
        List<RechargeableMgrEntity> list = list(new QueryWrapper<RechargeableMgrEntity>().gt("create_time", DateUtils.getStart()).le("create_time", new Date()).orderByDesc("create_time"));
        if (CollectionUtils.isEmpty(list)) {
            return Long.parseLong(DateUtils.format(new Date(), DateUtils.DATETIMEPATTERN) + 1);
        } else {
            String batchNumber = list.get(0).getBatchNumber();
            long l = Long.parseLong(batchNumber);
            return l + 1;
        }

    }


    private void checheData(RechargeableMgrEntity rechargeableMgr) {
        if (rechargeableMgr.getCount() == null) {
            throw new RRException(ServiceCode.DB_PARAM_NULL);//数量为空
        }
        if (rechargeableMgr.getFaceValue() == null) {
            throw new RRException(ServiceCode.DB_PARAM_NULL);//面值为空
        }
        if (rechargeableMgr.getBdId() == null) {
            throw new RRException(ServiceCode.DB_PARAM_NULL);//BD为空
        }
        if (rechargeableMgr.getStartDate() == null) {
            throw new RRException(ServiceCode.DB_PARAM_NULL);//数量为空
        }


    }


}