package org.multithreading.lecture.examples;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Runnable task = () -> {
            try {
                Thread.sleep(1500);
                System.out.printf("Thread [%s] done!", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        executorService.submit(task);

        executorService.shutdown();


    }

}
