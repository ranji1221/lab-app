package com.ranji.lab.controller;

import com.ranji.lab.entity.User;
import com.ranji.lab.util.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户控制器，暂时不用，所以先注释起来
 * @author RanJi
 */
//@Controller
public class UserController {
    @ApiOperation(value = "注册用户",notes = "根据给定的User对象进行注册")
    @RequestMapping(value="/user",method = RequestMethod.GET)
    public ResponseEntity<JsonResult> opentAccount(User act){
        JsonResult r = new JsonResult();
        r.setStatus("ok");
        r.setResult(new User("aaa","123",1));
        return ResponseEntity.ok(r);
    }
    @RequestMapping(value = "/user/{id}")
    public ResponseEntity<JsonResult> getUser(@PathVariable("id") int id){
        return null;
    }
}
