package com.ranji.lab.dto;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ConsumeAndConsumeNumDto {

    private int consumeId;
    @NonNull
    private String name;
    @NonNull
    private int num;
}
