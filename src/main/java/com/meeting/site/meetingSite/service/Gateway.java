package com.meeting.site.meetingSite.service;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class Gateway {
    private ExecutorService executor1 = Executors.newFixedThreadPool(5);
    private ExecutorService executor2 = Executors.newFixedThreadPool(2);
    private Random random = new Random();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Gateway().starting();

    }

    public void starting() throws ExecutionException, InterruptedException {
        long l = System.currentTimeMillis();
        ArrayList<String> strings = new ArrayList<>();
        strings.add("asas");
        strings.add("asas2222");
        strings.add("asas2223");
        strings.add("asas2224");
        strings.add("asas2225");
        strings.forEach(el -> {
            Runnable future = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("working with -> " + el);
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e);
                    }
                }
            };
            executor1.submit(future);
        });
        long currentTimeMillis = System.currentTimeMillis();
        executor1.shutdown();
        long res = currentTimeMillis - l;
        System.out.println("result -> " + res);
    }

}
