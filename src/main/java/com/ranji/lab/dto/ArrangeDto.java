package com.ranji.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArrangeDto {
    private int id;
    @NonNull
    private int laboratoryId;
    @NonNull
    private String projectId;
    @NonNull
    private int num;
    @NonNull
    private String arrangeTime;
    @NonNull
    private String date;
    @NonNull
    private String timeStart;
    @NonNull
    private String timeStop;
    @NonNull
    private String responsibility;
    @NonNull
    private String status;
    @NonNull
    private String projectName;
    @NonNull
    private String laboratoryName;
}
