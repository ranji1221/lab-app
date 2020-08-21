package com.ranji.lab.entity;

import com.ranji.lab.util.DateUtil;
import lombok.*;

import java.io.Serializable;
import java.util.Date;


/**
 * 项目信息实体类
 */

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class LabInformation implements Serializable {
    private int lid;
    @NonNull
    private String ltitle;
    @NonNull
    private String lfaculty;
    @NonNull
    private String ldevice;
    @NonNull
    private String ltercher;
    @NonNull
    private String ldate;
    @NonNull
    private String ltime;
    @NonNull
    private String lstatus;
    public void setLdate(String ldate){
        this.ldate = ldate;
    }
    public Date getLdate(){
        return DateUtil.StringToDate(this.ldate,"yyyy-MM-dd");
    }
}

