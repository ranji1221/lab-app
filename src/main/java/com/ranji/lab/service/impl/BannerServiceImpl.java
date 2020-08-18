package com.ranji.lab.service.impl;

import com.ranji.lab.entity.Banner;
import com.ranji.lab.entity.Images;
import com.ranji.lab.mapper.BannerMapper;
import com.ranji.lab.mapper.ImageMapper;
import com.ranji.lab.service.prototype.IBannerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BannerServiceImpl implements IBannerService {

    @Resource
    private BannerMapper bannerMapper;
    @Resource
    private ImageMapper imageMapper;

    @Override
    @Transactional
    public int insertBannerAndImagesAndGetLatestBannerId(Images images) {
        imageMapper.insertImage(images);
        int imageId = imageMapper.latestImageData();
        bannerMapper.insertBanner(imageId);
        int bannerId = bannerMapper.latestBannerData();
        return bannerId;
    }

    @Override
    public Banner findBannerById(int bannerId) {
        return bannerMapper.findBannerById(bannerId);
    }

    @Override
    public List<Banner> findAllBanner() {
        return bannerMapper.findAllBanner();
    }
}
