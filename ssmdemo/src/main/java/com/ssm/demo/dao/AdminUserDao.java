package com.ssm.demo.dao;

import com.ssm.demo.entity.AdminUser;
import com.ssm.demo.utils.PageUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据分页参数查出数据
     * @Author hsir
     * @Date 13:56 2019/2/22/022
     * @Param [param]
     * @return java.util.List<com.ssm.demo.entity.AdminUser>
     **/
    List<AdminUser> findAdminUsers(Map param);
    
    /**
     * 查询总条数
     * @Author hsir
     * @Date 13:56 2019/2/22/022
     * @Param [param]
     * @return int
     **/
    int getTotalAdminUser(Map param);
}
