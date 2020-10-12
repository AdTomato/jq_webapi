package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.entity.SysCalendar;
import com.authine.cloudpivot.web.api.parameters.YearMonthDay;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-03-03 23:26
 * @Description: 月历mapper
 */
public interface CalendarMapper {
    public List<SysCalendar> getOneMonthCalendar(String userId, Integer year, Integer month);

    /* * @Author chengpunan
     * @Description //获取用户一周的月历新信息
     * @Date 2020/3/9 13:30
     * @Param [userId, yearMonthDay]
     * @return com.authine.cloudpivot.web.api.entity.SysCalendar
     **/
    SysCalendar getOneWeekCalender(@Param("userId") String userId, @Param("yearMonthDay") YearMonthDay yearMonthDay);

    SysCalendar search(SysCalendar sysCalendar);

    void updateCalendar(SysCalendar sysCalendar);

    void insertCalendar(SysCalendar sysCalendar);

    SysCalendar getOneDayCalender(String userId, Integer year, Integer month, Integer day);
}
