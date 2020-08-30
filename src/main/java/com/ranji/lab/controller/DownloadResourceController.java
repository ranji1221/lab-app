package com.ranji.lab.controller;

import com.ranji.lab.entity.ResourceDoc;
import com.ranji.lab.entity.ResourcePdf;
import com.ranji.lab.service.prototype.IResourceDocService;
import com.ranji.lab.service.prototype.IResourcePdfService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Api(tags = "教学资源下载接口")
@RestController
public class DownloadResourceController {

    @Resource
    private IResourceDocService iResourceDocService;
    @Resource
    private IResourcePdfService iResourcePdfService;

    /**
     * 下载教学资源
     */
    @ApiOperation(value = "下载教学资源",notes = "下载教学资源")
    @GetMapping(value = "/downloadresourcedoc")
    public String downloadResourceDoc(HttpServletResponse response , int id){
        ResourceDoc resourceDoc = iResourceDocService.findResourceDocById(id);
        //获取文件地址
        File fileResourceDoc = new File(resourceDoc.getUrl());

        try {
            //设置下载格式
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/msword;charset=UTF8");
            response.setHeader("Content-Disposition","attachment;filename=" + resourceDoc.getName());
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

    @ApiOperation(value = "下载教学资源",notes = "下载教学资源")
    @GetMapping(value = "/downloadresourcepdf",produces = "text/plain;charset=utf-8")
    public String downloadResourcePdf(HttpServletResponse response , int id){

        ResourcePdf resourcePdf = iResourcePdfService.findResourcePdfById(id);
        //获取文件地址
        File fileResourcePdf = new File(resourcePdf.getUrl());

        try {
            //设置下载格式
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/pdf;charset=UTF8");
            response.setHeader("Content-Disposition","attachment;filename=" + resourcePdf.getName());
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

}
