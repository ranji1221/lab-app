package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.entity.*;
import com.ranji.lab.service.prototype.*;
import com.ranji.lab.util.DateUtil;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前台页面
 * 1.news
 * 2.banner
 * 3.regime
 * 4.notice
 * 5.study
 */
@Api(tags = "门户接口")
@RestController
public class PortalController {

    @Resource
    private INewsService iNewsService;
    @Resource
    private INoticeService iNoticeService;
    @Resource
    private IStudyService iStudyService;
    @Resource
    private IRegimeService iRegimeService;
    @Resource
    private IBannerService iBannerService;

    @ApiOperation(value="测试swagger", notes="测试swagger")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer"),
    })
    @PostMapping(value = "/testSwagger")
    @ResponseBody
    public String testSwagger(){
        return "successful";
    }
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
    public String insertNews(News news){
        Map<Object,Object> insertNewsMap = new HashMap<>();
        int i = iNewsService.insertNews(news);
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
        List<News> allNews = iNewsService.findAllNews();
        for(News news:allNews){
            Date time = news.getTime();
            String date = DateUtil.DateToString(time, "yyyy-MM-dd");
            news.setTime(date);
        }
        System.out.println(allNews);
        Map<Object,Object> newsMap = new HashMap<>();
        if(!allNews.isEmpty()) {
            newsMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            newsMap.put("data", allNews);
            return JSON.toJSONString(newsMap);
        }else{
            newsMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(newsMap);
        }
    }
    @ApiOperation(value="分页查询新闻", notes="根据传过来的新闻信息来查询新闻")
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
    @ApiOperation(value="查询由时间排序以及分页后的新闻", notes="前端通过访问接口获得所需新闻")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "几条数据", required = true, dataType = "String"),
    })
    @GetMapping(value = "/allnews/{pageNum}/{pageSize}",produces = "text/plain;charset=utf-8")
    public String allNewsOnPaging(@PathVariable("pageNum") int pageNum,@PathVariable("pageSize") int pageSize){
        Map<Object,Object> allNews = iNewsService.findAllNews(pageNum, pageSize);
        if(!allNews.isEmpty()) {
            allNews.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(allNews);
        }else {
            allNews.put(Code.FAILURE.getMsg(), Code.FAILURE.getCode());
            return JSON.toJSONString(allNews);
        }
    }
    @ApiOperation(value="查询本id的新闻", notes="前端通过访问接口获得所需新闻")
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
    @GetMapping(value = "/findnewsnexttonext/{newsOffsetId}",produces = "text/plain;charset=utf-8")
    public String findNewsNextToNext(@PathVariable("newsOffsetId") int newsOffsetId){
        Map<Object, Object> newsNextToNext = iNewsService.findNewsNextToNext(newsOffsetId-1);
        if(!newsNextToNext.isEmpty()){
            newsNextToNext.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(newsNextToNext);
        }else {
            newsNextToNext.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(newsNextToNext);
        }
    }

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
        List<Notice> allNotice = iNoticeService.findAllNotice();
        Map<Object,Object> noticeMap = new HashMap<>();
        if(!allNotice.isEmpty()) {
            noticeMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            noticeMap.put("data", allNotice);
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
    @ApiOperation(value="查询由时间排序以及分页后的通知公告", notes="前端通过访问接口获得所需通知公告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "几条数据", required = true, dataType = "String"),
    })
    @GetMapping(value = "/allnotice/{pageNum}/{pageSize}",produces = "text/plain;charset=utf-8")
    public String allNoticeOnPaging(@PathVariable("pageNum") int pageNum,@PathVariable("pageSize") int pageSize){
        Map<Object,Object> allNoticeOnPaging = iNoticeService.findAllNotice(pageNum,pageSize);
        if(!allNoticeOnPaging.isEmpty()) {
            allNoticeOnPaging.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(allNoticeOnPaging);
        }else{
            allNoticeOnPaging.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allNoticeOnPaging);
        }
    }
    @ApiOperation(value="查询由时间排序以及分页后的通知公告", notes="前端通过访问接口获得所需通知公告")
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
    @GetMapping(value = "/findnoticenexttonext/{noticeOffsetId}",produces = "text/plain;charset=utf-8")
    public String findNoticeNextToNext(@PathVariable("noticeOffsetId") int noticeOffsetId){
        Map<Object, Object> newsNoticeNextToNext = iNewsService.findNewsNextToNext(noticeOffsetId-1);
        if(!newsNoticeNextToNext.isEmpty()){
            newsNoticeNextToNext.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(newsNoticeNextToNext);
        }else {
            newsNoticeNextToNext.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(newsNoticeNextToNext);
        }
    }

      /*通过前台表单的数据插入实验制度*/
    @ApiOperation(value="插入实验制度", notes="根据传过来的实验制度信息来插入实验制度详细信息")
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
    @PostMapping(value = "/insertregime",produces = "text/plain;charset=utf-8")
    public String insertRegime(Regime regime){
        Map<Object,Object> insertRegimeMap = new HashMap<>();
        int i = iRegimeService.insertRegime(regime);
        if(i<1){
            insertRegimeMap.put("status","failure");
            return JSON.toJSONString(insertRegimeMap);
        }else{
            insertRegimeMap.put("status","success");
            return JSON.toJSONString(insertRegimeMap);
        }

    }


      /*通过前台表单的数据更新实验制度*/
      @ApiOperation(value="更新实验制度", notes="根据传过来的实验制度信息来更新实验制度详细信息")
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
    @PostMapping(value = "/updateregime",produces = "text/plain;charset=utf-8")
    public String updateRegime(Regime regime){
        Map<Object,Object> insertRegimeMap = new HashMap<>();
        int i = iRegimeService.updateRegime(regime);
        if(i<1){
            insertRegimeMap.put("status","failure");
            return JSON.toJSONString(insertRegimeMap);
        }else{
            insertRegimeMap.put("status","success");
            return JSON.toJSONString(insertRegimeMap);
        }

    }
    /*
    前台通过请求获得经由时间排序的实验制度
     */
    @ApiOperation(value="查询由时间排序以及分页后的实验制度", notes="前端通过访问接口获得所有实验制度")
    @GetMapping(value = "/allregime",produces = "text/plain;charset=utf-8")
    public String allRegime(){
        List<Regime> allRegime = iRegimeService.findAllRegime();
        Map<Object,Object> regimeMap = new HashMap<>();
        if(!allRegime.isEmpty()) {
            regimeMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            regimeMap.put("data", allRegime);
            return JSON.toJSONString(regimeMap);
        }else {
            regimeMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(regimeMap);
        }
    }

    /*
    前台通过请求获得经由时间排序以及分前五的实验制度
     */
    @ApiOperation(value="查询由前五条的实验制度", notes="前端通过访问接口获得所需实验制度")
    @GetMapping(value = "/allregimelatestfive",produces = "text/plain;charset=utf-8")
    public String allRegimeLimitFive() {
        Map<Object, Object> allRegimeOnPaging = iRegimeService.findAllRegime(1, 5);
        if (!allRegimeOnPaging.isEmpty()) {
            allRegimeOnPaging.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(allRegimeOnPaging);
        }else {
            allRegimeOnPaging.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allRegimeOnPaging);
        }
    }

    /*
    前台通过请求获得经由时间排序以及分页后的实验制度
     */
    @ApiOperation(value="查询由前五条的实验制度", notes="前端通过访问接口获得所需实验制度")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "几条数据", required = true, dataType = "String"),
    })
    @GetMapping(value = "/allregime/{pageNum}/{pageSize}",produces = "text/plain;charset=utf-8")
    public String allRegimeOnPaging(@PathVariable("pageNum") int pageNum,@PathVariable("pageSize") int pageSize) {
        Map<Object, Object> allRegimeOnPaging = iRegimeService.findAllRegime(pageNum, pageSize);
        if (!allRegimeOnPaging.isEmpty()) {
            allRegimeOnPaging.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(allRegimeOnPaging);
        }else {
            allRegimeOnPaging.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allRegimeOnPaging);
        }
    }
    @ApiOperation(value="查询具体id的实验制度", notes="前端通过访问接口获得所需实验制度")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String")
    @PostMapping(value = "/findregimebyid/{id}",produces = "text/plain;charset=utf-8")
    public String findRegimeById(@PathVariable("id") int id){
        Regime regimeById = iRegimeService.findById(id);
        Map<Object,Object> regime = new HashMap<>();
        regime.put("data",regimeById);
        regime.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        return JSON.toJSONString(regime);
    }

    /*
    查询连续两条的实验制度
    */
    @ApiOperation(value="查询由时间排序的连续两条的实验制度", notes="前端通过访问接口获得所需实验制度")
    @ApiImplicitParam(name = "regimeOffsetId", value = "第几篇", required = true, dataType = "String")
    @GetMapping(value = "/findregimenexttonext/{regimeOffsetId}",produces = "text/plain;charset=utf-8")
    public String findRegimeNextToNext(@PathVariable("regimeOffsetId") int regimeOffsetId){
        Map<Object, Object> newsRegimeNextToNext = iNewsService.findNewsNextToNext(regimeOffsetId-1);
        if(!newsRegimeNextToNext.isEmpty()){
            newsRegimeNextToNext.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(newsRegimeNextToNext);
        }else {
            newsRegimeNextToNext.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(newsRegimeNextToNext);
        }
    }


    /*
      通过前台表单的数据插入教学科研
    */
    @ApiOperation(value="插入教学科研", notes="根据传过来的教学科研信息来插入教学科研详细信息")
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
    @PostMapping(value = "/insertstudy",produces = "text/plain;charset=utf-8")
    public String insertStudy(HttpServletRequest request){
        Map<Object,Object> insertStudyMap = new HashMap<>();
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String title = request.getParameter("title");
        String informationSource = request.getParameter("informationsource");
        String author = request.getParameter("author");
        String time = request.getParameter("time");
        String content = request.getParameter("content");
        Study study = new Study(title,informationSource,author,time,content);
        int i = iStudyService.insertStudy(study);
        if(i<1){
            insertStudyMap.put("status","failure");
            return JSON.toJSONString(insertStudyMap);
        }else{
            insertStudyMap.put("status","success");
            return JSON.toJSONString(insertStudyMap);
        }
    }
    /*
      通过前台表单的数据更新教学科研
    */
    @ApiOperation(value="更新教学科研", notes="根据传过来的教学科研信息来更新教学科研详细信息")
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
    @PostMapping(value = "/updatestudy",produces = "text/plain;charset=utf-8")
    public String updateStudy(Study study){
        Map<Object,Object> updateStudyMap = new HashMap<>();
        int i = iStudyService.insertStudy(study);
        if(i<1){
            updateStudyMap.put("status","failure");
            return JSON.toJSONString(updateStudyMap);
        }else{
            updateStudyMap.put("status","success");
            return JSON.toJSONString(updateStudyMap);
        }

    }
    /*
    前台通过请求获得经由时间排序的教学科研
     */
    @ApiOperation(value="查询由所有的教学科研", notes="前端通过访问接口获得所需教学科研")
    @GetMapping(value = "/allstudy",produces = "text/plain;charset=utf-8")
    public String allStudy(){
        List<Study> allStudy = iStudyService.findAllStudy();
        Map<Object,Object> studyMap = new HashMap<>();
        if(!allStudy.isEmpty()) {
            studyMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            studyMap.put("data", allStudy);
            return JSON.toJSONString(studyMap);
        }else{
            studyMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(studyMap);
        }
    }
    /*
    前台通过请求获得经由时间排序以及前五条的教学科研
     */
    @ApiOperation(value="查询由前五条的教学科研", notes="前端通过访问接口获得所需教学科研")
    @GetMapping(value = "/allstudylatestfive",produces = "text/plain;charset=utf-8")
    public String allStudyLimitFive(){
        Map<Object,Object> allStudyeOnPaging = iStudyService.findAllStudy(1,5);
        if(!allStudyeOnPaging.isEmpty()) {
            allStudyeOnPaging.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(allStudyeOnPaging);
        }else{
            allStudyeOnPaging.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allStudyeOnPaging);
        }
    }

    /*
    前台通过请求获得经由时间排序以及分页后的教学科研
     */
    @ApiOperation(value="查询分页后的的教学科研", notes="前端通过访问接口获得所需教学科研")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "几条数据", required = true, dataType = "String"),
    })
    @GetMapping(value = "/allstudy/{pageNum}/{pageSize}",produces = "text/plain;charset=utf-8")
    public String allStudyOnPaging(@PathVariable("pageNum") int pageNum,@PathVariable("pageSize") int pageSize){
        Map<Object,Object> allStudyeOnPaging = iStudyService.findAllStudy(pageNum,pageSize);
        if(!allStudyeOnPaging.isEmpty()) {
            allStudyeOnPaging.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(allStudyeOnPaging);
        }else{
            allStudyeOnPaging.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allStudyeOnPaging);
        }
    }
    @ApiOperation(value="查询由前五条的教学科研", notes="前端通过访问接口获得所需教学科研")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String")
    @PostMapping(value = "/findstudybyid/{id}",produces = "text/plain;charset=utf-8")
    public String findStudyById(@PathVariable("id") int id){
        Study studyById = iStudyService.findById(id);
        Map<Object,Object> study = new HashMap<>();
        study.put("data",studyById);
        study.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        return JSON.toJSONString(study);
    }

    /*
    查询连续两条的教学科研
    */
    @ApiOperation(value="查询由时间排序的连续两条的教学科研", notes="前端通过访问接口获得所需教学科研")
    @ApiImplicitParam(name = "studyOffsetId", value = "第几篇", required = true, dataType = "String")
    @GetMapping(value = "/findstudynexttonext/{studyOffsetId}",produces = "text/plain;charset=utf-8")
    public String findStudyNextToNext(@PathVariable("studyOffsetId") int studyOffsetId){
        Map<Object, Object> newsStudyNextToNext = iNewsService.findNewsNextToNext(studyOffsetId-1);
        if(!newsStudyNextToNext.isEmpty()){
            newsStudyNextToNext.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(newsStudyNextToNext);
        }else {
            newsStudyNextToNext.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(newsStudyNextToNext);
        }
    }
}
