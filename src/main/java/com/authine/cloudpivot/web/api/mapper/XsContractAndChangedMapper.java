package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.entity.XsConstractAndChanged;
import com.authine.cloudpivot.web.api.entity.CwIncome;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: weiyao
 * @Date: 2020-03-05
 * @Description: 合同变更和合同管理
 */
public interface XsContractAndChangedMapper {

    //查询合同变更数据
    public XsConstractAndChanged selectById(String id);

    //插入合同管理
    void insertXsConstract(XsConstractAndChanged xsConstractAndChanged);


    //查询收入分析表
    List<CwIncome> getCwIncomeInfo(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    //获取验收信息acceptDate,验收时间 acceptOpinion  验收备注
    List<Map<String,String>> getAcceptReportInfo(String contractId);


}
