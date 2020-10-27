package com.authine.cloudpivot.ext.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 工作流状态
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/14 17:43
 */
@Getter
@AllArgsConstructor
public enum WorkflowStatus {
    DRAFT("草稿", ""),
    PROCESSING("处理中", "volcano"),
    COMPLETED("已完成", "green"),
    CANCELED("已作废", "grey"),
    EXCEPTION("异常", "red");

    private final String text;
    private final String color;

}
