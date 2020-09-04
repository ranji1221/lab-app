package com.ranji.lab.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceTypeDto {
    @NonNull
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
