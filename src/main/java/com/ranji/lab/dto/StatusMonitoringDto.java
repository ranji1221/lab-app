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
    private String imgSrc;
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
}
