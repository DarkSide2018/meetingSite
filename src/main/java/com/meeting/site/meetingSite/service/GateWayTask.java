package com.meeting.site.meetingSite.service;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope("prototype")
@Setter
public class GateWayTask implements Runnable{
    private UUID uuid;
    @Autowired
    private LRUCache cache;

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
            String name = Thread.currentThread().getName();
            cache.putElement(uuid);
            System.out.println("hard work in -> "+name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
