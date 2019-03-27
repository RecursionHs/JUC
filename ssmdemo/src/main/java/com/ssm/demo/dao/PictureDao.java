package com.ssm.demo.dao;

import com.ssm.demo.entity.Picture;
import com.ssm.demo.utils.PageUtil;

import java.util.List;

/**
 * @Author hsir
 * @Date 2019/3/15/015 16:22
 * @Version 1.0
 **/
public interface PictureDao {


    int save(Picture picture);

    List<Picture> findPictures(PageUtil pu);

    int getTotalPictures(PageUtil pu);

    Picture findPictureById(Integer id);

    int updatePicture(Picture picture);

    int deleteBatch(Integer[] ids);
}
