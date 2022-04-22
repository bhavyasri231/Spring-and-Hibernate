package com.springdemo.aop;


import com.springdemo.aop.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;


public class AroundHandleExceptionDemoApp {

    public static void main(String[] args) {

        Logger myLogger = Logger.getLogger(AroundHandleExceptionDemoApp.class.getName());

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        TrafficFortuneService theFortuneService  =
                context.getBean("trafficFortuneService",TrafficFortuneService.class);

        myLogger.info("\nMain Program: AroundDemoApp");

        myLogger.info("Calling getFortune");

        boolean tripWire = true;
        String data = theFortuneService.getFortune(tripWire);

        myLogger.info("\nMy fortune is: "+data);

        myLogger.info("Finished");
        context.close();
    }
}
