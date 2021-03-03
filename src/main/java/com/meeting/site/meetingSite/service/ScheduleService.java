package com.meeting.site.meetingSite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadPoolExecutor;

@Service
public class ScheduleService {
    @Autowired
    private GateWayTask gateWay;

    @Autowired
    @Qualifier("threadPoolTaskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;
    @Scheduled(fixedRate = 5000)
    public void schedule(){
        System.out.println("poolSize - > " + taskExecutor.getPoolSize());
        System.out.println("activeCount - > " + taskExecutor.getActiveCount());
        System.out.println("queueSize - > " + taskExecutor.getThreadPoolExecutor().getQueue().size());
        taskExecutor.execute(gateWay);
    }
}
