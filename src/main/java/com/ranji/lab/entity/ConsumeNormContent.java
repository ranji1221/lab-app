package com.ranji.lab.entity;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ConsumeNormContent {
    private int id;
    @NonNull
    private int contentId;
    @NonNull
    private String content;
}
