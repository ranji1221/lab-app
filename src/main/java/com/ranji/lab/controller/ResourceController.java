package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.ResourceDoc;
import com.ranji.lab.entity.ResourcePdf;
import com.ranji.lab.service.prototype.IResourceDocService;
import com.ranji.lab.service.prototype.IResourcePdfService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "教学资源接口")
@RestController
public class ResourceController {

    @Resource
    private IResourcePdfService iResourcePdfService;

    @Resource
    private IResourceDocService iResourceDocService;

    @ApiOperation(value = "分页查询教学资源Doc",notes = "分页查询教学资源Doc")
    @GetMapping(value = "/allresourcedocpaging",produces = "text/plain;charset=utf-8")
    public String allResourceDocPaging(int page,int limit){
        Map<Object, Object> allMap = iResourceDocService.ResourceDocPaging(page, limit);
        return JSON.toJSONString(allMap);
    }
    @ApiOperation(value = "分页查询教学资源Pdf",notes = "分页查询教学资源Pdf")
    @GetMapping(value = "/allresourcepdfpaging",produces = "text/plain;charset=utf-8")
    public String allResourcePdfPaging(int page,int limit){
        Map<Object, Object> allMap = iResourcePdfService.ResourcePdfPaging(page, limit);
        return JSON.toJSONString(allMap);
    }

    /**
     * 下载教学资源
     */
    @ApiOperation(value = "下载教学资源Doc",notes = "下载教学资源Doc")
    @GetMapping(value = "/downloadresourcedoc")
    public String downloadResourceDoc(HttpServletResponse response , int id) throws UnsupportedEncodingException {
        ResourceDoc resourceDoc = iResourceDocService.findResourceDocById(id);
        //获取文件地址
        File fileResourceDoc = new File(resourceDoc.getUrl());

        String name = new String(resourceDoc.getName().getBytes("UTF-8"), "ISO-8859-1");

        try {
            //设置下载格式
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/force-download;charset=UTF8");
            response.setHeader("Content-Disposition","attachment;filename=" + name);
            response.flushBuffer();

            //输出.Doc文件
            OutputStream os = null;
            byte[] buffer = new byte[1024];
            BufferedInputStream bis = null;
            bis = new BufferedInputStream(new FileInputStream(fileResourceDoc));
            os = response.getOutputStream();
            int len = 0;
            while ((len=bis.read(buffer))!= -1) {
                os.write(buffer, 0, len);
            }
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value = "下载教学资源Pdf",notes = "下载教学资源Pdf")
    @GetMapping(value = "/downloadresourcepdf")
    public String downloadResourcePdf(HttpServletResponse response , int id) throws Exception {

        ResourcePdf resourcePdf = iResourcePdfService.findResourcePdfById(id);
        //获取文件地址
        File fileResourcePdf = new File(resourcePdf.getUrl());

        String name = new String(resourcePdf.getName().getBytes("UTF-8"), "ISO-8859-1");
        try {
            //设置下载格式
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/force-download;charset=UTF8");
            response.setHeader("Content-Disposition","attachment;filename=" + name);
            response.flushBuffer();
            //输出.Pdf文件
            OutputStream os = null;
            byte[] buffer = new byte[1024];
            BufferedInputStream bis = null;
            bis = new BufferedInputStream(new FileInputStream(fileResourcePdf));
            os = response.getOutputStream();
            int len = 0;
            while ((len=bis.read(buffer))!= -1) {
                os.write(buffer, 0, len);
            }
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 上传pdf格式的资源
     */
    @ApiOperation(value = "上传pdf版的资源",notes = "上传pdf版的资源")
    @PostMapping(value = "/uploadresourcepdf", produces = "text/plain;charset=utf-8")
    @RequiresRoles(value = {"admin", "majorHead", "teacher"}, logical = Logical.OR)
    public String uploadResourcePdf(@RequestParam("file") MultipartFile[] files) throws IOException {
        //获取根目录
        String rootDirectory = System.getProperty("user.dir");
        //文件存储位置
        File resourceDirectory = new File(rootDirectory + File.separator + "upload" + File.separator + "resourcepdf");
        //创建文件夹
        if(!resourceDirectory.exists()) resourceDirectory.mkdirs();
        for (MultipartFile file : files) {
            String url = resourceDirectory.getAbsolutePath() +File.separator + file.getOriginalFilename();
            file.transferTo(new File(url));

            ResourcePdf resourcePdf = new ResourcePdf(url, file.getOriginalFilename());
            iResourcePdfService.insertResourcePdf(resourcePdf);
        }

        HashMap<Object, Object> allMap = new HashMap<>();
        allMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        return JSON.toJSONString(allMap);
    }

    /**
     * 上传doc格式的资源
     */
    @ApiOperation(value = "上传doc版的资源",notes = "上传doc版的资源")
    @PostMapping(value = "/uploadresourcedoc", produces = "text/plain;charset=utf-8")
    @RequiresRoles(value = {"admin", "majorHead", "teacher"}, logical = Logical.OR)
    public String uploadResourceDoc(@RequestParam("file") MultipartFile[] files) throws IOException {
        //获取根目录
        String rootDirectory = System.getProperty("user.dir");
        //文件存储位置
        File resourceDirectory = new File(rootDirectory + File.separator + "upload" + File.separator + "resourcedoc");
        //创建文件夹
        if(!resourceDirectory.exists()) resourceDirectory.mkdirs();
        for (MultipartFile file : files) {
            String url = resourceDirectory.getAbsolutePath() +File.separator + file.getOriginalFilename();
            file.transferTo(new File(url));
            ResourceDoc resourceDoc = new ResourceDoc(url, file.getOriginalFilename());
            iResourceDocService.insertResourceDoc(resourceDoc);
        }
        HashMap<Object, Object> allMap = new HashMap<>();
        allMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        return JSON.toJSONString(allMap);
    }

    @ApiOperation(value = "上传教学资源",notes = "上传教学资源")
    @PostMapping(value = "/uploadresource", produces = "text/plain;charset=utf-8")
    @RequiresRoles(value = {"admin", "majorHead", "teacher"}, logical = Logical.OR)
    public String uploadResource(@RequestParam("file") MultipartFile[] files) throws IOException {

        HashMap<Object, Object> allMap = new HashMap<>();
        //获取根目录
        String rootDirectory = System.getProperty("user.dir");
        //文件存储位置
        File resourceDirectory = new File(rootDirectory + File.separator + "upload" + File.separator + "resourcedoc");
        //创建文件夹
        if(!resourceDirectory.exists()) resourceDirectory.mkdirs();
        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            String type = originalFilename.substring(originalFilename.lastIndexOf(".")+1,originalFilename.length());
            if(type.equals("docx") || type.equals("doc")){
                String url = resourceDirectory.getAbsolutePath() +File.separator + file.getOriginalFilename();
                file.transferTo(new File(url));
                ResourceDoc resourceDoc = new ResourceDoc(url, file.getOriginalFilename());
                iResourceDocService.insertResourceDoc(resourceDoc);
            }else if(type.equals("pdf")){
                String url = resourceDirectory.getAbsolutePath() +File.separator + file.getOriginalFilename();
                file.transferTo(new File(url));
                ResourcePdf resourceDoc = new ResourcePdf(url, file.getOriginalFilename());
                iResourcePdfService.insertResourcePdf(resourceDoc);
            }else{
                allMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
                return JSON.toJSONString(allMap);
            }
        }
        allMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        return JSON.toJSONString(allMap);
    }
}
