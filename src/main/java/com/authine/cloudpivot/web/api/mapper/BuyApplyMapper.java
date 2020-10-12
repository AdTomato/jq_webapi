package com.authine.cloudpivot.web.api.mapper;

/**
 * @Author: wangyong
 * @Date: 2020-03-06 16:33
 * @Description: 采购申请mapper
 */
public interface BuyApplyMapper {
    void changeStates(String id, String states);
}
