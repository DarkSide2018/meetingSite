package com.meeting.site.meetingSite.service;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.googlecode.concurrentlinkedhashmap.EvictionListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LRUCache {

    private int counter = 0;

    private ConcurrentLinkedHashMap<Integer, UUID> linkedHashMap = new ConcurrentLinkedHashMap.Builder<Integer, UUID>()
            .listener(new EvictionListener<Integer, UUID>() {
                @Override
                public void onEviction(Integer key, UUID value) {
                    System.out.println("eviction key -> " + key + "value -> " + value);
                }
            })
            .maximumWeightedCapacity(10)
            .build();

    public void putElement(UUID value) {
        linkedHashMap.put(counter++, value);
        int size = linkedHashMap.size();

        System.out.println("cache size ->  " + size);
    }
}
