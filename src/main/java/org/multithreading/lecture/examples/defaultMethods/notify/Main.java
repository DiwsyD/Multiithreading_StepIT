package org.multithreading.lecture.examples.defaultMethods.notify;

public class Main {
    public static void main(String[] args) {
        Message message = new Message();

        // Потік, що встановлює повідомлення
        new Thread(() -> {
            message.setMessage("Hello from Thread 1");
        }).start();

        // Потік, що отримує повідомлення
        new Thread(() -> {
            System.out.println("Received message: " + message.getMessage());
        }).start();
    }
}
