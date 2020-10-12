package com.authine.cloudpivot.web.api.parameters;

import lombok.Data;

import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-03-06 17:46
 * @Description: 月历控制类，获取日信息
 */
@Data
public class GetOneWeekCalendarParameter {
    private String userId;
    private List<YearMonthDay> yearMonthDays;
}
