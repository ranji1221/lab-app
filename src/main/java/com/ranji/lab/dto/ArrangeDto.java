package com.ranji.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArrangeDto {
    private int id;
    @NonNull
    private int laboratoryId;           //实验室id
    @NonNull
    private int projectId;           //项目id
    @NonNull
    private int num;                    //实验人数
    @NonNull
    private String arrangeTime;         //预约日期
    @NonNull
    private String date;                //实验日期
    @NonNull
    private String timeStart;           //开始时间
    @NonNull
    private String timeStop;            //结束时间
    @NonNull
    private String responsibility;      //项目负责人
    @NonNull
    private String status;              //状态
    @NonNull
    private String projectName;         //项目名称
    @NonNull
    private String laboratoryName;      //实验室名称
    @NonNull
    private String faculty;             //所属院系
    @NonNull
    private String laboratoryResponsibility;        //实验室负责人
    private String devices;             //所用设备
    private String consumes;             //所用耗材
}
