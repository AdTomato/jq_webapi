package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.mapper.BuyApplyMapper;
import com.authine.cloudpivot.web.api.mapper.DailyPaperMapper;
import com.authine.cloudpivot.web.api.service.BuyApplyService;
import com.authine.cloudpivot.web.api.service.DailyPaperService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: weiyao
 * @Date: 2020-08-31
 * @Description: 日报
 */
@Service
public class DailyPaperServiceImpl implements DailyPaperService {

    @Resource
    DailyPaperMapper dailyPaperMapper;

    @Override
    public Integer getDailyTimeNum() {
        return dailyPaperMapper.getDailyTimeNum();
    }

    @Override
    public List<Map<String, String>> getviewWorkOrderInfo(String userid) {
        return dailyPaperMapper.getviewWorkOrderInfo(userid);
    }

    @Override
    public List<String> getRefuseId(String userId, String objectId, Date date) {
        return dailyPaperMapper.getRefuseId(userId, userId, date);
    }

    @Override
    public Integer batchDelete(List<String> ids) {
        return dailyPaperMapper.batchDelete(ids);
    }
}
