package com.ranji.lab.entity;

import lombok.*;

/**
 * 实验室图片实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class LaboratoryImage {
    private int id;
    @NonNull
    private String imageAddr;
}
