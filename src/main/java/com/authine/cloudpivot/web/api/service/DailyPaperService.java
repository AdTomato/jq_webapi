package com.authine.cloudpivot.web.api.service;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: weiyao
 * @Date: 2020-08-31
 * @Description: 日报
 */
public interface DailyPaperService {
    //获取日报可写天数
    Integer getDailyTimeNum();

    //获取视图信息
    List<Map<String,String>> getviewWorkOrderInfo(String id);

    //查询某人某天重复日报
    List<String> getRefuseId(String userId, String objectId, Date date);

    //数据库删除
    Integer  batchDelete(List<String> ids);
}
