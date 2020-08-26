package com.ranji.lab.entity;

import com.ranji.lab.util.DateUtil;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理标准实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ConsumeNorm implements Serializable {
    private int id;
    @NonNull
    private String title;//耗材管理发布题目
    @NonNull
    private String informationSource;//耗材管理发布来源
    @NonNull
    private String author;//耗材管理发布作者
    @NonNull
    private String time;//耗材管理发布时间
    @NonNull
    private String content;//耗材管理发布内容

    public void setTime(String time){
        this.time = time;
    }
    public Date getTime(){
        return DateUtil.StringToDate(this.time,"yyyy-MM-dd");
    }
}
