package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.HBizAttachment;
import com.authine.cloudpivot.web.api.entity.I1uycXsContractListPay;
import com.authine.cloudpivot.web.api.entity.SwBuyContractChangeDetail;
import com.authine.cloudpivot.web.api.mapper.I1uycXsContractListPayMapper;
import com.authine.cloudpivot.web.api.service.I1uycXsContractListPayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class I1uycXsContractListPayServiceImpl implements I1uycXsContractListPayService {

    @Resource
    private I1uycXsContractListPayMapper i1uycXsContractListPayMapper;

    @Override
    public void insertI1uycXsContractListPay(List<I1uycXsContractListPay> i1uycXsContractListPayList) {
        i1uycXsContractListPayMapper.insertI1uycXsContractListPay(i1uycXsContractListPayList);
    }

    @Override
    public void insertSwBuyContractChangeDetail(SwBuyContractChangeDetail swBuyContractChangeDetail) {
        i1uycXsContractListPayMapper.insertSwBuyContractChangeDetail(swBuyContractChangeDetail);
    }

    @Override
    public void insertSwBuyFujian(HBizAttachment hBizAttachment) {
        i1uycXsContractListPayMapper.insertSwBuyFujian(hBizAttachment);
    }
}
