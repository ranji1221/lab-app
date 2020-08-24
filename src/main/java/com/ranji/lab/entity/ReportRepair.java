package com.ranji.lab.entity;

import com.ranji.lab.util.DateUtil;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ReportRepair {

    private int id;
    @NonNull
    private int deviceId;
    @NonNull
    private String date;// 报修时间
    @NonNull
    private String status;
    @NonNull
    private String description;

    public void setDate(String date){
        this.date = date;
    }
    public Date getDate(){
        return DateUtil.StringToDate(this.date,"yyyy-MM-dd");
    }
}
