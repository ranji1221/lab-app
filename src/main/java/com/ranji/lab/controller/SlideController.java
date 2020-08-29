package com.ranji.lab.controller;

import com.ranji.lab.entity.Slide;
import com.ranji.lab.service.prototype.ISlideService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 轮播图控制器
 * @RanJi
 */
@Controller
public class SlideController {

    @Resource
    private ISlideService slideService;

    /**
     * 跳转到上传文件的界面
     * @return
     */
    @GetMapping("/toupload")
    public String toUpload(){
        return "slide/upload";
    }

    /**
     * 处理上传文件的控制器类
     * @param files
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
    @ResponseBody
    public String uploadFile(@RequestParam("id") int id,@RequestParam("name") String name,@RequestParam("file") MultipartFile[] files)
            throws Exception{
        //-- 1. 获取运行程序所在的根目录
        String rootDirectory = System.getProperty("user.dir");
        //-- 2. 创建存放上传资源的目录
        File resourceDirectory = new File(rootDirectory+File.separator+"upload");
        if(!resourceDirectory.exists()) resourceDirectory.mkdir();
        //-- System.out.println("文件个数："+files.length);
        //-- 3. 处理上传路径
        for (MultipartFile file : files) {
            //-- System.out.println(file.getOriginalFilename());
            //-- System.out.println(file.getSize());
            String path = resourceDirectory.getAbsolutePath()+File.separator+file.getOriginalFilename();
            //-- System.out.println(path);
            file.transferTo(new File(path));
            //-- 4. 保持到数据库
            Slide slide = new Slide(id,name,path);
            slideService.saveOrUpdateSlide(slide);
        }
        return "{'upload':'ok'}";
    }

    /**
     * 访问某张图片的接口
     * @param id
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     */
    @GetMapping(value = "/slide/{id}")
    public String getImage(@PathVariable int id, HttpServletResponse response) throws IOException, FileNotFoundException {
        /*
        //-- 1. 获取运行程序所在的根目录
        String path = System.getProperty("user.dir");
        //-- System.out.println(path);
        //-- 2. 指向要读取的图片
        File f = new File(path+File.separator+"upload"+File.separator+id+".jpg");
        */
        Slide slide = slideService.getSlide(id);
        File f = new File(slide.getUrl());
        //-- 3. 缓存区域
        byte[] buffer = new byte[1024];
        BufferedInputStream bis = null;
        bis = new BufferedInputStream(new FileInputStream(f));
        //-- 4. 输出图片
        OutputStream os = response.getOutputStream();
        int len = 0;
        while ((len=bis.read(buffer))!= -1) {
            os.write(buffer, 0, len);
        }
        bis.close();
        return null;
    }

}
