package com.ranji.lab.dto;

import com.ranji.lab.entity.ProjectConsume;
import com.ranji.lab.entity.ProjectDevice;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 实验项目实体类
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ExperimentProjectDto implements Serializable {
    private int id;         //实验项目id
    @NonNull
    private String experimentName;          //实验名称
    @NonNull
    private String experimentTarget;        //实验目标
    @NonNull
    private String experimentContent;       //实验内容
    @NonNull
    private String experimentProcess;       //实验流程
    private List<ProjectConsumeDto> projectConsumeList;         //实验所用消耗品及数量
    private List<ProjectDeviceDto> projectDeviceList;           //实验所用设备姐设备及数量
    @NonNull
    private int status;                   //实验项目状态
}
