package org.multithreading.lecture.examples.defaultMethods.notifyAll;

public class Worker {
    private boolean isTaskComplete = false;

    public synchronized void doTask() {
        // виконання дій
        isTaskComplete = true;
        notifyAll(); // будимо всі потоки, що чекають на завершення завдання
    }

    public synchronized void waitForTaskCompletion() throws InterruptedException {
        while (!isTaskComplete) {
            wait(); // чекаємо, поки завдання не буде завершено
        }
        // дії після завершення завдання
    }
}
