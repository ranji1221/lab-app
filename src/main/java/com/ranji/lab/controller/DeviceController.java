package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.dto.DeviceAndDeviceTypeNameDto;
import com.ranji.lab.dto.DeviceAndModelDto;
import com.ranji.lab.dto.DeviceDto;
import com.ranji.lab.dto.DeviceTypeDto;
import com.ranji.lab.entity.*;
import com.ranji.lab.service.prototype.IDeviceService;
import com.ranji.lab.service.prototype.IDeviceTypeService;
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
    private IDeviceTypeService iDeviceTypeService;

    /*
    通过前台表单的数据插入设备信息
     */
    @ApiOperation(value="插入设备信息", notes="根据传过来的设备信息来插入设备信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceName", value = "设备名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "brand", value = "品牌型号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "facid", value = "出厂编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "count", value = "数量", required = true, dataType = "String"),
            @ApiImplicitParam(name = "factime", value = "出厂日期(xxxx-xx-xx)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "proid", value = "生产厂商编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "supid", value = "供应商编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "设备类型id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "lifetime", value = "使用周期", required = true, dataType = "String"),
            @ApiImplicitParam(name = "unitName", value = "单位", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    @PostMapping(value="insertdevice",produces = "text/plain;charset=utf-8")
    public String insertDevice(DeviceAndModelDto deviceAndModelDto){
        Map<Object,Object> insertDeviceMap = new HashMap<>();
        int i = iDeviceService.insertDeviceAndDeviceModel(deviceAndModelDto);
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
            @ApiImplicitParam(name = "count", value = "数量", required = true, dataType = "String"),
            @ApiImplicitParam(name = "facid", value = "出厂编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "factime", value = "出厂日期(xxxx-xx-xx)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "proid", value = "生产厂商编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "supid", value = "供应商编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "更新设备id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "lifetime", value = "使用周期", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "设备类型id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "unitName", value = "单位", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    @PostMapping(value="updatedevice",produces = "text/plain;charset=utf-8")
    public String updateDevice(DeviceAndModelDto deviceAndModelDto){
        Map<Object,Object> updateDeviceMap = new HashMap<>();
        int i = iDeviceService.updateDeviceAndDeviceModel(deviceAndModelDto);
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
    @ApiOperation(value="获取所有设备信息(?x=a&y=b)", notes="调用接口直接返回所有设备信息")
    @GetMapping(value = "/alldevices",produces = "text/plain;charset=utf-8")
    public String allDevices(int page,int limit){
        Map<Object,Object> allDeviceMap = iDeviceService.findDeviceAndModel(page,limit);
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
    @ApiOperation(value="获取分页后的设备信息(/a/b)", notes="根据传过来的分页信息来查询设备信息")
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
    通过前台表单的数据插入设备类型信息
     */
    @ApiOperation(value="插入设备类型信息", notes="根据传过来的设备信息来插入设备类型信息")
    @ApiImplicitParam(name = "typeName", value = "设备类型名称", required = true, dataType = "String")
    @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    @PostMapping(value="insertdevicetype",produces = "text/plain;charset=utf-8")
    public String insertDeviceType(DeviceTypeDto deviceTypeDto){
        Map<Object,Object> insertDeviceTypeMap = new HashMap<>();
        int i = iDeviceTypeService.insertDeviceType(deviceTypeDto);
        if(i<1){
            insertDeviceTypeMap.put("status","failure");
            return JSON.toJSONString(insertDeviceTypeMap);
        }else{
            insertDeviceTypeMap.put("status","success");
            return JSON.toJSONString(insertDeviceTypeMap);
        }
    }

    /*
    通过前台表单的数据更新设备类型信息
     */
    @ApiOperation(value="更新设备类型信息", notes="根据传过来的设备信息来更新设备类型信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeName", value = "设备类型名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "设备类型id", required = true, dataType = "String")
    })
       @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    @PostMapping(value="updatedevicetype",produces = "text/plain;charset=utf-8")
    public String updateDeviceType(DeviceType deviceType){
        Map<Object,Object> updateDeviceTypeMap = new HashMap<>();
        int i = iDeviceTypeService.updateDeviceType(deviceType);
        if(i<1){
            updateDeviceTypeMap.put("status","failure");
            return JSON.toJSONString(updateDeviceTypeMap);
        }else{
            updateDeviceTypeMap.put("status","success");
            return JSON.toJSONString(updateDeviceTypeMap);
        }
    }

    @ApiOperation(value="查找所有设备类型信息", notes="根据传过来的设备信息来查询设备类型信息")
    @GetMapping(value="alldevicetype",produces = "text/plain;charset=utf-8")
    public String allDeviceType(){
        Map<Object, Object> objectObjectMap = iDeviceTypeService.allDeviceType();
        if(!objectObjectMap.isEmpty()){
            objectObjectMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(objectObjectMap);
        }else{
            objectObjectMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(objectObjectMap);
        }
    }

    @ApiOperation(value="通过id查找所有设备信息", notes="根据传过来的id来查询设备类型信息")
    @GetMapping(value="alldevicebyid",produces = "text/plain;charset=utf-8")
    @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    public String allDeviceById(int id){
        Map<Object, Object> AllDeviceByTypeIdMap = iDeviceService.findAllDeviceByTypeId(id);
        if(!AllDeviceByTypeIdMap.isEmpty()){
            AllDeviceByTypeIdMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(AllDeviceByTypeIdMap);
        }else{
            AllDeviceByTypeIdMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(AllDeviceByTypeIdMap);
        }
    }
    @ApiOperation(value="查询所有设备和设备类型", notes="查询所有设备和设备类型")
    @GetMapping(value = "alldeviceanddevicename",produces = "text/plain;charset=utf-8")
    public String allDeviceAndDeviceName(){
        Map<Object,Object> allDeviceAndDeviceNameMap = new HashMap<>();
        List<DeviceAndDeviceTypeNameDto> deviceAndDeviceName = iDeviceService.findDeviceAndDeviceName();
        if(!deviceAndDeviceName.isEmpty()){
            allDeviceAndDeviceNameMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            allDeviceAndDeviceNameMap.put("data",deviceAndDeviceName);
            return JSON.toJSONString(allDeviceAndDeviceNameMap);
        }else{
            allDeviceAndDeviceNameMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allDeviceAndDeviceNameMap);
        }
    }
}
