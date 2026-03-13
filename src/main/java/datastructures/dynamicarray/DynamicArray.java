package datastructures.dynamicarray;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * A generic dynamic array implementation in Java.
 * Supports automatic resizing, fail-fast iteration, and common array operations.
 *
 * @param <T> the type of elements stored in this array
 */
public class DynamicArray<T> implements Iterable<T> {

    private T[] array;
    private int capacity;
    private int size;
    private int modCount;

    /**
     * Constructs a DynamicArray with the specified initial capacity.
     *
     * @param capacity initial capacity of the array, must be positive
     * @throws IllegalArgumentException if capacity < 1
     */
    @SuppressWarnings("unchecked")
    public DynamicArray(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.array = (T[]) new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
        this.modCount = 0;
    }

    /**
     * Constructs a DynamicArray with default initial capacity of 10.
     */
    public DynamicArray() {
        this(10);
    }

    /**
     * Adds an element to the end of the dynamic array.
     * Automatically resizes if the array is full.
     *
     * @param element the element to add
     */
    public void add(T element) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        array[size++] = element;
        modCount++;
    }

    /**
     * Inserts an element at the specified index.
     * Shifts subsequent elements to the right.
     *
     * @param index the position to insert the element (0 ≤ index ≤ size)
     * @param element the element to insert
     * @throws IndexOutOfBoundsException if index < 0 or index > size
     */
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        if (index == size) {
            add(element);
            return;
        }

        if (size == capacity) {
            resize(capacity * 2);
        }

        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
        array[index] = element;
        size++;
        modCount++;
    }

    /**
     * Returns the element at the specified index.
     *
     * @param index index of the element to return
     * @return the element at the given index
     * @throws IndexOutOfBoundsException if index < 0 or index ≥ size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return array[index];
    }

    /**
     * Replaces the element at the specified index with the given element.
     *
     * @param index index of the element to replace
     * @param element element to store
     * @throws IndexOutOfBoundsException if index < 0 or index ≥ size
     */
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        array[index] = element;
    }

    /**
     * Removes the element at the specified index.
     * Shifts subsequent elements to the left.
     * Shrinks the array if necessary.
     *
     * @param index index of the element to remove
     * @throws IndexOutOfBoundsException if index < 0 or index ≥ size
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[--size] = null;
        modCount++;

        if (capacity > 1 && size <= capacity / 4) {
            resize(capacity / 2);
        }
    }

    /**
     * Returns the number of elements currently stored in the array.
     *
     * @return the size of the array
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if the array contains no elements.
     *
     * @return true if size == 0, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes all elements from the array and resets the size to zero.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
        modCount++;
    }

    /**
     * Checks whether the array contains the specified element.
     *
     * @param element element to search for (can be null)
     * @return true if the element is present, false otherwise
     */
    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the index of the first occurrence of the specified element,
     * or -1 if the element is not found.
     *
     * @param element element to search for (can be null)
     * @return index of element, or -1 if not found
     */
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the current capacity of the internal array.
     *
     * @return the capacity of the array
     */
    public int capacity() {
        return capacity;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        T[] array = (T[]) new Object[newCapacity];
        System.arraycopy(this.array, 0, array, 0, size);
        this.array = array;
        this.capacity = newCapacity;
    }

    /**
     * Returns an iterator over the elements in this array.
     * The iterator is fail-fast: it throws ConcurrentModificationException
     * if the array is modified after the iterator is created.
     *
     * @return an iterator over the elements
     */
    @Override
    public Iterator<T> iterator() {
        return new DynamicArrayIterator();
    }

    private class DynamicArrayIterator implements Iterator<T> {

        private int current = 0;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public T next() {
            checkForComodification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return array[current++];
        }

        private void checkForComodification() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}