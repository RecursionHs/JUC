package com.ssm.demo.service.impl;

import com.ssm.demo.dao.AdminUserDao;
import com.ssm.demo.entity.AdminUser;
import com.ssm.demo.service.AdminUserService;
import com.ssm.demo.utils.MD5Util;
import com.ssm.demo.utils.NumberUtil;
import com.ssm.demo.utils.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService {

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
        return null;
    }

    private String getNewToken(String sessionId,Long userId){
        String src = sessionId + userId + NumberUtil.genRandomNum(4);
        return SystemUtil.genToken(src);
    }

}
