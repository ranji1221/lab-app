package com.ranji.lab.entity;

import com.ranji.lab.util.DateUtil;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备报修实体
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Scrap implements Serializable {
    private int id;

    @NonNull
    private int deviceId;//报废设备id

    @NonNull
    private String date;//报废日期

    private String status;//报废状态

    @NonNull
    private String description;//报废描述

    public void setDate(String date){
        this.date = date;
    }
    public Date getDate(){
        return DateUtil.StringToDate(this.date,"yyyy-MM-dd");
    }
}
