package com.ranji.lab.entity;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ResourcePdf implements Serializable {
    private int id;

    @NonNull
    private String url;

    @NonNull
    private String name;
}
