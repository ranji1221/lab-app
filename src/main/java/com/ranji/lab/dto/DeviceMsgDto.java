package com.ranji.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceMsgDto implements Serializable {

    private String uuid;
    private String deviceName;
    private int deviceId;
    private int status;
}
