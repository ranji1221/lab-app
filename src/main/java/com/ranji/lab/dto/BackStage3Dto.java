package com.ranji.lab.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@NoArgsConstructor
public class BackStage3Dto {
    @NonNull
    private int count;
    @NonNull
    private int status;
}
