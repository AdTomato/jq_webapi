package com.authine.cloudpivot.ext.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/23 14:00
 */
@Getter
@Setter
public class Dict {

    /**
     * 字段编码
     */
    Object code;

    /**
     * 显示名称
     */
    String name;

    /**
     * 是否显示标签
     */
    Boolean tag;

    /**
     * 标签演示
     */
    String color;

    /**
     * 链接地址
     */
    String link;

}
