package com.ranji.lab.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 主控制器
 */
@Controller
public class MainController {
    //@RequiresPermissions(value = {"user:delete","user:edit","user:list"},logical = Logical.OR)
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        //String user = SecurityUtils.getSubject().getPrincipal().toString();
        //System.out.println(user);
        return "index";
    }
}
