package datastructures.queue;

import java.util.NoSuchElementException;

/**
 * A generic queue implementation using a circular array.
 * Elements are added at the rear and removed from the front.
 * The array grows and shrinks dynamically while maintaining FIFO order.
 */
public class CircularArrayQueue<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private T[] queue;
    private int front;
    private int rear;
    private int size;
    private final int initialCapacity;

    @SuppressWarnings("unchecked")
    public CircularArrayQueue(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be at least 1");
        }

        this.queue = (T[]) new Object[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
        this.initialCapacity = capacity;
    }

    public CircularArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    public void enqueue(T element) {
        if (size == queue.length) {
            resize(queue.length * 2);
        }

        queue[rear] = element;
        rear = (rear + 1) % queue.length;
        size++;
    }

    public T dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }

        T element = queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;
        size--;

        if (size > 0 && size <= queue.length / 4 && queue.length > initialCapacity) {
            int newCapacity = Math.max(queue.length / 2, initialCapacity);
            resize(newCapacity);
        }

        return element;
    }

    public T peek() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }

        return queue[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < size; i++) {
            sb.append(queue[(front + i) % queue.length]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        T[] newQueue = (T[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[(front + i) % queue.length];
        }

        queue = newQueue;
        front = 0;
        rear = size;
    }
}