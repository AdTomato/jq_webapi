package com.authine.cloudpivot.ext.util;

import com.authine.cloudpivot.ext.entity.DailyDetail;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/12/18 16:57
 */
public class DailyUtils {

    public static boolean same(DailyDetail a, DailyDetail b) {
        return a.getWorkOrderId().equals(b.getWorkOrderId())
                && a.getWorkFlowType().equals(b.getWorkFlowType())
                && a.getTitle().equals(b.getTitle())
                && a.getWorkContent().equals(b.getWorkContent());
    }

}
