package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.entity.SysCalendar;
import com.authine.cloudpivot.web.api.mapper.CalendarMapper;
import com.authine.cloudpivot.web.api.parameters.YearMonthDay;
import com.authine.cloudpivot.web.api.service.CalendarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-03-03 23:27
 * @Description: 月历service
 */
@Service
public class CalendarServiceImpl implements CalendarService {

    @Resource
    CalendarMapper calendarMapper;

    /**
     * @param userId : 用户id
     * @param year   : 年
     * @param month  : 月
     * @return : java.util.List<com.authine.cloudpivot.web.api.entity.SysCalendar>
     * @Author: wangyong
     * @Date: 2020/3/3 23:40
     * @Description: 根据用户id和年份，月份获取该用户的月历信息
     */
    @Override
    public List<SysCalendar> getOneMonthCalendar(String userId, Integer year, Integer month) {
        return calendarMapper.getOneMonthCalendar(userId, year, month);
    }

    /**
     * @Author lfh
     * @Description //获取用户一周的月历新信息
     * @Date 2020/3/9 13:28
     * @Param [userId, yearMonthDay]  用户id ， 年月日
     * @return com.authine.cloudpivot.web.api.entity.SysCalendar
     **/
    @Override
    public SysCalendar getOneWeekCalender(String userId, YearMonthDay yearMonthDay) {
        return calendarMapper.getOneWeekCalender(userId,yearMonthDay);
    }

    /* * @Author lfh
     * @Description //通过id查询是否有该用户的月历信息
     * @Date 2020/3/11 10:57
     * @Param [id]
     * @return com.authine.cloudpivot.web.api.entity.SysCalendar
     **/
    @Override
    public SysCalendar search(SysCalendar sysCalendar) {
        return calendarMapper.search(sysCalendar);
    }

    /* * @Author lfh
     * @Description //更新用户的月历信息
     * @Date 2020/3/11 11:02
     * @Param [sysCalendar]
     * @return void
     **/
    @Override
    public void updateCalendar(SysCalendar sysCalendar) {
        calendarMapper.updateCalendar(sysCalendar);
    }

    @Override
    public void insertCalendar(SysCalendar sysCalendar) {
        calendarMapper.insertCalendar(sysCalendar);
    }

    @Override
    public SysCalendar getOneDayCalender(String userId, Integer year, Integer month, Integer day) {
        return calendarMapper.getOneDayCalender(userId, year, month, day);
    }
}
