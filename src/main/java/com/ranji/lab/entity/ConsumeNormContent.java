package com.ranji.lab.entity;

import lombok.*;

/**
 * 耗材管理内容(bug)
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ConsumeNormContent {
    private int id;
    @NonNull
    private int contentId;//耗材管理发布id
    @NonNull
    private String content;//耗材管理发布内容
}
