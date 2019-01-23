package com.test;

import com.config.Appconfig;
import com.dao.IndexDao;
import com.dao.IndexDaoImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Appconfig.class);
        IndexDaoImpl dao = (IndexDaoImpl) annotationConfigApplicationContext.getBean(IndexDao.class);
        dao.Query();
    }
}
