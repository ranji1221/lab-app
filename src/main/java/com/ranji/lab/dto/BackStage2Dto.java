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
    private double useingCount;
    @NonNull
    private double useingCountPercentage;
    @NonNull
    private double noUseCount;
    @NonNull
    private double noUseCountPercentage;
    @NonNull
    private double willUseCount;
    @NonNull
    private double willUseCountPercentage;
}
