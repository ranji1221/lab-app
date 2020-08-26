package com.ranji.lab.entity;

import com.ranji.lab.dto.StudyDto;
import com.ranji.lab.util.DateUtil;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 科学教研实体类
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Study implements Serializable {
    private int id;
    @NonNull
    private String title;//教学科研发布题目
    @NonNull
    private String informationSource;//教学科研发布信息来源
    @NonNull
    private String author;//教学科研发布作者
    @NonNull
    private String time;//教学科研发布时间
    @NonNull
    private String content;//教学科研发布内容

    public void setTime(String time){
        this.time= time;
    }
    public Date getTime(){
        return DateUtil.StringToDate(this.time,"yyyy-MM-dd");
    }


    public Study(int id, StudyDto studyDto){
        this.id = id;
        this.title = studyDto.getTitle();
        this.informationSource = studyDto.getInformationSource();
        this.author = studyDto.getAuthor();
        this.time = DateUtil.DateToString(studyDto.getTime(),"yyyy-MM-dd");
        this.content = studyDto.getContent();
    }
    public Study(StudyDto studyDto){
        this.title = studyDto.getTitle();
        this.informationSource = studyDto.getInformationSource();
        this.author = studyDto.getAuthor();
        this.time = DateUtil.DateToString(studyDto.getTime(),"yyyy-MM-dd");
        this.content = studyDto.getContent();
    }
}
