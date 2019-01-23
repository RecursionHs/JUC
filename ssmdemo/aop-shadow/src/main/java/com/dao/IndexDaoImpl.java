package com.dao;

import org.springframework.stereotype.Component;

@Component
public class IndexDaoImpl implements IndexDao {
    @Override
    public void Query() {
        System.out.println("dao---Query");
    }
}
