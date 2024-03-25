package org.multithreading.lecture.examples.defaultMethods.notifyAll;

public class Main {
    public static void main(String[] args) {
        Worker worker = new Worker();

        // Створення багатьох потоків, які чекають на завершення завдання
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    worker.waitForTaskCompletion();
                    System.out.println("Task completed!");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }

        // Потік, що виконує завдання
        new Thread(() -> {
            worker.doTask();
        }).start();
    }
}