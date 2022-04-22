package com.springdemo.aop;


import com.springdemo.aop.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;


public class AroundWithLoggerDemoApp {

    public static void main(String[] args) {

        Logger myLogger = Logger.getLogger(AroundWithLoggerDemoApp.class.getName());

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        TrafficFortuneService theFortuneService  =
                context.getBean("trafficFortuneService",TrafficFortuneService.class);

        myLogger.info("\nMain Program: AroundDemoApp");

        myLogger.info("Calling getFortune");

        String data = theFortuneService.getFortune();

        myLogger.info("\nMy fortune is: "+data);

        myLogger.info("Finished");
        context.close();
    }
}
