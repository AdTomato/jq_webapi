package com.authine.cloudpivot.web.api.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: weiyao
 * @Date: 2020-08-31
 * @Description: 日报
 */
public interface DailyPaperMapper {

    //获取日报可写天数
    Integer getDailyTimeNum();

    //获取视图信息
    List<Map<String, String>> getviewWorkOrderInfo(@Param("userId") String userId);

    List<String> getRefuseId(@Param("userId") String userId, @Param("objectId") String objectId, @Param("date") Date date);

    Integer batchDelete(List<String> ids);
}
