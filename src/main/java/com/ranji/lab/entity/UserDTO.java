package com.ranji.lab.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * ClassName:    UserDTO
 * Package:    com.ranji.lab.entity
 * Description:  用户DTO类
 * Datetime:    2020/9/2   4:25 下午
 * Author:   ranji
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UserDTO implements Serializable {
    @NonNull
    private String name;
    @NonNull
    private String password;
}