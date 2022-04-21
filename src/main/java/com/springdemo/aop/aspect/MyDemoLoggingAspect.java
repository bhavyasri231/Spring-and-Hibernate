package com.springdemo.aop.aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

   // @Before("execution(public void add*())")

    @Pointcut("execution(* com.springdemo.aop.dao.*.*(..))")
    private void forDaoPackage(){}

    @Pointcut("execution(* com.springdemo.aop.dao.*.get*(..))")
    private void getter(){}

    @Pointcut("execution(* com.springdemo.aop.dao.*.set*(..))")
    private void setter(){}

    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoPackageNotGetterSetter() {}


    @Before("forDaoPackageNotGetterSetter()")
    public void beforeAddAccountAdvice()
    {
        System.out.println("\n=====>>> Executing @Before advice on method");
    }

    @Before("forDaoPackageNotGetterSetter()")
    public void performApiAnalytics()
    {
        System.out.println("\n=====>>> Performing API Analytics");

    }
}
