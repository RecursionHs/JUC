package com.ssm.demo.service;

import com.ssm.demo.entity.Description;

public interface DescriptionService {
    /**
     * 获取最新一条描述
     */
    Description getLastDescription();
}
