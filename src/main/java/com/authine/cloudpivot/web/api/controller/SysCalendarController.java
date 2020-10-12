package com.authine.cloudpivot.web.api.controller;

import com.alibaba.fastjson.JSON;
import com.authine.cloudpivot.engine.api.facade.BizObjectFacade;
import com.authine.cloudpivot.engine.api.model.runtime.BizObjectModel;
import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.entity.SysCalendar;
import com.authine.cloudpivot.web.api.parameters.YearMonthDay;
import com.authine.cloudpivot.web.api.service.CalendarService;
import com.authine.cloudpivot.web.api.utils.UserUtils;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangyong
 * @Date: 2020-03-03 17:16
 * @Description: 月历controller
 */
@RestController
@RequestMapping("/controller/calendar")
@Slf4j
public class SysCalendarController extends BaseController {

    @Autowired
    CalendarService calendarService;

    /**
     * @param userId : 用户id
     * @param year   : 年
     * @param month  : 月
     * @return : com.authine.cloudpivot.web.api.view.ResponseResult<java.lang.Object>
     * @Author: wangyong
     * @Date: 2020/3/3 23:29
     * @Description: 根据一个人的id，年份，月份获取这个人的月历信息
     */
    @GetMapping("/getOneMonthCalendar")
    public ResponseResult<Object> getOneMonthCalendar(@RequestParam String userId, @RequestParam Integer year, @RequestParam Integer month) {
        List<SysCalendar> calendars = calendarService.getOneMonthCalendar(userId, year, month);

        List<Map<String, Object>> result = new ArrayList<>();
        log.info("查询到的月历：" + calendars);
        for (SysCalendar calendar : calendars) {
            if (calendar == null) {
                log.info("查询出来的数据为空");
                continue;
            }
            Map<String, Object> data = new HashMap<>();
            List<String> months = new ArrayList<>();
            if (calendar.getMonth() == null)
                continue;
            months.add(calendar.getMonth() < 10 ? "0" + calendar.getMonth().intValue() : calendar.getMonth().intValue() + "");
            data.put("months", months);
            List<String> days = new ArrayList<>();
            if (calendar.getDay() == null)
                continue;
            days.add(calendar.getDay() < 10 ? "0" + calendar.getDay().intValue() : calendar.getDay().intValue() + "");
            data.put("days", days);
            String things;
            if ("内勤".equals(calendar.getDayType())) {
                things = "内勤";
            } else {
                things = calendar.getDayType() + "-" + calendar.getWorkSetting() + "-" + calendar.getRelatedName();
            }
            data.put("things", things);
            result.add(data);
        }

        return this.getErrResponseResult(result, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    /**
     * @param parameter : 年月日参数
     * @return : com.authine.cloudpivot.web.api.view.ResponseResult<java.lang.Object>
     * @Author: wangyong
     * @Date: 2020/3/6 17:50
     * @Description: 获取用户一周的月历新信息
     */
    @PostMapping("/getOneWeekCalendar")
    public ResponseResult<Object> getOneWeekCalendar(@RequestBody(required = true) List<YearMonthDay> parameter) {
        String userId = getUserId();
        if (StringUtils.isEmpty(userId)) {
            return this.getErrResponseResult(null, 404L, "不存在用户");
        }
        List<SysCalendar> calendars = new ArrayList<>();
        for (YearMonthDay yearMonthDay : parameter) {
            SysCalendar oneDayCalender = calendarService.getOneDayCalender(userId, yearMonthDay.getYear(), yearMonthDay.getMonth(), yearMonthDay.getDay());
            if (null != oneDayCalender) {
                calendars.add(oneDayCalender);
            }
        }
        return this.getErrResponseResult(calendars, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    /**
     * @param userId: 用户
     * @param year:   年
     * @param month:  月
     * @param day:    日
     * @Author: wangyong
     * @Date: 2020/3/16 0:16
     * @return: com.authine.cloudpivot.web.api.view.ResponseResult<java.lang.Object>
     * @Description: 获取某一天的月历数据
     */
    @GetMapping("/getOneDayCalendar")
    public ResponseResult<Object> getOneDayCalendar(@RequestParam String userId, @RequestParam Integer year, @RequestParam Integer month, @RequestParam Integer day) {
        SysCalendar oneDayCalender = calendarService.getOneDayCalender(userId, year, month, day);
        return this.getErrResponseResult(oneDayCalender, ErrCode.OK.getErrCode(), "获取日历数据成功");
    }

    /**
     * @param calendars : 月历集合
     * @return : com.authine.cloudpivot.web.api.view.ResponseResult<java.lang.Object>
     * @Author: wangyong
     * @Date: 2020/3/6 17:37
     * @Description: 更新月历信息
     */
    @PutMapping("/updateCalendar")
    public ResponseResult<Object> updateCalendar(@RequestBody(required = true) List<SysCalendar> calendars) {
        if (calendars.size() == 0 || calendars.isEmpty()) {
            return this.getOkResponseResult("error", "参数为空");
        }
        for (SysCalendar calendar : calendars) {
            SysCalendar sysCalendar = calendarService.search(calendar);
            log.info("获取的用户月历信息为" + sysCalendar);
            if (sysCalendar != null) {
                log.info("执行更新操作");
                //更新
                calendarService.updateCalendar(calendar);
            } else {
                //插入
                log.info("执行插入操作");
                BizObjectModel model = new BizObjectModel();
                model.setSchemaCode("sys_calendar");
                Map<String, Object> map = new HashMap<>();
                Map<String, Object> unit = new HashMap<>();
                unit.put("id", calendar.getPerson());
                unit.put("type", 3);
                String personJson = JSON.toJSONString(unit);
                map.put("person", personJson);
                map.put("year", calendar.getYear());
                map.put("month", calendar.getMonth());
                map.put("day", calendar.getDay());
                map.put("day_type", calendar.getDayType());
                map.put("work_setting", calendar.getWorkSetting());
                map.put("related_id", calendar.getRelatedId());
                map.put("related_name", calendar.getRelatedName());
                map.put("work_content", calendar.getWorkContent());
                model.put(map);
                model.setSequenceStatus("COMPLETED");
                //创建数据的引擎类
                BizObjectFacade bizObjectFacade = super.getBizObjectFacade();
                String userId = UserUtils.getUserId(calendar.getPerson());
                log.info("当前操作的用户id为" + userId);
                //使用引擎方法批量创建数据
                bizObjectFacade.saveBizObjectModel(userId, model, "id");
            }
        }

        return this.getErrResponseResult(null, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

}
