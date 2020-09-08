package com.ranji.lab.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BackStage2Dto implements Serializable {
    @NonNull
    private double allCount;
    @NonNull
    private double allCountPercentage;
    @NonNull
    private double finishedCount;
    @NonNull
    private double finishedCountPercentage;
    @NonNull
    private double unfinishedCount;
    @NonNull
    private double unfinishedCountPercentage;
    @NonNull
    private double noCount;
    @NonNull
    private double noCountPercentage;
}
