package com.ranji.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScrapDto {
    private int id;
    private String laboratoryName;
    private String uuid;
    private String description;
    private String date;
    private String status;
    private int deviceId;

    public ScrapDto(int id, int deviceId) {
        this.id = id;
        this.deviceId = deviceId;
    }
}
