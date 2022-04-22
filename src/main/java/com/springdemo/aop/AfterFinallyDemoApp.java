package com.springdemo.aop;


import com.springdemo.aop.dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;


public class AfterFinallyDemoApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAO theAccountDAO = context.getBean("accountDAO",AccountDAO.class);

        List<Account> theAccounts = null;
        try
        {
            boolean tripWire = false;
            theAccounts = theAccountDAO.findAccounts(tripWire);
        }
        catch (Exception exc)
        {
            System.out.println("\n\nMain program... caught exception: "+exc);
        }

        System.out.println("\n\nMain program: AfterThrowingDemoApp");
        System.out.println("-----");

        System.out.println(theAccounts);
         

        context.close();
    }
}
