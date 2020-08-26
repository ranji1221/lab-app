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
    private String imgName;//图片名字
    @NonNull
    private String imgAddr;//图片地址
    @NonNull
    private String imgDescription;//图片描述
}
