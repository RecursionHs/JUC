package com.ssm.demo.service.impl;

import com.ssm.demo.dao.PictureDao;
import com.ssm.demo.entity.Picture;
import com.ssm.demo.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author hsir
 * @Date 2019/3/15/015 16:25
 * @Version 1.0
 **/
@Service("PictureService")
public class PictureServiceImpl implements PictureService {

     @Autowired
     private PictureDao pictureDao;


    @Override
    public int save(Picture picture) {
        return pictureDao.save(picture);
    }
}
