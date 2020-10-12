package com.authine.cloudpivot.web.api.entity;

import lombok.Data;

import java.util.Date;

//附件实体
@Data
public class HBizAttachment {
    private String id;
    private String bizObjectId;
    private String bizPropertyCode;
    private Date createdTime;
    private String creater;
    private String fileExtension;
    private Integer fileSize;
    private String mimeType;
    private String name;
    private String parentBizObjectId;
    private String parentSchemaCode;
    private String refId;
    private String schemaCode;
}
