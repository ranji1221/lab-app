package com.ranji.lab.entity;

import com.ranji.lab.dto.NewsDto;
import com.ranji.lab.util.DateUtil;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 首页新闻实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class News implements Serializable {
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

    public void setTime(String time){
        this.time = time;
    }
    public Date getTime(){
        return DateUtil.StringToDate(this.time,"yyyy-MM-dd");
    }

    public News(int id, NewsDto newsDto){
        this.id = id;
        this.title = newsDto.getTitle();
        this.informationSource = newsDto.getInformationSource();
        this.author = newsDto.getAuthor();
        this.time = DateUtil.DateToString(newsDto.getTime(),"yyyy-MM-dd");
        this.content = newsDto.getContent();
    }
    public News(NewsDto newsDto){
        this.title = newsDto.getTitle();
        this.informationSource = newsDto.getInformationSource();
        this.author = newsDto.getAuthor();
        this.time = DateUtil.DateToString(newsDto.getTime(),"yyyy-MM-dd");
        this.content = newsDto.getContent();
    }

}
