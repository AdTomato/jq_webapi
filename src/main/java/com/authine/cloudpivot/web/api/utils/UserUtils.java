package com.authine.cloudpivot.web.api.utils;

import jodd.util.StringUtil;

/**
 * @Author:wangyong
 * @Date:2020/3/26 11:16
 * @Description: 用户工具类
 */
public class UserUtils {

    public static String getUserId(String userId) {
        return StringUtil.isEmpty(userId) ? "" : userId;
    }

}
