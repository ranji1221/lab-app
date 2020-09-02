package com.ranji.lab.mapper;

import com.ranji.lab.dto.*;
import com.ranji.lab.entity.Device;
import com.ranji.lab.entity.LaboratoryDevice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DeviceMapper {

    //插入
    @Insert("insert into device (factime,device_model_id,uuid) values (#{factime},#{deviceModelId},#{uuid})")
    int insertDevice(Device device);
    //修改
    @Update("update device set factime = #{factime},device_model_id = #{deviceModelId},uuid = #{uuid}  where id = #{id}")
    int updateDevice(Device device);
    //查询全部
    @Select("select device.* from device")
    List<Device> findAll();

    /**
     * 通过传来的uuid判断是否存在该设备
     * @return
     */
    @Select("select * from device where uuid =#{uuid}")
    List<Device> findDeviceByuuid(String uuid);

    /**
     * 通过传来的uuid获取设备id
     * @return
     */
    @Select("select id from device where uuid =#{uuid}")
    int findDeviceIdByuuid(String uuid);

    @Select("select * from device d left join device_model dm on dm.id=d.device_model_id")
    List<DeviceAndModelDto> findAllDeviceAndModel();
    //按照id查询
    @Select("select device.* from device where device.id = #{id}")
    Device findById(int id);
    //按照类型查询
    @Select("select device.* from device where type = #{type}")
    List<DeviceDto> findAllDeviceByTypeId(int type);

    @Select("select * from device d left join device_model dm on dm.id = d.device_model_id left join  device_type dt on dm.type=dt.type_name")
    List<DeviceAndDeviceTypeNameDto> findDeviceAndDeviceName();

    //智能分析
    @Select("SELECT d.id,dm.device_name,dm.brand,ld.status,count(*) count,l.laboratory_name,dm.unit_name unitName FROM device d JOIN device_model dm ON d.device_model_id=dm.id JOIN laboratory_device ld ON ld.device_id=d.id JOIN laboratory l ON l.id=ld.laboratory_id GROUP BY ld.laboratory_id,ld.STATUS")
    List<DeviceIntelligentAnalyzeDto> findIntelligentAnalyze();

    //按照设备型号，查询没有分配实验室的设备
    @Select("select * from device d  where d.device_model_id = #{deviceModelId} and d.id not in (select device_id id from laboratory_device) limit #{num}")
    List<Device> findNoAllocationDevice(int deviceModelId,int num);

    //没有分配实验室的设备的数量
    @Select("select dm.id,count(*) count,dm.device_name deviceName from device d left join device_model dm on dm.id = d.device_model_id where d.id not in (select device_id id from laboratory_device) GROUP BY device_model_id")
    List<LaboratoryDeviceNumDto> findNoAllocationDeviceTypeNum();

    //按照实验室查询拥有设备数量
    @Select("select dm.id,dm.device_name,count(*) count from device d left join device_model dm on dm.id = d.device_model_id left join laboratory_device ld on ld.device_id = d.id  where ld.laboratory_id = #{laboratoryId} GROUP BY d.device_model_id")
    List<LaboratoryDeviceNumDto> laboratoryIdFindDevice(int laboratoryId);

    //按照实验室id查询设备信息、数量及设备状态
    @Select("select d.device_model_id id,dm.device_name deviceName,ld.status status,count(*) count from laboratory_device ld join device d on ld.device_id = d.id join device_model dm on d.device_model_id = dm.id where ld.laboratory_id = #{laboratoryId} GROUP BY ld.`status`,d.device_model_id")
    List<LaboratoryDeviceNumDto> laboratoryIdFindDeviceAndStatus(int laboratoryId);

    //按照实验室id查询所有的设备
    @Select("select ld.*,d.uuid,dm.device_name from laboratory_device ld left join device d on d.id = ld.device_id left join device_model dm on dm.id = d.device_model_id where ld.laboratory_id = #{laboratoryId} and d.status != 2")
    List<DeviceMsgDto> laboratoryIdFindAllDevice(int laboratoryId);

    //按照实验室id查询所有分类
    @Select("select d.device_model_id from laboratory_device ld join device d on d.id = ld.device_id where ld.laboratory_id = #{laboratoryId} GROUP BY d.device_model_id ")
    List<Integer> findDeviceModel(int laboratoryId);
    //按照设备状态、设备分类和实验室id查询数据
    @Select("select d.device_model_id id,dm.device_name deviceName,count(*) count from laboratory_device ld join device d on ld.device_id = d.id join device_model dm on d.device_model_id = dm.id where ld.laboratory_id = #{laboratoryId} and d.device_model_id = #{deviceModelId} and d.status = #{status}")
    LaboratoryDeviceNumDto findDeviceStatusNum(int laboratoryId,int deviceModelId,int status);

    //按照实验室id查询该实验室已完成的实验数量
    @Select("select count(*) from project_device pd join arrange a on pd.arrange_project_id = a.id where a.status = 1 and a.laboratory_id = #{laboratoryId}")
    int findEndingProjectNumByLaboratoryId(int laboratoryId);

    //按照实验室id、设备id查询该实验室使用到该设备的实验数量
    @Select("select count(*) from project_device pd join arrange a on pd.arrange_project_id = a.id where a.status = 1 and a.laboratory_id = #{laboratoryId} and pd.experiment_device_id = #{deviceId}")
    int findEndingProjectNumByLaboratoryIdAndDeviceId(int laboratoryId,int deviceId);

}
