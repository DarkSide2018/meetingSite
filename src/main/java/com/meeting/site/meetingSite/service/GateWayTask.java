package com.meeting.site.meetingSite.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class GateWayTask implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
            String name = Thread.currentThread().getName();

            System.out.println("hard work in -> "+name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
