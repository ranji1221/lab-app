package com.ranji.lab.service.prototype;

import com.ranji.lab.entity.Banner;
import com.ranji.lab.entity.Images;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBannerService {
    int insertOrUpdateBannerAndImages(int bannerId,Images images);

    Banner findBannerById(int bannerId);

    List<Banner> findAllBanner();

    Images findBannerImageByBannerId(int bannerId);
}
