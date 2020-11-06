package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.mapper.DailyPaperMapper;
import com.authine.cloudpivot.web.api.service.DailyPaperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: weiyao
 * @Date: 2020-08-31
 * @Description: 日报
 */
@Slf4j
@Service
public class DailyPaperServiceImpl implements DailyPaperService {

    @Resource
    private DailyPaperMapper dailyPaperMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

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

    @Override
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getDailyReceiver(String uid) {
        Object o = redisTemplate.opsForValue().get("DailyReceiver:" + uid);
        if (o instanceof List) {
            return (List<Map<String, Object>>) o;
        }
        return null;
    }

    @Override
    public void saveDailyReceiver(String uid, List<Map<String, Object>> receiver) {
        log.info("{} => {}", uid, receiver);
        redisTemplate.opsForValue().set("DailyReceiver:" + uid, receiver, Duration.ofDays(7));
    }
}
