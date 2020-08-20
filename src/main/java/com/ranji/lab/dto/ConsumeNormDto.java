package com.ranji.lab.dto;

import com.ranji.lab.util.DateUtil;
import lombok.*;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumeNormDto {
    @NonNull
    private String title;
    @NonNull
    private String informationSource;
    @NonNull
    private String author;
    @NonNull
    private String time;
    @NonNull
    private int contentId;

    public void setTime(String time){
        this.time = time;
    }
    public Date getTime(){
        return DateUtil.StringToDate(this.time,"yyyy-MM-dd");
    }

}
