package com.ranji.lab.entity;

import com.ranji.lab.util.DateUtil;
import lombok.*;

import javax.swing.text.Utilities;
import java.io.Serializable;
import java.util.Date;

/**
 * 监控状态实体类
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Monitaring implements Serializable {
    private int id;
    @NonNull
    private String labiratoryName;
    @NonNull
    private String faculty;
    @NonNull
    private String experimentName;
    @NonNull
    private String experimentTime;
    @NonNull
    private String time;
    @NonNull
    private String responsibility;
    @NonNull
    private String status;

    public void setExperimentTime(String experimentTime){
        this.experimentName=experimentTime;
    }
    public Date getExperimentTime(){
        return DateUtil.StringToDate(this.experimentTime,"yyyy-MM-dd");
    }
}
