package com.meeting.site.meetingSite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    @Autowired
    private GateWayTask gateWay;

    @Autowired
    @Qualifier("threadPoolTaskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;
    @Scheduled(fixedRate = 1000)
    public void schedule(){
        taskExecutor.execute(gateWay);
    }
}
