package com.ssm.demo.service;

import com.ssm.demo.entity.AdminUser;

public interface AdminUserService {
    /**
     * 登录并更新tocken
     * @return
     */
    AdminUser updateTokenAndLogin(String name,String password);

    /**
     * 根据token获取用户记录
     */

    AdminUser getAdminUserByToken(String userToken);
}
