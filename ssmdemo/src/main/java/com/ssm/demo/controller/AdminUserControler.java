package com.ssm.demo.controller;


import com.ssm.demo.common.Constants;
import com.ssm.demo.common.Result;
import com.ssm.demo.common.ResultGenerator;
import com.ssm.demo.controller.annotation.TokenToUser;
import com.ssm.demo.entity.AdminUser;
import com.ssm.demo.service.AdminUserService;
import com.ssm.demo.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @Author hsir
 * @Date 16:24 2019/1/31/031       
 * @Param 
 * @return 
 **/
@RestController
@RequestMapping("/users")
public class AdminUserControler {

    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody AdminUser user){
        Result result = ResultGenerator.genFailResult("登录失败!");
        if(StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())){
                result.setMessage("请填写登录信息");
        }
        AdminUser loginUser = adminUserService.updateTokenAndLogin(user.getUserName(), user.getPassword());
        if(loginUser != null){
                result = ResultGenerator.genSuccessResult(loginUser);
        }
        return result;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result list(@RequestParam Map<String,Object> params){
        if(StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))){
                return  ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"参数异常");
        }
        PageUtil pageUtil = new PageUtil(params);
        return ResultGenerator.genSuccessResult(adminUserService.getAdminUserPage(pageUtil));
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result save(@RequestBody AdminUser user, @TokenToUser AdminUser loginUser){
        //先判断用户是否登录
        if(loginUser == null){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_NOT_LOGIN,"用户未登录!");
        }
        if(StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"用户名或密码不能为空!");
        }
        AdminUser adminUser = adminUserService.selectByUserName(user.getUserName());
        if(adminUser != null){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_BAD_REQUEST,"用户已存在!");
        }
        if("admin".endsWith(user.getUserName().trim())){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_BAD_REQUEST,"不能添加admin用户!");
        }
        if(adminUserService.save(user) > 0){
            return ResultGenerator.genSuccessResult();
        }else{
            return ResultGenerator.genFailResult("添加失败!");
        }
    }

    @RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
    public Result updatePassword(@RequestBody AdminUser user,@TokenToUser AdminUser loginUser ){
        if(loginUser == null){
            return   ResultGenerator.genErrorResult(Constants.RESULT_CODE_NOT_LOGIN,"用户未登录!");
        }
        if(StringUtils.isEmpty(user.getPassword())){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"密码不能为空!");
        }
        if(adminUserService.selectByUserId(user.getId()) != null){
            if(adminUserService.updateUserPassword(user) > 0){
                return ResultGenerator.genSuccessResult();
            }else{
                return ResultGenerator.genErrorResult(Constants.RESULT_CODE_SERVER_ERROR,"更新失败!");
            }
        }else{
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"用户不存在!");
        }
    }

}
