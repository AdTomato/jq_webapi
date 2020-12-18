package com.authine.cloudpivot.web.api.controller;


import com.authine.cloudpivot.ext.entity.DailyDetail;
import com.authine.cloudpivot.ext.util.DailyUtils;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.service.DailyPaperService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author weiyao
 * @time 2020-08-31
 */
@RestController
@RequestMapping("/controller/dailypaper")
@Slf4j
public class DailyPaperController extends BaseController {

    @Resource
    DailyPaperService dailyPaperService;

    /**
     * 获取日报补填可选天数
     *
     * @return ResponseResult<Integer>
     */
    @GetMapping("/getDailyTimeNum")
    public ResponseResult<Integer> getDailyTimeNum() {
        return this.getOkResponseResult(dailyPaperService.getDailyTimeNum(), "succeed");
    }

    /**
     * 获取工单事项
     *
     * @param userId 用户ID
     * @param date   日报填写日期
     * @return ResponseResult<Object>
     */
    @GetMapping("/getViewWorkOrderInfo")
    public ResponseResult<Object> getViewWorkOrderInfo(String userId, String date) {
        if (StringUtils.isNotEmpty(userId)) {
            List<DailyDetail> rsp = dailyPaperService.getViewWorkOrderInfo(userId);
            List<DailyDetail> dailyDetail = dailyPaperService.getDailyDetail(userId, date);
            if (dailyDetail != null && dailyDetail.size() > 0) {
                rsp.forEach(srcDetail -> {
                    dailyDetail.stream().filter(desDetail -> DailyUtils.same(srcDetail, desDetail)).findFirst().ifPresent(detail -> {
                        srcDetail.setWorkingHours(detail.getWorkingHours());
                    });
                });
            }
            return this.getOkResponseResult(rsp, "succeed");
        } else {
            return this.getErrResponseResult(null, 404L, "用户id为空");
        }
    }

    //weiyao 去除重复项
    //date格式：2020-08-28 00:00:00 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="GMT+8")
    @GetMapping("/removeRepetitionInfo")
    public ResponseResult<Object> removeRepetitionInfo(String userId, String objectId, String date) {
        log.info("开始检测是否有重复项提交");
        log.info("userId:{}, objectId:{}, date{}", userId, objectId, date);
        if (StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(date)) {
            String format = "yyyy-MM-dd";
            Date time = null;
            try {
                time = new SimpleDateFormat(format).parse(date);
            } catch (ParseException ps) {
                return this.getErrResponseResult(null, 404L, "时间格式错误");
            }

            List<String> idList = dailyPaperService.getRefuseId(userId, objectId, time);
            log.info("重复项id为：" + idList.toString());
            try {
                Boolean suc = this.getBizObjectFacade().removeBizObjects(userId, userId, idList);
                return this.getOkResponseResult(suc, "succeed");
            } catch (Exception e) {
                //引擎报错的话数据库操作删除
                Integer de = dailyPaperService.batchDelete(idList);
                return this.getOkResponseResult(de, "succeed");
            }
        } else {
            return this.getErrResponseResult(null, 404L, "参数不齐");
        }
    }

    /**
     * 获取日报接收人
     *
     * @return ResponseResult<List < DailyReceiver>>
     */
    @GetMapping("/dailyReceiver")
    public ResponseResult<List<Map<String, Object>>> getDailyReceiver() {
        String userId = getUserId();
        List<Map<String, Object>> dailyReceiver = dailyPaperService.getDailyReceiver(userId);
        return this.getOkResponseResult(dailyReceiver, "获取日报接收人成功");
    }

    /**
     * 保存日报接收人
     *
     * @return ResponseResult<Void>
     */
    @PostMapping("/dailyReceiver")
    public ResponseResult<Void> addDailyReceiver(@RequestBody List<Map<String, Object>> receiver) {
        String userId = getUserId();
        dailyPaperService.saveDailyReceiver(userId, receiver);
        return this.getOkResponseResult("保存日报接收人成功");
    }
}
