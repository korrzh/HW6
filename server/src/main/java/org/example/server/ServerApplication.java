package org.example.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableScheduling
public class ServerApplication implements CommandLineRunner {

    private volatile int counter = 0;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        startCounterTask();
    }

    private void startCounterTask() {
        executorService.scheduleAtFixedRate(() -> {
            if (counter < 10) {
                counter++;
                System.out.println("Counter: " + counter);
            } else {
                System.out.println("Counter reached 10. Stopping task...");
                executorService.shutdown();
            }
        }, 0, 2, TimeUnit.SECONDS);
    }

    @Scheduled(fixedRate = 5000)
    public void printCurrentTime() {
        if (!executorService.isShutdown()) {
            System.out.println("Current time: " + LocalTime.now());
        }
    }
}

