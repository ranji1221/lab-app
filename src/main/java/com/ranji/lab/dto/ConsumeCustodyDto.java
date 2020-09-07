package com.ranji.lab.dto;

import com.ranji.lab.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumeCustodyDto {

    private int id;
    private String consumeName;
    private String recipient;
    private String date;
    private double count;
    private int status;
    private String unitName;
    private int arrangeProjectId;
    private String projectName;

    public void setDate(String date){
        this.date = date;
    }
    public Date getDate(){
        return DateUtil.StringToDate(this.date,"yyyy-MM-dd");
    }
}
