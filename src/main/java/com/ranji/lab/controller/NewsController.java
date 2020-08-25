package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ranji.lab.entity.News;
import com.ranji.lab.entity.NewsImage;
import com.ranji.lab.entity.Slide;
import com.ranji.lab.mapper.NewsMapper;
import com.ranji.lab.service.prototype.INewsService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 新闻控制器
 */
@Controller
public class NewsController {
    @Resource
    private INewsService newsService;

    @GetMapping("/toaddnews")
    public String toAddNews(){
        return "content/news";
    }

    @PostMapping("/uploadNewsImage")
    @ResponseBody
    public String uploadNewsImage(HttpServletRequest request, @RequestParam("file") MultipartFile file)
            throws Exception{
        //-- 获取图片访问路径的前缀
        String url= request.getRequestURL().toString();
        url = url.substring(0,url.length()-request.getServletPath().length());
        //-- System.out.println(url);
        //-- 1. 获取运行程序所在的根目录
        String rootDirectory = System.getProperty("user.dir");
        //-- 2. 创建存放上传资源的目录
        File resourceDirectory = new File(rootDirectory+File.separator+"upload");
        if(!resourceDirectory.exists()) resourceDirectory.mkdir();
        //-- System.out.println("文件个数："+files.length);
        //-- 3. 处理上传路径
        //-- System.out.println(file.getOriginalFilename());
        //-- System.out.println(file.getSize());
        String path = resourceDirectory.getAbsolutePath()+File.separator+System.currentTimeMillis()+"_"+file.getOriginalFilename();
        //-- System.out.println(path);
        file.transferTo(new File(path));
        //-- 4. 保存新闻图片
        NewsImage newsImage = new NewsImage(path);
        int id = newsService.addNewsImage(newsImage);
        //-- 5. 构建返回的JSON
        JSONObject jo = new JSONObject();
        jo.put("code",0);
        jo.put("msg","");
        JSONObject data = new JSONObject();
        data.put("src",url+"/newsImage/"+id);
        data.put("title","");
        jo.put("data",data);
        System.out.println(jo.toJSONString());
        return jo.toJSONString();
    }

    /**
     * 访问某张图片的接口
     * @param id
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     */
    @RequestMapping(value = "/newsImage/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public String getImage(@PathVariable int id, HttpServletResponse response) throws IOException, FileNotFoundException {
        NewsImage newsImage = newsService.getNewsImage(id);
        File f = new File(newsImage.getUrl());
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

    @PostMapping(value = "/addnews",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addNews(String title,String content){
        //System.out.println(title);
        //System.out.println(content);
        News news = new News(title,content);
        newsService.issueNews(news);
        JSONObject jo = new JSONObject();
        jo.put("code",200);
        return jo.toJSONString();
    }

    @GetMapping("/viewnews")
    public String toNewsDetail(){
        return "content/detail";
    }

    @GetMapping(value = "/news")
    @ResponseBody
    public String getNews(@RequestParam("id") int id){
        News news = newsService.getNews(id);
        return JSON.toJSONString(news);
    }
}
