package com.springdemo.aop;


import com.springdemo.aop.dao.AccountDAO;
import com.springdemo.aop.dao.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAO theAccountDAO = context.getBean("accountDAO",AccountDAO.class);

        MembershipDAO theMembershipDAO = context.getBean("membershipDAO",MembershipDAO.class);

        Account myAccount = new Account();
        theAccountDAO.addAccount(myAccount,true);
        theAccountDAO.doWork();

        theMembershipDAO.addSillyMember();
        theMembershipDAO.goToSleep();

        context.close();
    }
}
