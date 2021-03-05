package com.meeting.site.meetingSite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class Job {

    private int counter = 0;
    @Autowired
    private LRUCache cache;

    @Async("threadPoolTaskExecutor")
    public void acyncJob(UUID uuid) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:9090/check", String.class);
        System.out.println("response -> " + forEntity.getBody());
    }

}
