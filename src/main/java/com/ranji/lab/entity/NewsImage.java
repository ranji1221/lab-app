package com.ranji.lab.entity;

import lombok.*;

/**
 * 新闻图片实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class NewsImage {
    private int id;
    @NonNull
    private String imageAddr;
}
