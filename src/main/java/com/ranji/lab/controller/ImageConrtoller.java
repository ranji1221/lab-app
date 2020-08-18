package com.ranji.lab.controller;

import com.ranji.lab.service.prototype.IImageService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class ImageConrtoller {
    @Resource
    private IImageService iImageService;

}
