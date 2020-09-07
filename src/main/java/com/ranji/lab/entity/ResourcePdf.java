package com.ranji.lab.entity;

import lombok.*;

import java.io.Serializable;

/**
 * pdf资源实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ResourcePdf implements Serializable {
    private int id;

    @NonNull
    private String url;

    @NonNull
    private String name;
}
