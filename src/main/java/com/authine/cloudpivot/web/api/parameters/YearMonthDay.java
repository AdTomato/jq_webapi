package com.authine.cloudpivot.web.api.parameters;


import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2020-03-06 17:48
 * @Description: 年，月，日
 */
@Data
public class YearMonthDay {

    /**
     * 年
     */
    private Integer year;

    /**
     * 月
     */
    private Integer month;

    /**
     * 日
     */
    private Integer day;

}
