package com.ranji.lab.entity;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName:    Employee
 * Package:    com.ranji.lab.entity
 * Description: 员工实体类
 * Datetime:    2020/9/15   6:06 下午
 * Author:   ranji
 */
@Data
@NoArgsConstructor
public class Employee implements Serializable {
    private int id;
    private String realName;
    private int gender;
    private Date birth ;
    private String address;

    public Employee(int id, String realName, String gender, String birth, String address) {
        this.id = id;
        this.realName = realName;
        this.gender = (gender.equals("男") ? 1: 0);
        try {
            this.birth = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.address = address;
    }

    public String getBirth() {
        return new SimpleDateFormat("yyyy-MM-dd").format(birth);
    }

    public void setBirth(String birth) {
        try {
            this.birth = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getGender() {
        return (gender==0 ? "女":"男");
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static void main(String[] args) {
        Employee emp = new Employee(3,"哈哈","女","2020-11-30","all豆腐块大家疯狂路上风景");
        System.out.println(emp);
    }
}