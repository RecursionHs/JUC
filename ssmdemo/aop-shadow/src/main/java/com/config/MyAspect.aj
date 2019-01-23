package com.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {

    @Pointcut("execution(* com.dao.*.*(..))")
    public void pointCut(){

    }
    @Before("pointCut()")
    public void before(){
        System.out.println("advice before");
    }
}
