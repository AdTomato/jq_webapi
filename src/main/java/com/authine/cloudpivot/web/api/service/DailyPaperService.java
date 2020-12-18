package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.ext.entity.DailyDetail;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 日报
 *
 * @author weiyao
 * @time 2020-08-31
 */
public interface DailyPaperService {

    /**
     * 获取日报可写天数
     *
     * @return Integer
     */
    Integer getDailyTimeNum();

    /**
     * 获取用户工单信息
     *
     * @param id 用户ID
     * @return List<Map < String, String>>
     */
    List<DailyDetail> getViewWorkOrderInfo(String id);

    //查询某人某天重复日报
    List<String> getRefuseId(String userId, String objectId, Date date);

    /**
     * 数据库删除
     *
     * @param ids List<String>
     * @return Integer
     */
    Integer batchDelete(List<String> ids);

    /**
     * 获取用户的日报接收人
     *
     * @param uid 用户ID
     * @return List<Map < String, ?>>
     */
    List<Map<String, Object>> getDailyReceiver(String uid);

    /**
     * 保存用户的日报接收人
     *
     * @param uid      用户ID
     * @param receiver 日志接收人
     */
    void saveDailyReceiver(String uid, List<Map<String, Object>> receiver);

    /**
     * 获取用户指定日期最新日报明细信息
     *
     * @param uid  用户ID
     * @param date 日期
     * @return List<Object>
     */
    List<DailyDetail> getDailyDetail(String uid, String date);

}
