package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.Images;
import com.ranji.lab.service.prototype.IBannerService;
import com.ranji.lab.service.prototype.IImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "上传接口")
@Controller
public class UploadController {
    @GetMapping(value = "/toupload",produces = "text/plain;charset=utf-8")
    public String toUpload(){
        return "slide/upload";
    }

    @Resource
    private IBannerService iBannerService;
    @Resource
    private IImageService iImageService;

    /**
     * 处理上传文件的控制器类
     * @param files
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "上传轮播图",notes = "通过传来的轮播图id，以及轮播图的信息来上传轮播图信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "轮播图id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "name", value = "图片名字", required = true, dataType = "String"),
            @ApiImplicitParam(name = "description", value = "图片描述", required = true, dataType = "String"),
            @ApiImplicitParam(name = "files",value = "图片文件(无法在swagger上进行测试，需要自写前端页面验证)",dataType = "String")
    })
    @PostMapping(value = "/uploadbanner",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String uploadFile(@RequestParam("id") int id, @RequestParam("name") String name,@RequestParam("description") String description,@RequestParam("file") MultipartFile[] files)
            throws Exception{
        //-- 1. 获取运行程序所在的根目录
        String rootDirectory = System.getProperty("user.dir");
        //-- 2. 创建存放上传资源的目录
        File resourceDirectory = new File(rootDirectory+File.separator+"upload"+File.separator+"banner");
        if(!resourceDirectory.exists()) resourceDirectory.mkdirs();
        //-- 3. 处理上传路径
        for (MultipartFile file : files) {
            String path = resourceDirectory.getAbsolutePath()+File.separator+file.getOriginalFilename();
            file.transferTo(new File(path));
            //-- 4. 保持到数据库
            Images images = new Images(id,name,path,description);
            iBannerService.insertOrUpdateBannerAndImages(id,images);
        }
        return "{'upload':'ok'}";
    }

    @ApiOperation(value = "查看jpg格式的轮播图",notes = "通过传来的轮播图id，查看图片(.jpg)")
    @ApiImplicitParam(name = "id", value = "轮播图id", required = true, dataType = "String")
    @GetMapping(value = "/bannerimagejpg/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public String getImage(@PathVariable int id, HttpServletResponse response) throws IOException, FileNotFoundException {
        Images bannerImage= iBannerService.findBannerImageByBannerId(id);

        File f = new File(bannerImage.getImgAddr());
        byte[] buffer = new byte[1024];
        BufferedInputStream bis = null;
        bis = new BufferedInputStream(new FileInputStream(f));
        OutputStream os = response.getOutputStream();
        int len = 0;
        while ((len=bis.read(buffer))!= -1) {
            os.write(buffer, 0, len);
        }
        bis.close();

        return null;
    }
    @ApiOperation(value = "插入jpg格式的图片",notes = "通过传来的文件插入jpg格式的图片")
    @ApiImplicitParam(name = "files",value = "图片文件(无法在swagger上进行测试，需要自写前端页面验证)",dataType = "String")
    @PostMapping(value = "/insertimagesjpg",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String uploadImagesJpg(@RequestParam("file") MultipartFile[] files){
        //-- 0. 返回map
        Map<Object,Object> imagesMap = new HashMap<>();
        //-- 1. 获取项目的根目录
        String rootDirectory = System.getProperty("user.dir");
        //-- 2. 创建存放上传资源的目录
        File resourceDirectory = new File(rootDirectory+File.separator+"upload"+File.separator+"image");

        if(!resourceDirectory.exists()) resourceDirectory.mkdirs();
        int i = 1;
        for(MultipartFile file : files){
            String jpgname = file.getOriginalFilename();
            if(jpgname.substring(jpgname.indexOf(".")+1,jpgname.length()).equals("jpg")) {

                String path = resourceDirectory.getAbsolutePath() + File.separator + file.getOriginalFilename();
                try {
                    file.transferTo(new File(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String description = "description";
                Images images = new Images(jpgname, path, description);
                iImageService.insertImage(images);

                imagesMap.put("image" + i++, path);
            }else{
                return "{status:jpg plz}";
            }
        }
        if(!imagesMap.isEmpty()){
            imagesMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(imagesMap);
        }else{
            imagesMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(imagesMap);
        }
    }



}
