package com.authine.cloudpivot.web.api.entity;

import lombok.Data;

import java.util.Date;


/**
 * @Author: weiyao
 * @Date: 2020-02-26
 * @Description: 合同变更子表
 */
@Data
public class I1uycXsContractListPay {

    private String id;
    private Float money;
    private String summary;//款项描述
    private String abstrac;//付款说明
    private String parentId;
    private Integer sortKey;
    private Date expectedReceiptDate;
}
