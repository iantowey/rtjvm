package com.demo.java;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;


public class VirtualThreadDemo {
    static final Logger logger = LoggerFactory.getLogger(VirtualThreadDemo.class);

    static void log(String message) {
        logger.info("{} | " + message, Thread.currentThread());
    }

    @SneakyThrows
    private static void sleep(Duration duration) {
        Thread.sleep(duration);
    }

    private static Thread virtualThread(String name, Runnable runnable) {
        return Thread.ofVirtual()
                .name(name)
                .start(runnable);
    }

    static Thread bathTime() {
        return virtualThread(
                "Bath time",
                () -> {
                    log("I'm going to take a bath");
                    sleep(Duration.ofMillis(500L));
                    log("I'm done with the bath");
                });
    }

    static Thread boilingWater() {
        return virtualThread(
                "Boil some water",
                () -> {
                    log("I'm going to boil some water");
                    sleep(Duration.ofSeconds(1L));
                    log("I'm done with the water");
                });
    }

    @SneakyThrows
    static void concurrentMorningRoutine() {
        var bathTime = bathTime();
        var boilingWater = boilingWater();
        bathTime.join();
        boilingWater.join();
    }

    public static void main(String[] args){
        concurrentMorningRoutine();
        System.out.println("Done");
    }
}
