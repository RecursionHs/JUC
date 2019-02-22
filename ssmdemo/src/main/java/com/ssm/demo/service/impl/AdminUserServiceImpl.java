package com.ssm.demo.service.impl;

import com.ssm.demo.dao.AdminUserDao;
import com.ssm.demo.entity.AdminUser;
import com.ssm.demo.service.AdminUserService;
import com.ssm.demo.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author hsir
 * @Date 10:37 2019/2/1/001       
 * @Param 
 * @return 
 **/
@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService
{

    @Autowired
    private AdminUserDao adminUserDao;

    @Override
    public AdminUser updateTokenAndLogin(String name, String password) {
        AdminUser user = adminUserDao.getAdminUserByUserNameAndPassword(name, MD5Util.MD5Encode(password, "UTF-8"));
        if(user != null){
            String token = getNewToken(System.currentTimeMillis() + "", user.getId());
            if(adminUserDao.updateUserToken(user.getId(),token) > 0){
                user.setUserToken(token);
                return user;
            }
        }

        return null;
    }

    @Override
    public AdminUser getAdminUserByToken(String userToken) {
        AdminUser  adminUser= adminUserDao.getAdminUserByToken(userToken);
        return adminUser;
    }

    @Override
    public PageResult getAdminUserPage(PageUtil pageUtil) {
        List<AdminUser> adminUsers = adminUserDao.findAdminUsers(pageUtil);
        int totalAdminUser = adminUserDao.getTotalAdminUser(pageUtil);
        PageResult pageResult = new PageResult(totalAdminUser, pageUtil.getPage(), adminUsers, pageUtil.getLimit());
        return pageResult;
    }

    private String getNewToken(String sessionId,Long userId){
        String src = sessionId + userId + NumberUtil.genRandomNum(4);
        return SystemUtil.genToken(src);
    }






}
