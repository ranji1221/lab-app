package com.ranji.lab.dto;

import com.ranji.lab.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumeInformDto {
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String brand;
    @NonNull
    private int num;
    @NonNull
    private String facid;
    @NonNull
    private String factime;
    @NonNull
    private String proid;
    @NonNull
    private String supid;
    @NonNull
    private String unitName;
    @NonNull
    private int type;

    public void setFactime(String factime){
        this.factime=factime;
    }
    public Date getFactime(){
        return DateUtil.StringToDate(this.factime,"yyyy-MM-dd");
    }

}
