package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.mapper.BuyApplyMapper;
import com.authine.cloudpivot.web.api.service.BuyApplyService;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: wangyong
 * @Date: 2020-03-06 16:35
 * @Description: 采购申请service
 */
@Service
public class BuyApplyServiceImpl implements BuyApplyService {

    @Resource
    BuyApplyMapper buyApplyMapper;

    @Override
    public void changeStates(String id, String states) {
        buyApplyMapper.changeStates(id, states);
    }
}
