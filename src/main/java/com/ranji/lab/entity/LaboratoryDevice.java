package com.ranji.lab.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class LaboratoryDevice {
    private int id;
    @NonNull
    private int laboratoryId;
    @NonNull
    private int deviceId;
    @NonNull
    private String status;
}
