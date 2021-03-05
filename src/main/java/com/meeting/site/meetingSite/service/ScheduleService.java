package com.meeting.site.meetingSite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class ScheduleService {
    @Autowired
    private GateWayTask gateWay;

    @Autowired
    @Qualifier("threadPoolTaskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private Job job;

    @Scheduled(fixedRate = 1000)
    public void schedule(){
         int threadCount = 0;
        System.out.println("poolSize - > " + taskExecutor.getPoolSize());
        System.out.println("activeCount - > " + taskExecutor.getActiveCount());
        System.out.println("queueSize - > " + taskExecutor.getThreadPoolExecutor().getQueue().size());
        gateWay.setUuid(UUID.randomUUID());
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        for ( Thread t : threadSet){
            System.out.println("Thread :"+t+":"+"state:"+t.getState());
                ++threadCount;
        }
        System.out.println("Thread count started by Main thread:"+threadCount);
        taskExecutor.execute(gateWay);
        job.acyncJob(UUID.randomUUID());
    }
}
