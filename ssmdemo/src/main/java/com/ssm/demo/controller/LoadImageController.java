package com.ssm.demo.controller;

import com.ssm.demo.common.Result;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author hsir
 * @Date 2019/3/8/008
 * @Version 1.0
 **/
public class LoadImageController {

    public Result upload(HttpServletRequest request, @RequestParam("file")MultipartFile file){
        return null;
    }
}
