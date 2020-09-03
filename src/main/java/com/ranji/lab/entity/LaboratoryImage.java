package com.ranji.lab.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class LaboratoryImage {
    private int id;
    @NonNull
    private String imageAddr;
}