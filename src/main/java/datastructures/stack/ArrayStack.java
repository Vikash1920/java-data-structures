package datastructures.stack;

/**
 * A generic stack implementation using a dynamically resizing array.
 *
 * The stack grows when full and shrinks when it becomes one quarter full.
 * This guarantees amortized O(1) time for push and pop operations.
 *
 * @param <T> the type of elements stored in the stack
 */
public class ArrayStack<T> {

    private T[] stack;
    private int top;
    private int capacity = 1;

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        stack = (T[]) new Object[capacity];
        top = -1;
    }

    /**
     * Returns true if the stack is empty.
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Returns the number of elements in the stack.
     */
    public int size() {
        return top + 1;
    }

    /**
     * Pushes an item onto the stack.
     */
    public void push(T item) {
        if (size() == capacity) {
            resize(capacity * 2);
        }
        stack[++top] = item;
    }

    /**
     * Removes and returns the top element of the stack.
     */
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        T item = stack[top];
        stack[top] = null;
        top--;

        if (capacity > 1 && size() <= capacity / 4) {
            resize(capacity / 2);
        }

        return item;
    }

    /**
     * Returns the top element without removing it.
     */
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[top];
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        T[] newStack = (T[]) new Object[newCapacity];
        System.arraycopy(stack, 0, newStack, 0, size());
        stack = newStack;
        capacity = newCapacity;
    }
}
