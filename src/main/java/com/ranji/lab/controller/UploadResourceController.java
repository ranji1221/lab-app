package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.ResourceDoc;
import com.ranji.lab.entity.ResourcePdf;
import com.ranji.lab.service.prototype.IResourceDocService;
import com.ranji.lab.service.prototype.IResourcePdfService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Api(tags = "教学资源上传接口")
@RestController
public class UploadResourceController {

    @Resource
    private IResourcePdfService iResourcePdfService;

    @Resource
    private IResourceDocService iResourceDocService;

    /**
     * 上传pdf格式的资源
     */
    @ApiOperation(value = "上传pdf版的资源",notes = "上传pdf版的资源")
    @PostMapping(value = "/uploadresourcepdf",produces = "text/plain;charset=utf-8")
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
        allMap.put(Code.SUCCESS.getCode(),Code.SUCCESS.getMsg());
        return JSON.toJSONString(allMap);
    }

    /**
     * 上传doc格式的资源
     */
    @ApiOperation(value = "上传doc版的资源",notes = "上传doc版的资源")
    @PostMapping(value = "/uploadresourcedoc",produces = "text/plain;charset=utf-8")
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
        allMap.put(Code.SUCCESS.getCode(),Code.SUCCESS.getMsg());
        return JSON.toJSONString(allMap);
    }
}
