package com.ranji.lab.entity;

import lombok.*;

/**
 * 教学科研实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class StudyImage {
    private int id;
    @NonNull
    private String imageAddr;
}
