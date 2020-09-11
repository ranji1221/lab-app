package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.dto.BannerImagesDto;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.Images;
import com.ranji.lab.service.prototype.IBannerService;
import com.ranji.lab.service.prototype.IImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "轮播图接口")
@Controller
public class BannerController {
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
    @RequiresRoles(value = {"admin", "majorHead"}, logical = Logical.OR)
    public String uploadFile(int id, String name, String description, MultipartFile files)
            throws Exception{
        //-- 1. 获取运行程序所在的根目录
        String rootDirectory = System.getProperty("user.dir");
        //-- 2. 创建存放上传资源的目录
        File resourceDirectory = new File(rootDirectory+File.separator+"upload"+File.separator+"banner");
        if(!resourceDirectory.exists()) resourceDirectory.mkdirs();
        //-- 3. 处理上传路径
       // for (MultipartFile file : files) {
            String path = resourceDirectory.getAbsolutePath()+File.separator+files.getOriginalFilename();
            files.transferTo(new File(path));
            //-- 4. 保持到数据库
            Images images = new Images(id,name,path,description);
            iBannerService.insertOrUpdateBannerAndImages(id,images);
            Map<String,Object> map = new HashMap<>();
            map.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(map);
       // }
        //return "{'upload':'no'}";
    }

    @ApiOperation(value = "查看轮播图",notes = "通过传来的轮播图id，查看图片(.jpg)")
    @ApiImplicitParam(name = "id", value = "轮播图id", required = true, dataType = "String")
    @GetMapping(value = "/bannerimagejpg/{id}")
    public String getImage(@PathVariable int id, HttpServletResponse response) throws IOException, FileNotFoundException {
        Images bannerImage= iBannerService.findBannerImageByBannerId(id);
        String rootDirectory = System.getProperty("user.dir");

        String addr = bannerImage.getImgAddr();

        addr = addr.substring(bannerImage.getImgAddr().lastIndexOf(File.separator)+1);

        String imageAddr = rootDirectory+File.separator+"banner"+File.separator+addr;

        File f = new File(imageAddr);

        byte[] buffer = new byte[1024];
        BufferedInputStream bis = null;
        bis = new BufferedInputStream(new FileInputStream(f));
        OutputStream os = response.getOutputStream();
        int len = 0;
        while ((len=bis.read(buffer))!= -1) {
            os.write(buffer, 0, len);
        }
        bis.close();
        Map<String,Object> map = new HashMap<>();
        map.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        return JSON.toJSONString(map);
    }

    @ApiOperation(value = "获取门户轮播图信息",notes = "获取所有轮播图信息")
    @GetMapping(value = "allbanner",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String allImage(){
        List<BannerImagesDto> allBannerImages = new ArrayList<>();
        for(int i = 1;i<4;i++){
            int imagesId = iBannerService.findSureBannerId(i);
            /*if(i<0) return JSONObject.*/
            BannerImagesDto bannerImagesDto = new BannerImagesDto();
            bannerImagesDto.setId(i);
            bannerImagesDto.setSureUrl("/bannerimagejpg/"+imagesId);
            allBannerImages.add(bannerImagesDto);
        }
        HashMap<Object, Object> allData = new HashMap<>();
        if(!allBannerImages.isEmpty()){
            allData.put("data",allBannerImages);
            allData.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        }else{
            allData.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
        }
        return JSON.toJSONString(allData);
    }

}
