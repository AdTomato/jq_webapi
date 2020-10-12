package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.mapper.SaleApplyMapper;
import com.authine.cloudpivot.web.api.service.SaleApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: wangyong
 * @Date: 2020-03-06 13:35
 * @Description:
 */
@Service
public class SaleApplyServiceImpl implements SaleApplyService {

    @Resource
    SaleApplyMapper saleApplyMapper;

    @Override
    public void changeStates(String id, String states) {
        saleApplyMapper.changeStates(id, states);
    }
}
