package datastructures.queue;

public class CircularArrayQueueDemo {

    public static void main(String[] args) {

        CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(3);

        System.out.println("Initial queue: " + queue);

        // enqueue elements
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        System.out.println("After enqueue 10, 20, 30: " + queue);

        // dequeue one element
        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("Queue now: " + queue);

        // enqueue more elements (tests circular behavior)
        queue.enqueue(40);
        queue.enqueue(50);

        System.out.println("After enqueue 40, 50: " + queue);

        // peek
        System.out.println("Front element (peek): " + queue.peek());

        // remove everything
        while (!queue.isEmpty()) {
            System.out.println("Dequeued: " + queue.dequeue() + " | Queue: " + queue);
        }

        System.out.println("Queue empty: " + queue.isEmpty());
    }
}
