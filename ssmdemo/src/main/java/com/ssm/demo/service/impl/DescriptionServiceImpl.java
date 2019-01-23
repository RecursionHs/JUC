package com.ssm.demo.service.impl;

import com.ssm.demo.dao.DescriptionDao;
import com.ssm.demo.entity.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssm.demo.service.DescriptionService;

@Service("descriptionService")
public class DescriptionServiceImpl implements DescriptionService {

    @Autowired
    private DescriptionDao descriptionDao;

    public Description getLastDescription() {
        return descriptionDao.getLastDescription();
    }
}
