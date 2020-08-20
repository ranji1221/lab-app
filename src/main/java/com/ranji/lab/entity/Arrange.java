package com.ranji.lab.entity;

import com.ranji.lab.util.DateUtil;
import lombok.*;

import java.io.Serializable;
import java.sql.Time;
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
    private int laboratoryId;
    @NonNull
    private String projectId;
    @NonNull
    private int num;
    @NonNull
    private String arrangeTime;
    @NonNull
    private String date;
    @NonNull
    private String timeStart;
    @NonNull
    private String timeStop;
    @NonNull
    private String responsibility;
    @NonNull
    private String status;

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
