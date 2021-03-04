package com.meeting.site.meetingSite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Job {

    private int counter = 0;
    @Autowired
    private LRUCache cache;
    @Async("threadPoolTaskExecutor")
    public void acyncJob(UUID uuid){
        try {
            Thread.sleep(10000);
            String name = Thread.currentThread().getName();
            cache.putElement(uuid);
            System.out.println("Async HARD-WORK in -> " + name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
