package com.ranji.lab.entity;

import com.ranji.lab.util.DateUtil;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 预约安排实体类
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Arrange implements Serializable {
    private int id;
    @NonNull
    private String laboratoryName;
    @NonNull
    private String experimentName;
    @NonNull
    private int num;
    @NonNull
    private String date;
    @NonNull
    private String device;
    @NonNull
    private String time;
    @NonNull
    private String faculty;
    @NonNull
    private String responsibility;
    @NonNull
    private String status;

    public void setDate(String date){
        this.date = date;
    }
    public Date getDate(){
        return DateUtil.StringToDate(this.date,"yyyy-MM-dd");
    }
}
