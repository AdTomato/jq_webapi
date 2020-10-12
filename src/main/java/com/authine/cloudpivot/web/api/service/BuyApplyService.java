package com.authine.cloudpivot.web.api.service;

/**
 * @Author: wangyong
 * @Date: 2020-03-06 16:35
 * @Description: 采购申请service接口
 */
public interface BuyApplyService {
    void changeStates(String id, String states);
}
