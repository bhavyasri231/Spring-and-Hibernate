package com.springdemo.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {

    @Pointcut("execution(* com.springdemo.aop.dao.*.*(..))")
    public void forDaoPackage(){}

    @Pointcut("execution(* com.springdemo.aop.dao.*.get*(..))")
    public void getter(){}

    @Pointcut("execution(* com.springdemo.aop.dao.*.set*(..))")
    public void setter(){}

    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNotGetterSetter() {}
}
