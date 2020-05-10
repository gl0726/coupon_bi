package com.sq.modules.customer.cache;

import com.sq.modules.customer.entity.CustomerThirdpartyEntity;

import java.util.List;

/**
 * @Classname CustomerThirdpartyCacheService
 * @Description TODO
 * @Date 2019\4\3 0003 14:10
 * @Created by WenJunChi
 */
public interface CustomerThirdpartyCacheService {
    List<CustomerThirdpartyEntity> getOneByUserId(Long id);

    CustomerThirdpartyEntity getOneBySourceId(String sourceId, Integer partyId);

    CustomerThirdpartyEntity save(CustomerThirdpartyEntity customerThirdpartyEntity);
}
