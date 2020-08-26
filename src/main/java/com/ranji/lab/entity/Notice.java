package com.ranji.lab.entity;

import com.ranji.lab.dto.NoticeDto;
import com.ranji.lab.util.DateUtil;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 通知公告实体类
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Notice implements Serializable {
    private int id;
    @NonNull
    private String title;//通知公告发布题目
    @NonNull
    private String informationSource;//通知公告发布信息来源
    @NonNull
    private String author;//通知公告发布作者
    @NonNull
    private String time;//通知公告发布时间
    @NonNull
    private String content;//通知公告发布内容

    public void setTime(String time){
        this.time= time;
    }
    public Date getTime(){
        return DateUtil.StringToDate(this.time,"yyyy-MM-dd");
    }

    public Notice(int id, NoticeDto noticeDto){
        this.id = id;
        this.title = noticeDto.getTitle();
        this.informationSource = noticeDto.getInformationSource();
        this.author = noticeDto.getAuthor();
        this.time = DateUtil.DateToString(noticeDto.getTime(),"yyyy-MM-dd");
        this.content = noticeDto.getContent();
    }
    public Notice(NoticeDto noticeDto){
        this.title = noticeDto.getTitle();
        this.informationSource = noticeDto.getInformationSource();
        this.author = noticeDto.getAuthor();
        this.time = DateUtil.DateToString(noticeDto.getTime(),"yyyy-MM-dd");
        this.content = noticeDto.getContent();
    }
}
