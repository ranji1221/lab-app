package com.ranji.lab.entity;

import lombok.*;

/**
 * 用户审计表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Audit {

    private int id;

    @NonNull
    private String username;

    @NonNull
    private String ipAddr;

    @NonNull
    private String time;

    @NonNull
    private String accessAddr;
}
