package com.ranji.lab.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class DeviceType {
    private int id;
    @NonNull
    private String typeName;
}
