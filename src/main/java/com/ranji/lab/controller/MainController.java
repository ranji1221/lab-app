package com.ranji.lab.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Test控制器
 */
@Controller
public class MainController {
    @RequiresPermissions(value = {"user:delete","user:edit","user:list"},logical = Logical.OR)
    @RequestMapping("/index")
    public String test(){
        //String user = SecurityUtils.getSubject().getPrincipal().toString();
        //System.out.println(user);
        return "index";
    }
}
