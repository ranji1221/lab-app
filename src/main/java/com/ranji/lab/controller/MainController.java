package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.service.prototype.IConsumeNormContentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 主控制器
 */
@Controller
public class MainController {
    @Resource
    private IConsumeNormContentService iConsumeNormContentService;

    //@RequiresPermissions(value = {"user:delete","user:edit","user:list"},logical = Logical.OR)
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        //String user = SecurityUtils.getSubject().getPrincipal().toString();
        //System.out.println(user);
        return "index";
    }
    @RequestMapping(value = "/indexx", method = RequestMethod.GET)
    public String indexx(){
        //String user = SecurityUtils.getSubject().getPrincipal().toString();
        //System.out.println(user);

        return "indexx";
    }
    @RequestMapping(value = "/aaa" , method = RequestMethod.GET ,produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String indexxxx(){
        Map<Object, Object> object = iConsumeNormContentService.consumeContent();
        System.out.println(object);
        return JSON.toJSONString(object);
    }
}
