package com.ranji.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.ranji.lab.entity.NewsImage;
import com.ranji.lab.entity.StudyImage;
import com.ranji.lab.service.prototype.IImageService;
import com.ranji.lab.service.prototype.INewsImageService;
import com.ranji.lab.service.prototype.IStudyImageService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class ImageConrtoller {
    @Resource
    private IImageService iImageService;
    @Resource
    private INewsImageService iNewsImageService;
    @Resource
    private IStudyImageService iStudyImageService;

    @ApiOperation(value = "插入新闻(富文本)图片", notes = "通过传来的文件插入新闻(富文本)图片")
    @ApiImplicitParam(name = "files", value = "图片文件(无法在swagger上进行测试，需要自写前端页面验证)", dataType = "String")
    @PostMapping(value = "/insertnewsimage")
    @ResponseBody
    public String insertNewsImage(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws Exception{
        //-- 获取图片访问路径的前缀
        String url= request.getRequestURL().toString();
        System.out.println("截取前地址" + url);
        url = url.substring(0,url.length()-request.getServletPath().length());
        System.out.println("截取后地址" + url);
        //-- 1. 获取运行程序所在的根目录
        String rootDirectory = System.getProperty("user.dir");
        //-- 2. 创建存放上传资源的目录
        File resourceDirectory = new File(rootDirectory+File.separator+"upload");
        if(!resourceDirectory.exists()) resourceDirectory.mkdir();
        //-- System.out.println("文件个数："+files.length);
        //-- 3. 处理上传路径
        String path = resourceDirectory.getAbsolutePath()+File.separator+System.currentTimeMillis()+"_"+file.getOriginalFilename();
        //-- System.out.println(path);
        file.transferTo(new File(path));
        //-- 4. 保存新闻图片
        NewsImage newsImage = new NewsImage(path);
        int id = iNewsImageService.insertNewsImage(newsImage);
        //-- 5. 构建返回的JSON
        JSONObject jo = new JSONObject();
        jo.put("code",0);
        jo.put("msg","");
        JSONObject data = new JSONObject();
        data.put("src",url+"/newsimage/"+id);
        data.put("title","");
        jo.put("data",data);
        System.out.println(jo.toJSONString());
        return jo.toJSONString();
    }

    @ApiOperation(value = "通过新闻图片id查看新闻图片",notes = "通过新闻图片id查看新闻图片")
    @ApiImplicitParam(name = "id", value = "轮播图id", required = true, dataType = "String")
    @GetMapping(value = "/newsimage/{id}")
    public String getNewsImage(@PathVariable int id, HttpServletResponse response) throws IOException, FileNotFoundException {
        NewsImage newsImage = iNewsImageService.findNewsImageById(id);

        File f = new File(newsImage.getImageAddr());
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

    @ApiOperation(value = "插入教学科研(富文本)图片", notes = "通过传来的文件插入教学科研(富文本)图片")
    @ApiImplicitParam(name = "files", value = "图片文件(无法在swagger上进行测试，需要自写前端页面验证)", dataType = "String")
    @PostMapping(value = "/insertstudyimage")
    @ResponseBody
    public String insertStudyImage(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws Exception{
        //-- 获取图片访问路径的前缀
        String url= request.getRequestURL().toString();
        System.out.println("截取前地址" + url);
        url = url.substring(0,url.length()-request.getServletPath().length());
        System.out.println("截取后地址" + url);
        //-- 1. 获取运行程序所在的根目录
        String rootDirectory = System.getProperty("user.dir");
        //-- 2. 创建存放上传资源的目录
        File resourceDirectory = new File(rootDirectory+File.separator+"upload");
        if(!resourceDirectory.exists()) resourceDirectory.mkdir();
        //-- System.out.println("文件个数："+files.length);
        //-- 3. 处理上传路径
        String path = resourceDirectory.getAbsolutePath()+File.separator+System.currentTimeMillis()+"_"+file.getOriginalFilename();
        //-- System.out.println(path);
        file.transferTo(new File(path));
        //-- 4. 保存新闻图片
        StudyImage studyImage = new StudyImage(path);
        int id = iStudyImageService.insertStudyImage(studyImage);
        //-- 5. 构建返回的JSON
        JSONObject jo = new JSONObject();
        jo.put("code",0);
        jo.put("msg","");
        JSONObject data = new JSONObject();
        data.put("src",url+"/studyimage/"+id);
        data.put("title","");
        jo.put("data",data);
        System.out.println(jo.toJSONString());
        return jo.toJSONString();
    }

    @ApiOperation(value = "通过教学科研图片id查看教学科研图片",notes = "通过教学科研图片id查看教学科研图片")
    @ApiImplicitParam(name = "id", value = "轮播图id", required = true, dataType = "String")
    @GetMapping(value = "/studyimage/{id}")
    public String getStudyImage(@PathVariable int id, HttpServletResponse response) throws IOException, FileNotFoundException {
        StudyImage studyImage = iStudyImageService.findStudyImageById(id);

        File f = new File(studyImage.getImageAddr());
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
}
