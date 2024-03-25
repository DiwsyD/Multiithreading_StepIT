package org.multithreading.lecture;

public class App {

    private final String line = "asd";

    public static void main(String[] args) throws InterruptedException {

        Object obj = new Object();
        obj.wait();

        App app = new App();

        Thread th1 = new Thread(() -> {
            try {
                app.someMethod1();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread th2 = new Thread(() -> {
            try {
                app.someMethod2();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        th1.start();
        th2.start();
    }

    public void someMethod1() throws InterruptedException {
        synchronized (line) {
            System.out.println("Action0");
            line.wait();
            System.out.println("Action2");
        }
    }

    public void someMethod2() throws InterruptedException {
        synchronized (line) {
            System.out.println("Action1");
            line.notify();
            System.out.println("Action3");
        }
    }

}
