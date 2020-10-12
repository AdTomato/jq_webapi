package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.entity.HBizAttachment;
import com.authine.cloudpivot.web.api.entity.I1uycXsContractListPay;
import com.authine.cloudpivot.web.api.entity.SwBuyContractChangeDetail;

import java.util.List;

/**
 * @Author: weiyao
 * @Date: 2020-02-26
 * @Description: 合同变更子表
 */
public interface I1uycXsContractListPayService {

    //批量插入合同变更子表
   public void insertI1uycXsContractListPay(List<I1uycXsContractListPay> i1uycXsContractListPayList);

    //批量插入采购合同变更子表
    public void insertSwBuyContractChangeDetail(SwBuyContractChangeDetail swBuyContractChangeDetail);

    //插入附件
    public void insertSwBuyFujian(HBizAttachment hBizAttachment);
}
