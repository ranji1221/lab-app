package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.dto.NewsDto;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.News;
import com.ranji.lab.service.prototype.INewsService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "门户新闻接口")
@RestController
public class PortalNewsController {
    @Resource
    private INewsService iNewsService;

    /*
    通过前台表单的数据插入新闻
     */
    @ApiOperation(value="插入新闻", notes="根据传过来的新闻信息来插入新闻详细信息")
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
    @PostMapping(value = "/insertnews",produces = "text/plain;charset=utf-8")
    public String insertNews(NewsDto newsDto){
        System.out.println(newsDto);
        Map<Object,Object> insertNewsMap = new HashMap<>();
        int i = iNewsService.insertNews(newsDto);
        if(i<1){
            insertNewsMap.put("status","failure");
            return JSON.toJSONString(insertNewsMap);
        }else{
            insertNewsMap.put("status","success");
            return JSON.toJSONString(insertNewsMap);
        }

    }
    /*
    通过前台表单的数据更新新闻
     */
    @ApiOperation(value="更新新闻", notes="根据传过来的新闻信息来更新新闻详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "题目", required = true, dataType = "String"),
            @ApiImplicitParam(name = "informationSource", value = "信息来源(教务处)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "author", value = "作者", required = true, dataType = "String"),
            @ApiImplicitParam(name = "time", value = "更新时间(xxxx-xx-xx)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    @PostMapping(value = "/updatenews",produces = "text/plain;charset=utf-8")
    public String updateNews(News news){
        Map<Object,Object> updateNewsMap = new HashMap<>();
        int i = iNewsService.updateNews(news);
        if(i<1){
            updateNewsMap.put("status","failure");
            return JSON.toJSONString(updateNewsMap);
        }else{
            updateNewsMap.put("status","success");
            return JSON.toJSONString(updateNewsMap);
        }

    }
    /*
    前台通过请求获得经由时间排序的新闻
     */
    @ApiOperation(value="查询所有新闻", notes="前端通过访问接口获得所有新闻")
    @GetMapping(value = "/allnews",produces = "text/plain;charset=utf-8")
    public String allNews(){
        Map<Object,Object> newsMap = iNewsService.findAllNews();
        if(!newsMap.isEmpty()) {
            newsMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(newsMap);
        }else{
            newsMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(newsMap);
        }
    }


    /*
    前台通过请求获得经由时间排序前五的新闻
     */
    @ApiOperation(value="查询最新五条新闻", notes="前端通过访问接口获得最新的五条新闻")
    @GetMapping(value = "/allnewslatestfive",produces = "text/plain;charset=utf-8")
    public String allNewsLimitFive(){
        Map<Object, Object> allNews = iNewsService.findAllNews(1, 5);
        if(!allNews.isEmpty()) {
            allNews.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(allNews);
        }else{
            allNews.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allNews);
        }
    }

    /*
    前台通过请求获得经由时间排序以及分页后的新闻
     */
    @ApiOperation(value="分页查询新闻", notes="前端通过访问接口获得所需新闻")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "几条数据", required = true, dataType = "String"),
    })
    @GetMapping(value = "/allnewspaging",produces = "text/plain;charset=utf-8")
    public String allNewsOnPaging(int pageNum,int pageSize){
        Map<Object,Object> allNews = iNewsService.findAllNews(pageNum, pageSize);
        if(!allNews.isEmpty()) {
            allNews.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(allNews);
        }else {
            allNews.put(Code.FAILURE.getMsg(), Code.FAILURE.getCode());
            return JSON.toJSONString(allNews);
        }
    }

    @ApiOperation(value="分页查询所有新闻", notes="根据传过来的新闻信息来查询新闻")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "所需要的条数", required = true, dataType = "String"),
    })
    @GetMapping(value = "/allnewss",produces = "text/plain;charset=utf-8")
    public Object allNewss(int page,int limit){
        Map<Object, Object> allNewss = iNewsService.findAllNews(page, limit);
        if(!allNewss.isEmpty()) {
            allNewss.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(allNewss);
        }else{
            allNewss.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allNewss);
        }
    }


    @ApiOperation(value="通过id查询新闻", notes="前端通过访问接口获得所需新闻")
    @ApiImplicitParam(name = "id", value = "第几条新闻", required = true, dataType = "String")
    @PostMapping(value = "/findnewsbyid/{id}",produces = "text/plain;charset=utf-8")
    public String findNewsById(@PathVariable("id") int id){
        News newsById = iNewsService.findById(id);
        Map<Object,Object> news = new HashMap<>();
        news.put("data",newsById);
        news.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        return JSON.toJSONString(news);
    }

    /*
    查询连续两条的新闻
     */
    @ApiOperation(value="查询由时间排序的连续两条的新闻", notes="前端通过访问接口获得所需新闻")
    @ApiImplicitParam(name = "newsOffsetId", value = "第几篇", required = true, dataType = "String")
    @GetMapping(value = "/findnewsnexttonext",produces = "text/plain;charset=utf-8")
    public String findNewsNextToNext(int newsOffsetId){
        Map<Object, Object> newsNextToNext = iNewsService.findNewsNextToNext(newsOffsetId);
        if(!newsNextToNext.isEmpty()){
            newsNextToNext.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(newsNextToNext);
        }else {
            newsNextToNext.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(newsNextToNext);
        }
    }

    /**
     * 模糊查找
     */

    //模糊查找新闻
    @ApiOperation(value="模糊查找新闻", notes="模糊查找新闻")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "like", value = "模糊词", required = true, dataType = "String")
    })
    @GetMapping(value = "/likenews",produces = "text/plain;charset=utf-8")
    public String findLikeNews(String like){
        Map<Object, Object> likeNews = iNewsService.findLikeNews(like);
        if(!likeNews.isEmpty()){
            likeNews.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        }else{
            likeNews.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
        }
        return JSON.toJSONString(likeNews);
    }
}
