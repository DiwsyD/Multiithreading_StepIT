package org.multithreading.lecture;

public class MyCustomThread extends Thread {

    @Override
    public void run() {

        System.out.println("before " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("after " + Thread.currentThread().getName());
    }

}
