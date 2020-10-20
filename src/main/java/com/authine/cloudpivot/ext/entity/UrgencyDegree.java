package com.authine.cloudpivot.ext.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 紧急程度
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/20 09:21
 */
@Getter
@AllArgsConstructor
public enum UrgencyDegree {
    NORMAL("一般"),
    URGENCY("紧急");

    private final String text;

}
