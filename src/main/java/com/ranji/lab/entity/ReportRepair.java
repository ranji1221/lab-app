package com.ranji.lab.entity;

import com.ranji.lab.util.DateUtil;
import lombok.*;

import java.util.Date;

/**
 * 报修设备表
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ReportRepair {

    private int id;
    @NonNull
    private int deviceId;//报修设备id
    @NonNull
    private String date;// 报修时间
    @NonNull
    private String status;//报修设备状态
    @NonNull
    private String description;//报修描述

    public void setDate(String date){
        this.date = date;
    }
    public Date getDate(){
        return DateUtil.StringToDate(this.date,"yyyy-MM-dd");
    }
}
