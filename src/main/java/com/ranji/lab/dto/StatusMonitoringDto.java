package com.ranji.lab.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusMonitoringDto {
    private int id;
    @NonNull
    private String laboratoryName;   //
    @NonNull
    private String date;
    @NonNull
    private String laboratoryResponsibility;
    @NonNull
    private int imgSrc;
    private String imageAddr;
    @NonNull
    private String timeStart;
    @NonNull
    private String timeStop;
    @NonNull
    private String experimentName;
    @NonNull
    private String faculty;
    @NonNull
    private int status;
    @NonNull
    private String responsibility;

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
        this.imageAddr = "/laboratoryimage/" + imgSrc;
    }
}
