package com.springdemo.aop.aspect;


import com.springdemo.aop.Account;
import com.springdemo.aop.AroundWithLoggerDemoApp;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    private static Logger myLogger = Logger.getLogger(AroundWithLoggerDemoApp.class.getName());

    @Around("execution(* com.springdemo.aop.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable{

        String method = theProceedingJoinPoint.getSignature().toShortString();
        myLogger.info("\n=====>>> Executing @Around on method: "+method);

        long begin = System.currentTimeMillis();

        Object result = null;

        /*try {
            result = theProceedingJoinPoint.proceed();
        }
        catch (Exception ex)
        {
            myLogger.warning(ex.getMessage());

            result = "Major accident! But no worries, your private AOP helicopter is on the way!";
        }*/

        try {
            result = theProceedingJoinPoint.proceed();
        }
        catch (Exception ex)
        {
            myLogger.warning(ex.getMessage());

            throw ex;
        }

        long end = System.currentTimeMillis();

        long duration = end-begin;

        myLogger.info("\n=====>>> Duration: "+duration/1000.0 +" seconds");

        return result;
    }

    @After("execution(* com.springdemo.aop.dao.AccountDAO.findAccounts(..))")
    public void afterFinallydvice(JoinPoint theJointPoint)
    {
        String method = theJointPoint.getSignature().toShortString();
        myLogger.info("\n=====>>> Executing @After (finally) on method: "+method);

    }

    @AfterThrowing(pointcut = "execution(* com.springdemo.aop.dao.AccountDAO.findAccounts(..))"
            , throwing= "theExc")
    public void afterThrowingFindAccountAdvice(JoinPoint theJointPoint, Throwable theExc)
    {
        String method = theJointPoint.getSignature().toShortString();
        myLogger.info("\n=====>>> Executing @AfterThrowing on method: "+method);

        myLogger.info("\n=====>>> The exception is: "+theExc);

    }


    @AfterReturning(pointcut = "execution(* com.springdemo.aop.dao.AccountDAO.findAccounts(..))"
            ,returning = "result")
    public void afterReturningFindAccountAdvice(JoinPoint theJointPoint, List<Account> result)
    {
        String method = theJointPoint.getSignature().toShortString();
        myLogger.info("\n=====>>> Executing @AfterReturning on method: "+method);

        myLogger.info("\n=====>>> result is: "+result);

        convertAccountNamesToUpperCase(result);

        myLogger.info("\n=====>>> result is: "+result);
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
        myLogger.info("\n=====>>> Executing @Before advice on method");

        MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();

        Object[] args = theJoinPoint.getArgs();

        for(Object temparg : args)
        {
            myLogger.info(temparg.toString());

            if (temparg instanceof Account)
            {
                Account theAccount = (Account) temparg;

                myLogger.info("Account name: "+theAccount.getName());
                myLogger.info("Account level: "+theAccount.getLevel());
            }
        }

        myLogger.info("Method: " + methodSig);
    }




}
