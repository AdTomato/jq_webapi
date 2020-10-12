package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.service.ScheduledService;
import com.dingtalk.api.DingTalkClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @Author: wangyong
 * @Date: 2020-03-02 13:08
 * @Description: 定时任务service
 */
@Service
public class ScheduledServiceImpl implements ScheduledService {

    /**
     * @return : void
     * @Author: wangyong
     * @Date: 2020/3/2 13:13
     * @Description: 定时任务，每天凌晨1点从钉钉获取考勤数据
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void get() {
//        DingTalkClient
    }

}
