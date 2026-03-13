# Java Data Structures

This repository contains implementations of fundamental data structures written in Java.

The goal of this project is to understand how core data structures work internally by implementing them from scratch without relying on Java's built-in collections.

## Implemented Data Structures

### Stack
Array-based stack implementation with dynamic resizing.

Operations:
- push
- pop
- peek
- size
- isEmpty

Time Complexity:

| Operation | Complexity     |
|-----------|----------------|
| push      | O(1) amortized |
| pop       | O(1) amortized |
| peek      | O(1)           |
| size      | O(1)           |

Location:
`src/main/java/datastructures/stack/StackAsArray.java`

---

### Dynamic Array
Generic dynamic array implementation with automatic resizing, fail-fast iteration, and common array operations.

Operations:
- add (append)
- add (at index)
- get
- set
- remove
- contains
- indexOf
- size
- isEmpty
- clear

Time Complexity:

| Operation     | Complexity     |
|---------------|----------------|
| add (end)     | O(1) amortized |
| add (index)   | O(n)           |
| get           | O(1)           |
| set           | O(1)           |
| remove        | O(n)           |
| contains      | O(n)           |
| indexOf       | O(n)           |
| size          | O(1)           |

Location:
`src/main/java/datastructures/dynamicarray/DynamicArray.java`

---

### Doubly Linked List
Generic doubly linked list implementation with head and tail references, bidirectional traversal, and a fail-fast iterator.

Operations:
- addFirst
- addLast
- add (at index)
- removeFirst
- removeLast
- remove (at index)
- get
- set
- contains
- indexOf
- reverse
- clear
- size
- isEmpty
- iterator

Time Complexity:

| Operation      | Complexity     |
|----------------|----------------|
| addFirst       | O(1)           |
| addLast        | O(1)           |
| add (index)    | O(n)           |
| removeFirst    | O(1)           |
| removeLast     | O(1)           |
| remove (index) | O(n)           |
| get (index)    | O(n)           |
| set (index)    | O(n)           |
| contains       | O(n)           |
| indexOf        | O(n)           |
| reverse        | O(n)           |
| size           | O(1)           |
| isEmpty        | O(1)           |

Location:  
`src/main/java/datastructures/list/DoublyLinkedList.java`

---

## Example Usage
```java
StackAsArray<Integer> stack = new StackAsArray<>();

stack.push(10);
stack.push(20);

System.out.println(stack.pop());  // 20
System.out.println(stack.peek()); // 10
System.out.println(stack.size()); // 1
```

```java
DynamicArray<String> arr = new DynamicArray<>();

arr.add("Alice");
arr.add("Bob");
arr.add(1, "Charlie");

System.out.println(arr.get(0));      // Alice
System.out.println(arr.get(1));      // Charlie
System.out.println(arr.contains("Bob")); // true
System.out.println(arr.size());      // 3
```

```java
DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

list.addFirst(3);
list.addFirst(1);
list.addLast(5);
list.add(1, 2); // insert 2 at index 1
list.add(3, 4); // insert 4 at index 3

System.out.println(list.get(2));      // 3
System.out.println(list.contains(4)); // true

list.removeFirst();
list.removeLast();

list.reverse();

for (Integer val : list) {
    System.out.print(val + " ");
}
// Output: 4 3 2
```
---

## Planned Implementations

The following data structures will be added progressively:

- Queue
- Binary Search Tree
- AVL Tree
- Red-Black Tree