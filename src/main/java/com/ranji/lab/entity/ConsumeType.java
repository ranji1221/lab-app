package com.ranji.lab.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ConsumeType {
    private int id;
    @NonNull
    private String typeName;
}
