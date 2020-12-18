package com.authine.cloudpivot.ext.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 日报明细
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/12/18 15:50
 */
@Getter
@Setter
@EqualsAndHashCode
public class DailyDetail {
    private String workOrderId;
    private String workFlowType;
    private String title;
    private String workOrderType;
    private String workContent;
    private String beginDate;
    private String endDate;
    private String workPersons;
    private String workResult;
    private Double workingHours;

    private List<Map<String, String>> userList;
}
