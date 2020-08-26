package com.ranji.lab.entity;

import lombok.*;

/**
 * 耗材类型实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ConsumeType {
    private int id;
    @NonNull
    private String typeName;//耗材类型名字
}
