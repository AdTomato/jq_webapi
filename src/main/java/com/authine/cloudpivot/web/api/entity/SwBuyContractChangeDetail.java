package com.authine.cloudpivot.web.api.entity;

import lombok.Data;


/**
 * @Author: weiyao
 * @Date: 2020-02-28
 * @Description: 采购合同变更子表
 */
@Data
public class SwBuyContractChangeDetail {

    private String id;
    private Integer totalMoney;
    //关联供应商
    private String supplier;
    //合同编号
    private String contractNo;
    //付款方式
    private String payType;
    //发票类型
    private String invoiceType;
    //税点
    private Float invoiceTaxPoint;
    private String parentId;
    private Integer sortKey;

    //附件信息
    //创建人
    private String creater;
    //文件大小
    private Integer fileSize;
    //文件路径
    private String mimeType;
    //文件名
    private String name;
}
