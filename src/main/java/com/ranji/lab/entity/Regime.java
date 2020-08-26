package com.ranji.lab.entity;

import com.ranji.lab.dto.RegimeDto;
import com.ranji.lab.util.DateUtil;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 实验制度实体类
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Regime implements Serializable {
    private int id;
    @NonNull
    private String title;//实体制度发布题目
    @NonNull
    private String informationSource;//实体制度发布信息来源
    @NonNull
    private String author;//实体制度发布作者
    @NonNull
    private String time;//实体制度发布时间
    @NonNull
    private String content;//实体制度发布内容

    public void setTime(String time){
        this.time= time;
    }
    public Date getTime(){
        return DateUtil.StringToDate(this.time,"yyyy-MM-dd");
    }

    public Regime(int id, RegimeDto regimeDto){
        this.id = id;
        this.title = regimeDto.getTitle();
        this.informationSource = regimeDto.getInformationSource();
        this.author = regimeDto.getAuthor();
        this.time = DateUtil.DateToString(regimeDto.getTime(),"yyyy-MM-dd");
        this.content = regimeDto.getContent();
    }
    public Regime(RegimeDto regimeDto){
        this.title = regimeDto.getTitle();
        this.informationSource = regimeDto.getInformationSource();
        this.author = regimeDto.getAuthor();
        this.time = DateUtil.DateToString(regimeDto.getTime(),"yyyy-MM-dd");
        this.content = regimeDto.getContent();
    }

}
