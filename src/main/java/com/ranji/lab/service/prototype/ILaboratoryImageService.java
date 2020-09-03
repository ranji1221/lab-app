package com.ranji.lab.service.prototype;

import com.ranji.lab.dto.LaboratoryDto;
import com.ranji.lab.dto.StatusMonitoringDto;
import com.ranji.lab.entity.Laboratory;
import com.ranji.lab.entity.LaboratoryImage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface ILaboratoryImageService {
    /**
     * 插入实验室图片
     * @param laboratory
     * @return
     *
     */
    int insertLaboratoryImage(LaboratoryImage laboratoryImage);

    /**
     * 通过id获取实验室id图片
     * @param id
     * @return
     */
    Laboratory findlaboratoryImageById(int id);
}
