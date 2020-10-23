package com.authine.cloudpivot.ext.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 工单状态
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/14 17:43
 */
@Getter
@AllArgsConstructor
public enum WorkOrderStatus {
    PROCESSING("处理中", "volcano"),
    ;

    private final String text;
    private final String color;

}
