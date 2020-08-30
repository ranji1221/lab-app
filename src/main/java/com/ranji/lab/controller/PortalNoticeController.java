package com.ranji.lab.controller;


import com.alibaba.fastjson.JSON;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.Notice;
import com.ranji.lab.service.prototype.INoticeService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "门户通知公告接口")
@RestController
public class PortalNoticeController {


    @Resource
    private INoticeService iNoticeService;

    /*
      通过前台表单的数据插入通知公告
    */
    @ApiOperation(value="插入通知公告", notes="根据传过来的新闻信息来插入通知公告详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "题目", required = true, dataType = "String"),
            @ApiImplicitParam(name = "informationSource", value = "信息来源(教务处)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "author", value = "作者", required = true, dataType = "String"),
            @ApiImplicitParam(name = "time", value = "发布时间(xxxx-xx-xx)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "内容", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    @PostMapping(value = "/insertnotice",produces = "text/plain;charset=utf-8")
    public String insertNotice(Notice notice){
        Map<Object,Object> insertNoticeMap = new HashMap<>();
        int i = iNoticeService.insertNotice(notice);
        if(i<1){
            insertNoticeMap.put("status","failure");
            return JSON.toJSONString(insertNoticeMap);
        }else{
            insertNoticeMap.put("status","success");
            return JSON.toJSONString(insertNoticeMap);
        }

    }
    /*
      通过前台表单的数据更新通知公告
    */

    @ApiOperation(value="更新通知公告", notes="根据传过来的新闻信息来更新通知公告详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "题目", required = true, dataType = "String"),
            @ApiImplicitParam(name = "informationSource", value = "信息来源(教务处)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "author", value = "作者", required = true, dataType = "String"),
            @ApiImplicitParam(name = "time", value = "更新时间(xxxx-xx-xx)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    @PostMapping(value = "/updatenotice",produces = "text/plain;charset=utf-8")
    public String updateNotice(Notice notice){
        Map<Object,Object> insertNoticeMap = new HashMap<>();
        int i = iNoticeService.insertNotice(notice);
        if(i<1){
            insertNoticeMap.put("status","failure");
            return JSON.toJSONString(insertNoticeMap);
        }else{
            insertNoticeMap.put("status","success");
            return JSON.toJSONString(insertNoticeMap);
        }

    }
    /*
    前台通过请求获得经由时间排序的通知公告
     */
    @ApiOperation(value="查询所有通知公告", notes="前端通过访问接口获得所需通知公告")
    @GetMapping(value = "/allnotice",produces = "text/plain;charset=utf-8")
    public String allNotice(){
        Map<Object,Object> noticeMap = iNoticeService.findAllNotice();
        if(!noticeMap.isEmpty()) {
            noticeMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(noticeMap);
        }else{
            noticeMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(noticeMap);
        }
    }

    /*
    前台通过请求获得经由时间排序前五的通知公告
     */
    @ApiOperation(value="查询最新的五条通知公告", notes="前端通过访问接口获得最新的五条通知公告")
    @GetMapping(value = "/allnoticelatestfive",produces = "text/plain;charset=utf-8")
    public String allNoticeLimitFive(){
        Map<Object,Object> allNoticeOnPaging = iNoticeService.findAllNotice(1,5);
        if(!allNoticeOnPaging.isEmpty()) {
            allNoticeOnPaging.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(allNoticeOnPaging);
        }else{
            allNoticeOnPaging.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allNoticeOnPaging);
        }
    }

    /*
    前台通过请求获得经由时间排序以及分页后的通知公告
     */
    @ApiOperation(value="分页查询由时间排序以及分页后的通知公告", notes="前端通过访问接口获得所需通知公告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "几条数据", required = true, dataType = "String"),
    })
    @GetMapping(value = "/allnoticepaging",produces = "text/plain;charset=utf-8")
    public String allNoticeOnPaging(int pageNum,int pageSize){
        Map<Object,Object> allNoticeOnPaging = iNoticeService.findAllNotice(pageNum,pageSize);
        if(!allNoticeOnPaging.isEmpty()) {
            allNoticeOnPaging.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(allNoticeOnPaging);
        }else{
            allNoticeOnPaging.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allNoticeOnPaging);
        }
    }

    @ApiOperation(value="分页查询所有通知公告", notes="前端通过访问接口获得所需通知公告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "需要几条数据", required = true, dataType = "String"),
    })
    @GetMapping(value = "/allnotices",produces = "text/plain;charset=utf-8")
    public String allNotices(int page , int limit){
        Map<Object,Object> noticeMap = iNoticeService.findAllNotice(page,limit);
        if(!noticeMap.isEmpty()) {
            noticeMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(noticeMap);
        }else{
            noticeMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(noticeMap);
        }
    }


    @ApiOperation(value="通过id查询通知公告", notes="前端通过访问接口获得所需通知公告")
    @ApiImplicitParam(name = "id", value = "第几条", required = true, dataType = "String")
    @PostMapping(value = "/findnoticebyid/{id}",produces = "text/plain;charset=utf-8")
    public String findNoticeById(@PathVariable("id") int id){
        Notice noticeById = iNoticeService.findById(id);
        Map<Object,Object> notice = new HashMap<>();
        notice.put("data",noticeById);
        notice.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        return JSON.toJSONString(notice);
    }
    /*
        查询连续两条的通知公告
         */
    @ApiOperation(value="查询由时间排序的连续两条的通知公告", notes="前端通过访问接口获得所需通知公告")
    @ApiImplicitParam(name = "noticeOffsetId", value = "第几篇", required = true, dataType = "String")
    @GetMapping(value = "/findnoticenexttonext",produces = "text/plain;charset=utf-8")
    public String findNoticeNextToNext(int noticeOffsetId){
        Map<Object, Object> noticeNextToNext = iNoticeService.findNoticeNextToNext(noticeOffsetId);
        if(!noticeNextToNext.isEmpty()){
            noticeNextToNext.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(noticeNextToNext);
        }else {
            noticeNextToNext.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(noticeNextToNext);
        }
    }
    @ApiOperation(value="模糊查找通知公告", notes="模糊查找通知公告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "like", value = "模糊词", required = true, dataType = "String"),
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "每页几条", required = true, dataType = "String")
    })
    @GetMapping(value = "/likenotice",produces = "text/plain;charset=utf-8")
    public String findLikeNotice(String like,int page,int limit){
        Map<Object, Object> likeNotice = iNoticeService.findLikeNotice(like,page,limit);
        if(!likeNotice.isEmpty()){
            likeNotice.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        }else{
            likeNotice.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
        }
        return JSON.toJSONString(likeNotice);
    }
}
