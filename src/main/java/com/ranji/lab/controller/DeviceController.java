package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.Device;
import com.ranji.lab.entity.LabInformation;
import com.ranji.lab.entity.Monitaring;
import com.ranji.lab.service.prototype.IDeviceService;
import com.ranji.lab.service.prototype.ILabInformationService;
import com.ranji.lab.service.prototype.IMonitaringService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "设备接口")
@RestController
public class DeviceController {
    @Resource
    private IDeviceService iDeviceService;
    @Resource
    private IMonitaringService iMonitaringService;
    @Resource
    private ILabInformationService iLabInformationService;

    /*
    通过前台表单的数据插入设备信息
     */
    @ApiOperation(value="插入设备信息", notes="根据传过来的设备信息来插入设备信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceName", value = "设备名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "brand", value = "品牌型号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "num", value = "设备数量", required = true, dataType = "String"),
            @ApiImplicitParam(name = "facid", value = "出厂编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "factime", value = "出厂日期(xxxx-xx-xx)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "proid", value = "生产厂商编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "supid", value = "供应商编号", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    @PostMapping(value="insertdevice",produces = "text/plain;charset=utf-8")
    public String insertDevice(Device device){
        Map<Object,Object> insertDeviceMap = new HashMap<>();
        int i = iDeviceService.insertDevice(device);
        if(i<1){
            insertDeviceMap.put("status","failure");
            return JSON.toJSONString(insertDeviceMap);
        }else{
            insertDeviceMap.put("status","success");
            return JSON.toJSONString(insertDeviceMap);
        }
    }
    /*
    通过前台表单的数据更新设备信息
     */
    @ApiOperation(value="更新设备信息", notes="根据传过来的设备信息来更新设备信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceName", value = "设备名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "brand", value = "品牌型号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "num", value = "设备数量", required = true, dataType = "String"),
            @ApiImplicitParam(name = "facid", value = "出厂编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "factime", value = "出厂日期(xxxx-xx-xx)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "proid", value = "生产厂商编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "supid", value = "供应商编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "更新设备id", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    @PostMapping(value="updatedevice",produces = "text/plain;charset=utf-8")
    public String updateDevice(HttpServletRequest request){
        Map<Object,Object> updateDeviceMap = new HashMap<>();

        String deviceName = request.getParameter("");
        String brand = request.getParameter("");
        String conid = request.getParameter("");
        int num = Integer.parseInt(request.getParameter(""));
        String roomnames = request.getParameter("");
        String facid = request.getParameter("");
        String factime = request.getParameter("");
        String proid = request.getParameter("");
        String supid = request.getParameter("");
        int id = Integer.parseInt(request.getParameter(""));

        Device device = new Device(id,deviceName, brand, conid, num, roomnames, facid, factime, proid, supid);
        int i = iDeviceService.updateDevice(device);
        if(i<1){
            updateDeviceMap.put("status","failure");
            return JSON.toJSONString(updateDeviceMap);
        }else{
            updateDeviceMap.put("status","success");
            return JSON.toJSONString(updateDeviceMap);
        }
    }
    /*
    前台通过请求获得设备信息
     */

    @ApiOperation(value="获取所有设备信息", notes="调用接口直接返回所有设备信息")
    @GetMapping(value = "/alldevice",produces = "text/plain;charset=utf-8")
    public String allDevice(){
        List<Device> allDevice = iDeviceService.findAllDevice();
        Map<Object,Object> allDeviceMap = new HashMap<>();
        if(!allDevice.isEmpty()) {
            allDeviceMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            allDeviceMap.put("data", allDevice);
            return JSON.toJSONString(allDeviceMap);
        }else{
            allDeviceMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allDeviceMap);
        }
    }
    @GetMapping(value = "/alldevices",produces = "text/plain;charset=utf-8")
    public String allDevices(int page,int limit){
        Map<Object,Object> allDeviceMap = iDeviceService.findAllDevice(page,limit);
        if(!allDeviceMap.isEmpty()){
            allDeviceMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(allDeviceMap);
        }else{
            allDeviceMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allDeviceMap);
        }
    }
    /*
    前台通过请求获得分页后的设备信息
    */
    @ApiOperation(value="获取分页后的设备信息", notes="根据传过来的分页信息来查询设备信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页几条", required = true, dataType = "String")
    })
    @GetMapping(value = "/alldevice/{pagenum}/{pagesize}",produces = "text/plain;charset=utf-8")
    public String allDeviceOnPaging(@PathVariable("pagenum") int pageNum, @PathVariable("pagesize") int pageSize){
        Map<Object,Object> allDeviceOnPaging = iDeviceService.findAllDevice(pageNum,pageSize);
        if(!allDeviceOnPaging.isEmpty()) {
            allDeviceOnPaging.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(allDeviceOnPaging);
        }else{
            allDeviceOnPaging.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allDeviceOnPaging);
        }
    }

    /*
    通过前台表单的数据插入项目信息
     */
    @PostMapping(value="insertlabinformation",produces = "text/plain;charset=utf-8")
    public String insertLabInformation(HttpServletRequest request){
        Map<Object,Object> insertLabInformationMap = new HashMap<>();

        String ltitle = request.getParameter("");
        String lfaculty = request.getParameter("");
        String ldevice = request.getParameter("");
        String lteacher = request.getParameter("");
        String ldate = request.getParameter("");
        String ltime = request.getParameter("");
        String lstatus = request.getParameter("");
        LabInformation labInformation = new LabInformation(ltitle, lfaculty, ldevice, lteacher, ldate, ltime, lstatus);
        int i = iLabInformationService.insertLabInformation(labInformation);
        if(i<1){
            insertLabInformationMap.put("status","failure");
            return JSON.toJSONString(insertLabInformationMap);
        }else{
            insertLabInformationMap.put("status","success");
            return JSON.toJSONString(insertLabInformationMap);
        }
    }
    /*
    通过前台表单的数据更新项目信息
     */
    @PostMapping(value="updatelabinformation",produces = "text/plain;charset=utf-8")
    public String updateLabInformation(HttpServletRequest request){
        Map<Object,Object> updateLabInformationMap = new HashMap<>();

        String ltitle = request.getParameter("");
        String lfaculty = request.getParameter("");
        String ldevice = request.getParameter("");
        String lteacher = request.getParameter("");
        String ldate = request.getParameter("");
        String ltime = request.getParameter("");
        String lstatus = request.getParameter("");
        int id = Integer.parseInt(request.getParameter(""));
        LabInformation labInformation = new LabInformation(id,ltitle, lfaculty, ldevice, lteacher, ldate, ltime, lstatus);
        int i = iLabInformationService.updateLabInformation(labInformation);
        if(i<1){
            updateLabInformationMap.put("status","failure");
            return JSON.toJSONString(updateLabInformationMap);
        }else{
            updateLabInformationMap.put("status","success");
            return JSON.toJSONString(updateLabInformationMap);
        }
    }
    /*
    前台通过请求获得经由时间排序处理后的设备信息
     */
    @GetMapping(value = "/alllabinformation",produces = "text/plain;charset=utf-8")
    public String allLabInformation(){
        List<LabInformation> allLabInformation = iLabInformationService.findAllLabInformation();
        Map<Object,Object> allLabInformationMap = new HashMap<>();
        if(!allLabInformation.isEmpty()) {
            allLabInformationMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            allLabInformationMap.put("data", allLabInformation);
            return JSON.toJSONString(allLabInformationMap);
        }else{
            allLabInformationMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allLabInformationMap);
        }
    }
    /*
    前台通过请求获得经由时间排序处理后并且分页后的设备信息
     */
    @GetMapping(value = "/alllabinformation/{pagenum}/{pagesize}",produces = "text/plain;charset=utf-8")
    public String allLabInformationOnPaging(@PathVariable("pagenum") int pageNum,@PathVariable("pagesize") int pageSize){
        Map<Object,Object> allLabInformationOnPaging = iLabInformationService.findAllLabInformation(pageNum,pageSize);
        if(!allLabInformationOnPaging.isEmpty()) {
            allLabInformationOnPaging.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(allLabInformationOnPaging);
        }else{
            allLabInformationOnPaging.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allLabInformationOnPaging);
        }
    }

    /*
    通过前台表单的数据插入智能监控信息
     */
    @PostMapping(value="insertmonitaring",produces = "text/plain;charset=utf-8")
    public String insertMonitaring(HttpServletRequest request){
        Map<Object,Object> insertMonitaringMap = new HashMap<>();

        String laboratoryName = request.getParameter("");
        String faculty = request.getParameter("");
        String experimentName = request.getParameter("");
        String experimentTime = request.getParameter("");
        String time = request.getParameter("");
        String responsibility = request.getParameter("");
        String status = request.getParameter("");
        Monitaring monitaring = new Monitaring(laboratoryName, faculty, experimentName, experimentTime, time, responsibility, status);
        int i = iMonitaringService.insertMonitaring(monitaring);
        if(i<1){
            insertMonitaringMap.put("status","failure");
            return JSON.toJSONString(insertMonitaringMap);
        }else{
            insertMonitaringMap.put("status","success");
            return JSON.toJSONString(insertMonitaringMap);
        }
    }
    /*
    通过前台表单的数据更新智能监控信息
     */
    @PostMapping(value="updatemonitaring",produces = "text/plain;charset=utf-8")
    public String updateMonitaring(HttpServletRequest request){
        Map<Object,Object> updateMonitaringMap = new HashMap<>();

        String laboratoryName = request.getParameter("");
        String faculty = request.getParameter("");
        String experimentName = request.getParameter("");
        String experimentTime = request.getParameter("");
        String time = request.getParameter("");
        String responsibility = request.getParameter("");
        String status = request.getParameter("");
        int id = Integer.parseInt(request.getParameter(""));
        Monitaring monitaring = new Monitaring(id,laboratoryName, faculty, experimentName, experimentTime, time, responsibility, status);
        int i = iMonitaringService.updateMonitaring(monitaring);
        if(i<1){
            updateMonitaringMap.put("status","failure");
            return JSON.toJSONString(updateMonitaringMap);
        }else{
            updateMonitaringMap.put("status","success");
            return JSON.toJSONString(updateMonitaringMap);
        }
    }
    /*
    前台通过请求获得经由时间排序处理后智能监控信息
     */
    @GetMapping(value = "/allmonitaring",produces = "text/plain;charset=utf-8")
    public String allMonitaring(){
        List<Monitaring> allMonitaring = iMonitaringService.findAllMonitaring();
        Map<Object,Object> allMonitaringMap = new HashMap<>();
        if(!allMonitaring.isEmpty()) {
            allMonitaringMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            allMonitaringMap.put("data", allMonitaring);
            return JSON.toJSONString(allMonitaringMap);
        }else{
            allMonitaringMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allMonitaringMap);
        }
    }
    /*
    前台通过请求获得经由时间排序处理后并且分页后的智能监控信息
     */
    @GetMapping(value = "/allmonitaring/{pagenum}/{pagesize}",produces = "text/plain;charset=utf-8")
    public String allMonitaring(@PathVariable("pagenum") int pageNum,@PathVariable("pagesize") int pageSize){
        Map<Object,Object> allMonitaringOnPaging = iMonitaringService.findAllMonitaring(pageNum,pageSize);
        if(!allMonitaringOnPaging.isEmpty()) {
            allMonitaringOnPaging.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(allMonitaringOnPaging);
        }else{
            allMonitaringOnPaging.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allMonitaringOnPaging);
        }
    }
}
