package com.ranji.lab.entity;

import lombok.*;

/**
 * 用户基本信息实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class UserBasic {
    private int id;
    @NonNull
    private int userId; //用户id

    private String nickname;//用户昵称

    private String sex;//用户性别

    private int imageId;//用户头像

    private String phone;//用户手机号

    private String eMali;//用户邮箱

    private String remark;//用户备注
}
