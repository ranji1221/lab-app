package com.ranji.lab.entity;

import com.ranji.lab.dto.RegimeDto;
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
    private String title;
    @NonNull
    private String informationSource;
    @NonNull
    private String author;
    @NonNull
    private String time;
    @NonNull
    private String content;

    public void setTime(Date time){
        this.time= DateUtil.DateToString(time,"yyyy-MM-dd");
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
