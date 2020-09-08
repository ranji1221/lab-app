package com.ranji.lab.dto;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BackStageDto implements Serializable {
    @NonNull
    private String laboratoryName;
    @NonNull
    private String date;
    @NonNull
    private int count;
}
