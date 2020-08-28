package com.ranji.lab.entity;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ResourceDoc implements Serializable {
    private int id;

    @NonNull
    private String url;

    @NonNull
    private String name;
}
