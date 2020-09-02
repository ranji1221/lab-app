package com.ranji.lab.entity;

import com.alibaba.fastjson.JSON;
import lombok.*;

/**
 * 角色实体类
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Role {
    private int id;
    @NonNull
    private String code;    //-- 角色英文名
    @NonNull
    private String name;    //-- 角色中文名

    /**
     * 重写toString方法
     * @return
     */
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
