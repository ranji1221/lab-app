package com.ranji.lab.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class StudyImage {
    private int id;
    @NonNull
    private String imageAddr;
}
