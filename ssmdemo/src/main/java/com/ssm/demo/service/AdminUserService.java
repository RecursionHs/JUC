package com.ssm.demo.service;

import com.ssm.demo.entity.AdminUser;
import com.ssm.demo.utils.PageResult;
import com.ssm.demo.utils.PageUtil;



/**
 * @Author hsir
 * @Date 10:36 2019/2/1/001       
 * @Param 
 * @return 
 **/
public interface AdminUserService {
    /**
     * @Author hsir
     * @Date 10:32 2019/2/1/001
     * @Param [name, password]
     * @return com.ssm.demo.entity.AdminUser
     * 登录并更新tocken
     **/
    AdminUser updateTokenAndLogin(String name,String password);


    /**
     * @Author hsir
     * @Date 10:33 2019/2/1/001
     * @Param [userToken]
     * @return com.ssm.demo.entity.AdminUser
     * 根据token获取用户记录
     **/
    AdminUser getAdminUserByToken(String userToken);


    PageResult getAdminUserPage(PageUtil pageUtil);

    AdminUser selectByUserName(String userName);

    int save(AdminUser user);

    int updateUserPassword(AdminUser user);

    AdminUser selectByUserId(Long id);
}
