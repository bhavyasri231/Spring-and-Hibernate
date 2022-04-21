package com.springdemo.aop.aspect;


import com.springdemo.aop.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {


    @Before("com.springdemo.aop.aspect.AopExpressions.forDaoPackageNotGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint)
    {
        System.out.println("\n=====>>> Executing @Before advice on method");

        MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();

        Object[] args = theJoinPoint.getArgs();

        for(Object temparg : args)
        {
            System.out.println(temparg);

            if (temparg instanceof Account)
            {
                Account theAccount = (Account) temparg;

                System.out.println("Account name: "+theAccount.getName());
                System.out.println("Account level: "+theAccount.getLevel());
            }
        }

        System.out.println("Method: " + methodSig);
    }




}
