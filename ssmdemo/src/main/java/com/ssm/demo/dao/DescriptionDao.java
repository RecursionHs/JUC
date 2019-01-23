package com.ssm.demo.dao;

import com.ssm.demo.entity.Description;

public interface DescriptionDao {

    /**
     * 获取最新的一条描述
     */
    Description getLastDescription();


}
