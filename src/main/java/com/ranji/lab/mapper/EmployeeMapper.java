package com.ranji.lab.mapper;

import com.ranji.lab.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * ClassName:    EmployeeMapper
 * Package:    com.ranji.lab.mapper
 * Description: 员工映射类
 * Datetime:    2020/9/15   6:25 下午
 * Author:   ranji
 */
public interface EmployeeMapper {
    @Insert("insert into t_employee(id,realName,gender,birth,address) values(#{id},#{realName},#{gender},#{birth},#{address})")
    void save(Employee employee);

    /**
     * 查找某用户对应的员工
     * @param userName
     * @return
     */
    @Select("select e.* from t_employee e left join t_user u on e.id=u.id where u.name=#{userName}")
    Employee find(String userName);
}