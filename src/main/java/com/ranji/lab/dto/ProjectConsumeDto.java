package com.ranji.lab.dto;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ProjectConsumeDto {
    private Integer id;
    @NonNull
    private Integer experimentConsumeId;
    @NonNull
    private Integer projectId;
    @NonNull
    private Integer consumeNum;
    @NonNull
    private String consumeName;
    @NonNull
    private Integer status;       //类型，0新增，1修改
    @NonNull
    private String unitName;
}
