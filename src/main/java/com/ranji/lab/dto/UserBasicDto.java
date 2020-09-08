package com.ranji.lab.dto;

import lombok.*;

/**
 * 用户基本信息实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBasicDto {
    @NonNull
    private String roleName; //用户角色

    private String sex;//用户性别

    private String phone;//用户手机号

    private String eMail;//用户邮箱

    private String remark;//用户备注
}
