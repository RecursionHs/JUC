package com.ssm.demo.controller;

import com.ssm.demo.common.Constants;
import com.ssm.demo.common.Result;
import com.ssm.demo.common.ResultGenerator;
import com.ssm.demo.controller.annotation.TokenToUser;
import com.ssm.demo.entity.AdminUser;
import com.ssm.demo.entity.Picture;
import com.ssm.demo.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author hsir
 * @Date 2019/3/15/015 15:54
 * @Version 1.0
 **/
@RestController
@RequestMapping("/pictures")
public class PictureController {

        @Autowired
        private PictureService pictureService;

        @RequestMapping("/save")
        public Result savePictures(@RequestBody Picture picture, @TokenToUser AdminUser loginUser){
            if(loginUser == null){
                return ResultGenerator.genErrorResult(Constants.RESULT_CODE_NOT_LOGIN,"未登录！");
            }
            if(StringUtils.isEmpty(picture.getPath()) || StringUtils.isEmpty(picture.getRemark())){
                return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"参数异常");
            }

            if(pictureService.save(picture) > 0){
                return ResultGenerator.genSuccessResult();
            }else{
                return ResultGenerator.genFailResult("添加失败");
            }

        }

}
