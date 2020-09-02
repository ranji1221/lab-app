package com.ranji.lab.service.prototype;

import com.ranji.lab.entity.Auth;

import java.util.List;
import java.util.Map;

/**
 * ClassName:    IAuthService
 * Package:    com.ranji.lab.service.prototype
 * Description: 权限业务接口
 * Datetime:    2020/9/1   3:38 下午
 * Author:   ranji
 */
public interface IAuthService {
    /**
     * 获取到所有的权限信息，按照权限的分类分组
     * @return
     */
    List<Map<String,Object>> getAllAuths();

    /**
     * 根据用户名获取该用户的所有权限
     * @param userName
     * @return
     */
    List<Auth> getAllAuths(String userName);
}