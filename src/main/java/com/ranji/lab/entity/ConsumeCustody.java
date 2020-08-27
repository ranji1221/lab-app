package com.ranji.lab.entity;

import com.ranji.lab.util.DateUtil;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 保管领用
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ConsumeCustody implements Serializable {
    private int id;
    @NonNull
    private int consumeId;//耗材id
    @NonNull
    private String recipient;//领用人
    @NonNull
    private String date;//领用日期
    @NonNull
    private int count;//领用数量
    @NonNull
    private String status;//领用状态
    @NonNull
    private String unitName;
    @NonNull
    private String consumeName;

    public void setDate(String date){
        this.date = date;
    }
    public Date getDate(){
        return DateUtil.StringToDate(this.date,"yyyy-MM-dd");
    }
}
