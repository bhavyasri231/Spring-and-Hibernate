package com.springdemo.aop;


import com.springdemo.aop.dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;


public class AfterReturningDemoApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAO theAccountDAO = context.getBean("accountDAO",AccountDAO.class);

        List<Account> theAccounts = theAccountDAO.findAccounts(false);

        System.out.println("\n\nMain program: AfterReturningDemoApp");
        System.out.println("-----");

        System.out.println(theAccounts);
         

        context.close();
    }
}
