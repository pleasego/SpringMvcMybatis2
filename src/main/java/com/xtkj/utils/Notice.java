package com.xtkj.utils;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Log4j
//@Slf4j
class Notice {

    @Before("execution(* com.xtkj.service.impl.*.*(..))")
    public void before() {
        System.out.println("--------------><---------------");
        log.info(".................please away.............................");
        log.info("---------->前置通知<--------------");
    }

    @After("execution(* com.xtkj.service.impl.*.*(..))")
    public void after() {
        System.out.println("--------------><---------------");
        log.info(".................please like.............................");
        log.info("----------->后置通知<--------------");
    }

    @AfterThrowing(pointcut = "execution(* com.xtkj.service.impl.*.*(..))",throwing = "e")
    public void throwing(Exception e){
        log.debug(e.getMessage(),e);
    }


}
