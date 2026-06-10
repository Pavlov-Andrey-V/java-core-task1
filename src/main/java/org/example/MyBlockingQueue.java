package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class MyBlockingQueue<T> {
    private final Queue<T> queue;
    private final int size;

    public MyBlockingQueue(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size queue must be over zero");
        }
        this.queue = new LinkedList<>();
        this.size = size;
    }

    public synchronized void enqueue(T element) throws InterruptedException {
        while (queue.size() == size) {
            wait();
        }
        queue.offer(element);
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T element = queue.poll();
        notifyAll();
        return element;
    }

    public synchronized int size() {
        return queue.size();
    }
}
