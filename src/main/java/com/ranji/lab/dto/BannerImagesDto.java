package com.ranji.lab.dto;

import lombok.*;

@NoArgsConstructor
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BannerImagesDto {

    private int id;
    @NonNull
    private String sureUrl;
}
