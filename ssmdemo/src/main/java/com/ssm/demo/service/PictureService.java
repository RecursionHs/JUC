package com.ssm.demo.service;

import com.ssm.demo.entity.Picture;
import com.ssm.demo.utils.PageUtil;

/**
 * @Author hsir
 * @Date 2019/3/15/015 16:22
 * @Version 1.0
 **/
public interface PictureService {

    int save(Picture picture);

    Object getPicturePage(PageUtil pu);

    Picture findOnePicture(Integer id);

    int updatePicture(Picture picture);

    int deleteBatch(Integer[] ids);
}
