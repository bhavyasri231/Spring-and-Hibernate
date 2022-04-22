package com.springdemo.aop.aspect;


import com.springdemo.aop.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Around("execution(* com.springdemo.aop.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable{

        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @Around on method: "+method);

        long begin = System.currentTimeMillis();

        Object result = theProceedingJoinPoint.proceed();

        long end = System.currentTimeMillis();

        long duration = end-begin;

        System.out.println("\n=====>>> Duration: "+duration/1000.0 +" seconds");

        return result;
    }

    @After("execution(* com.springdemo.aop.dao.AccountDAO.findAccounts(..))")
    public void afterFinallydvice(JoinPoint theJointPoint)
    {
        String method = theJointPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @After (finally) on method: "+method);

    }

    @AfterThrowing(pointcut = "execution(* com.springdemo.aop.dao.AccountDAO.findAccounts(..))"
            , throwing= "theExc")
    public void afterThrowingFindAccountAdvice(JoinPoint theJointPoint, Throwable theExc)
    {
        String method = theJointPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method: "+method);

        System.out.println("\n=====>>> The exception is: "+theExc);

    }


    @AfterReturning(pointcut = "execution(* com.springdemo.aop.dao.AccountDAO.findAccounts(..))"
            ,returning = "result")
    public void afterReturningFindAccountAdvice(JoinPoint theJointPoint, List<Account> result)
    {
        String method = theJointPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: "+method);

        System.out.println("\n=====>>> result is: "+result);

        convertAccountNamesToUpperCase(result);

        System.out.println("\n=====>>> result is: "+result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {

        for (Account tempAccount : result)
        {
            String theUpperName = tempAccount.getName().toUpperCase();
            tempAccount.setName(theUpperName);


        }
    }


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
