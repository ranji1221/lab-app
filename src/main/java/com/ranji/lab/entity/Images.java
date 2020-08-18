package com.ranji.lab.entity;

import lombok.*;

import java.io.Serializable;

/**
 * 图片实体类
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Images implements Serializable {
    private int id;
    @NonNull
    private String imgName;
    @NonNull
    private String imgAddr;
    @NonNull
    private String imgDescription;
}
