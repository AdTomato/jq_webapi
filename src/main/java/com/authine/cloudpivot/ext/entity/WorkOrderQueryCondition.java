package com.authine.cloudpivot.ext.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 工单查询条件
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/20 09:46
 */
@Getter
@Setter
public class WorkOrderQueryCondition {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 工单标题
     */
    private String title;

    /**
     * 紧急程度
     */
    private UrgencyDegree urgencyDegree;

    /**
     * 是否逾期
     */
    private Boolean overdue;

    /**
     * 工单状态
     */
    private WorkOrderStatus status;

    /**
     * 页码
     */
    private Integer pageNumber;

    /**
     * 页码数据条数
     */
    private Integer pageSize;

}
