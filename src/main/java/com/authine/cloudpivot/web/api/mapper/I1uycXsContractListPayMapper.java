package com.authine.cloudpivot.web.api.mapper;


import com.authine.cloudpivot.web.api.entity.HBizAttachment;
import com.authine.cloudpivot.web.api.entity.I1uycXsContractListPay;
import com.authine.cloudpivot.web.api.entity.SwBuyContractChangeDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: weiyao
 * @Date: 2020-02-26
 * @Description: 合同变更子表
 */
public interface I1uycXsContractListPayMapper {

    //批量插入合同变更子表
    public void insertI1uycXsContractListPay(List<I1uycXsContractListPay> i1uycXsContractListPayList);

    //批量插入采购合同变更子表
    public void insertSwBuyContractChangeDetail(SwBuyContractChangeDetail swBuyContractChangeDetail);

    //插入附件
    public void insertSwBuyFujian(HBizAttachment hBizAttachment);

    //获取合同变更子表信息
    List<I1uycXsContractListPay> getChangeByParentId(String parentId);

    //获取采购合同变更子表信息
    List<SwBuyContractChangeDetail> getSwChangeByParentId(String parentId);

    HBizAttachment getFujianInfo(@Param("bizObjectId") String bizObjectId, @Param("parentBizObjectId") String parentBizObjectId);
}
