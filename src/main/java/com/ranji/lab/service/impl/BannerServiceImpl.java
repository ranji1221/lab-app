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
    public int insertOrUpdateBannerAndImages(int bannerId,Images images) {
        Banner sureBanner = bannerMapper.findSureIdBanner(bannerId);
        if(sureBanner==null){
            imageMapper.insertImage(images);
            int imageId = imageMapper.latestImageData();
            Banner banner = new Banner(bannerId,imageId);
            int i = bannerMapper.insertBanner(banner);
            return i;
        }else{
            int imagesId = bannerMapper.findBannerImageIdByBannerId(bannerId);
            images.setId(imagesId);
            int i = imageMapper.updateImage(images);
            return i;
        }

    }

    @Override
    public Banner findBannerById(int bannerId) {
        return bannerMapper.findBannerById(bannerId);
    }

    @Override
    public List<Banner> findAllBanner() {
        return bannerMapper.findAllBanner();
    }

    @Override
    public Images findBannerImageByBannerId(int bannerId) {
        int imagesId= bannerMapper.findBannerImageIdByBannerId(bannerId);
        Images images = imageMapper.findImagesById(imagesId);
        return images;
    }

}
