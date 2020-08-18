package com.ranji.lab.entity;

import lombok.*;

import java.io.Serializable;

/**
 * 幻灯片实体类
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Banner implements Serializable {
    private int id;
    @NonNull
    private int imageId;
}
