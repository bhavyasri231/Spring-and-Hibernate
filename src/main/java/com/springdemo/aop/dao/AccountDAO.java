package com.springdemo.aop.dao;

import com.springdemo.aop.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDAO {

    private String name;
    private String serviceCode;

    public void addAccount(Account theAccount, boolean vipFlag)
    {
        System.out.println(getClass() + ":DOING MY DB WORK: ADDING AN ACCOUNT");
    }

    public boolean doWork()
    {
        System.out.println(getClass() + ": doWork()");
        return false;

    }

    public String getName() {

        System.out.println(getClass() + ": In getName()");
        return name;
    }

    public void setName(String name) {

        System.out.println(getClass() + ": In setName()");
        this.name = name;
    }

    public String getServiceCode() {

        System.out.println(getClass() + ": In getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {

        System.out.println(getClass() + ": In setServiceCode()");
        this.serviceCode = serviceCode;
    }
}
