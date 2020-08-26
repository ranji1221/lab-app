package com.ranji.lab.entity;

import lombok.*;

import java.io.Serializable;

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
    private int laboratoryId;//实验室id
    @NonNull
    private int projectId;//项目id
    @NonNull
    private int num;//预约人数
    @NonNull
    private String arrangeTime;//预约时间(当前时间)
    @NonNull
    private String date;//预约项目日期
    @NonNull
    private String timeStart;//项目项目开始时间
    @NonNull
    private String timeStop;//预约项目结束时间
    @NonNull
    private String responsibility;//预约项目负责人
    @NonNull
    private String status;//预约状态

   /* public void setDate(String date){
        this.date = date;
    }
    public Date getDate(){
        return DateUtil.StringToDate(this.date,"yyyy-MM-dd");
    }

    public Date getArrangeTime() {
        return DateUtil.StringToDate(this.arrangeTime,"yyyy-MM-dd'T'HH:mm:ssX");
    }

    public void setArrangeTime(String arrangeTime) {
        this.arrangeTime = arrangeTime;
    }

    public Time getTimeStart() {
        return DateUtil.strToTime(this.timeStart,"HH:mm:ss");
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public Time getTimeStop() {
        return DateUtil.strToTime(this.timeStop,"HH:mm:ss");
    }

    public void setTimeStop(String timeStop) {
        this.timeStop = timeStop;
    }*/
}
