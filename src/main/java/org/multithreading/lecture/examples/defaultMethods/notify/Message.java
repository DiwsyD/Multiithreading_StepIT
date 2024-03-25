package org.multithreading.lecture.examples.defaultMethods.notify;

public class Message {
    private String content;
    private boolean isMessageReady = false;

    public synchronized void setMessage(String message) {
        while (isMessageReady) {
            try {
                wait(); // чекаємо, поки не буде готове повідомлення
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.content = message;
        isMessageReady = true;
        notify(); // будимо потік, який чекає на повідомлення
    }

    public synchronized String getMessage() {
        while (!isMessageReady) {
            try {
                wait(); // чекаємо, поки не буде готове повідомлення
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        isMessageReady = false;
        notify(); // будимо інші потоки, які можуть чекати на повідомлення
        return content;
    }
}
