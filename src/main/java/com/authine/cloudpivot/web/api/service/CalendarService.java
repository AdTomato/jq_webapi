package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.entity.SysCalendar;
import com.authine.cloudpivot.web.api.parameters.YearMonthDay;
import org.apache.catalina.LifecycleState;

import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-03-03 22:54
 * @Description: 月历service接口
 */
public interface CalendarService {
    List<SysCalendar> getOneMonthCalendar(String userId, Integer year, Integer month);

    /**
     * @Author lfh
     * @Description //获取用户一周的月历新信息
     * @Date 2020/3/9 13:27
     * @Param [userId, yearMonthDay]
     * @return com.authine.cloudpivot.web.api.entity.SysCalendar
     **/
    SysCalendar getOneWeekCalender(String userId, YearMonthDay yearMonthDay);

    /* * @Author lfh
     * @Description //通过id查询是否有该用户的月历信息
     * @Date 2020/3/11 10:57
     * @Param [id]
     * @return com.authine.cloudpivot.web.api.entity.SysCalendar
     **/
    SysCalendar search(SysCalendar sysCalendar);

    /* * @Author lfh
     * @Description //更新用的月历信息
     * @Date 2020/3/11 10:58
     * @Param [sysCalendar]
     * @return void
     **/
    void updateCalendar(SysCalendar sysCalendar);

    void insertCalendar(SysCalendar sysCalendar);

    SysCalendar getOneDayCalender(String userId, Integer year, Integer month, Integer day);
}
