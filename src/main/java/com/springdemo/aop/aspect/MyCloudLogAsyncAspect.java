package com.springdemo.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class MyCloudLogAsyncAspect {

    @Before("com.springdemo.aop.aspect.AopExpressions.forDaoPackageNotGetterSetter()")
    public void logTOCloudAsync()
    {
        System.out.println("\n=====>>> Logging to the Cloud in async fashion");

    }
}
