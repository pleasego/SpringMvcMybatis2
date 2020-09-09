package com.xtkj.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class Notice {
    @Before("execution(* com.xtkj.service.impl.*.*(..))")
    public void before() {
        log.debug("--------log4j日志--------------");
    }


}
