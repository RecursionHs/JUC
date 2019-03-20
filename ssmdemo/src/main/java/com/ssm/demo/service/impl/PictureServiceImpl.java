package com.ssm.demo.service.impl;

import com.ssm.demo.dao.PictureDao;
import com.ssm.demo.entity.Picture;
import com.ssm.demo.service.PictureService;
import com.ssm.demo.utils.PageResult;
import com.ssm.demo.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Object getPicturePage(PageUtil pu) {
        List<Picture> pictures = pictureDao.findPictures(pu);
        int totalPicturesNum = pictureDao.getTotalPictures(pu);
        PageResult pageResult = new PageResult(totalPicturesNum, pu.getPage(), pictures, pu.getLimit());
        return pageResult;
    }

    @Override
    public Picture findOnePicture(Integer id) {
        return pictureDao.findPictureById(id);
    }
}
