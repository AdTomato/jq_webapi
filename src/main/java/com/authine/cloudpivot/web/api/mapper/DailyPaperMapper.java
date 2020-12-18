package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.ext.entity.DailyDetail;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author: weiyao
 * @Date: 2020-08-31
 * @Description: 日报
 */
public interface DailyPaperMapper {

    Integer getDailyTimeNum();

    List<DailyDetail> getViewWorkOrderInfo(@Param("userId") String userId);

    List<String> getRefuseId(@Param("userId") String userId, @Param("objectId") String objectId, @Param("date") Date date);

    Integer batchDelete(List<String> ids);

    /**
     * 获取某人指定日期最新提交日报
     *
     * @param uid  用户ID
     * @param date 日期
     * @return List<DailyDetail>
     */
    List<DailyDetail> getDailyDetail(@Param("uid") String uid, @Param("date") String date);

}
