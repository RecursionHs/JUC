package com.ssm.demo.controller;

import com.ssm.demo.common.Result;
import com.ssm.demo.common.ResultGenerator;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Author hsir
 * @Date 2019/3/8/008
 * @Version 1.0
 **/
public class LoadImageController {

    public Result upload(HttpServletRequest request, @RequestParam("file")MultipartFile file){
        ServletContext context = request.getSession().getServletContext();
        String uploadDir = context.getRealPath("upload");
        System.out.println(uploadDir+"  :!!!!!!!!!!!!! "+file.getOriginalFilename().lastIndexOf("."));
        String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random random = new Random();
        String imaName = "";
        if("jpg".equals(type)){
            imaName = dateFormat.format(new Date()) + random.nextInt(100) + "jpg";
        }else if("png".equals(type)){
            imaName = dateFormat.format(new Date()) + random.nextInt(100) + "png";
        }else if("jpeg".equals(type)){
            imaName = dateFormat.format(new Date()) + random.nextInt(100) + "jpeg";
        }else if("gif".equals(type)){
            imaName = dateFormat.format(new Date()) + random.nextInt(100) + "gif";
        }else {
            return null;
        }
        try {
            //将文件流写入到磁盘中
            FileUtils.writeByteArrayToFile(new File(uploadDir,imaName),file.getBytes());
            Result result = ResultGenerator.genSuccessResult();
            result.setData("/upload/" + imaName);
            return result;
        }catch (Exception e){
            System.out.println("文件写入失败！");
            return null;
        }

    }
}
