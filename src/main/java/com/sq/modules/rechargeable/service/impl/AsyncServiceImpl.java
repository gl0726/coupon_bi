package com.sq.modules.rechargeable.service.impl;

import com.sq.modules.rechargeable.service.AsyncService;
import com.sq.modules.rechargeable.service.RechargeableCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author jhy
 * @date 2019\4\9 0009 15:24
 */
@Service("asyncService")
public class AsyncServiceImpl implements AsyncService {
    private static Logger logger = LoggerFactory.getLogger(RechargeableMgrServiceImpl.class);

    @Autowired
    private RechargeableCardService rechargeableCardService;

    @Override
    @Async("asyncServiceExecutor")
    public  void executeAsyncSave(List list) {
        logger.info("start executeAsync"+Thread.currentThread().getName()+new Date());

        System.out.println("异步线程要做的事情");
        System.out.println("可以在这里执行批量插入等耗时的事情");
        rechargeableCardService.saveBatch(list);
        logger.info("end executeAsync"+Thread.currentThread().getName()+new Date());
    }
}
