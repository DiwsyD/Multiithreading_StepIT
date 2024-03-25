package org.multithreading.lecture.examples;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomic_Example {

    public static int value = 0;
    public static AtomicInteger atomic = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                value++;
                atomic.incrementAndGet();
            }
        };

        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();

        Thread.sleep(300);
        System.out.println(value);
        System.out.println(atomic.get());
    }
}