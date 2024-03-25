package org.multithreading.lecture.examples;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {

    static class FactorialTask implements Callable<Long> {
        private final int number;

        public FactorialTask(int number) {
            this.number = number;
        }

        @Override
        public Long call() {
            System.out.println("5");
            long factorial = 1;
            for (int i = 1; i <= number; i++) {
                factorial *= i;
            }
            System.out.println("6");
            return factorial;
        }
    }

    public static void main(String[] args) {
        int number = 5;

        ExecutorService executor = Executors.newFixedThreadPool(1);

        FactorialTask task = new FactorialTask(number);

        Future<Long> future = executor.submit(task);

        System.out.println("1");

        executor.shutdown();

        System.out.println("2");
        try {
            System.out.println("3");
            long factorialResult = future.get();
            System.out.println("4");
            System.out.println("Factorial of " + number + " is: " + factorialResult);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
