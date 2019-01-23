package com.ssm.demo.dao;

import com.ssm.demo.entity.AdminUser;
import org.apache.ibatis.annotations.Param;

public interface AdminUserDao {

    /**
     * 根据登录用户名及密码获取token
     */
    AdminUser getAdminUserByUserNameAndPassword(@Param("userName") String userName,@Param("passWordMd5") String passWordMd5);

    /**
     * 根据userToken获取用户记录
     */
    AdminUser getAdminUserByToken(String userToken);

    /**
     * 更新用户token值
     */
    int updateUserToken(@Param("userId") Long userId,@Param("newToken") String newToken);
}
