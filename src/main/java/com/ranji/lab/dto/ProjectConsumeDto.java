package com.ranji.lab.dto;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ProjectConsumeDto implements Serializable {
    private Integer id;
    @NonNull
    private Integer experimentConsumeId;
    private Integer projectId;
    @NonNull
    private Integer consumeNum;
    private String consumeName;
    private Integer status;       //类型，0新增，1修改
}
