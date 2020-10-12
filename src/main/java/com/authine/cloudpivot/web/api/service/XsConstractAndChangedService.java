package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.controller.CwIncomeController;
import com.authine.cloudpivot.web.api.entity.CwIncome;

import java.util.Date;
import java.util.List;

/**
 * @Author: weiyao
 * @Date: 2020-03-06
 * @Description: 合同变更he子表
 */
public interface XsConstractAndChangedService {

    public String insertXsConstract(String id);

    public List<CwIncome> getCwIncomeList(Date startDate, Date endDate);
}
