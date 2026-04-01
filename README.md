# Java Data Structures

![Java](https://img.shields.io/badge/Java-17+-orange)
![Status](https://img.shields.io/badge/Status-In%20Progress-yellow)

This repository contains implementations of fundamental data structures written in Java.

The goal of this project is to understand how core data structures work internally by implementing them from scratch without relying on Java's built-in collections.

---

## Getting Started

**Requirements:** Java 17+

Clone the repository and open it in IntelliJ IDEA as a new project.  
No build tool required — compile and run directly.

---

## Project Structure
```
src/
├── main/
│   └── java/
│       └── datastructures/
│           ├── dynamicarray/
│           │   ├── DynamicArray.java
│           │   └── DynamicArrayDemo.java
│           ├── list/
│           │   ├── DoublyLinkedList.java
│           │   └── DoublyLinkedListDemo.java
│           ├── queue/
│           │   ├── CircularArrayQueue.java
│           │   ├── CircularArrayQueueDemo.java
│           │   ├── LinkedListQueue.java
│           │   └── LinkedListQueueDemo.java
│           ├── stack/
│           │   ├── ArrayStack.java
│           │   └── ArrayStackDemo.java
│           ├── binarysearchtree/
│           │   ├── BinarySearchTree.java
│           │   └── BSTDemo.java 
│           ├── redblacktree/
│           │   ├── RedBlackTree.java
│           │   └── RBTreeDemo.java 
│           └── avltree/        (planned)
│
└── test/     (planned)                
```

---

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
`src/main/java/datastructures/stack/ArrayStack.java`

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

### Queue (Circular Array)
Generic queue implementation using a circular array with dynamic resizing.  
The structure follows FIFO (First-In-First-Out) order and uses modular indexing to reuse array space efficiently.

Operations:
- enqueue
- dequeue
- peek
- size
- isEmpty

Time Complexity:

| Operation | Complexity     |
|-----------|----------------|
| enqueue   | O(1) amortized |
| dequeue   | O(1) amortized |
| peek      | O(1)           |
| size      | O(1)           |
| isEmpty   | O(1)           |

Location:  
`src/main/java/datastructures/queue/CircularArrayQueue.java`

---
### Queue (LinkedList)
Generic queue implementation using a singly LinkedList.  
The structure follows FIFO (First-In-First-Out) order.

Operations:
- enqueue
- dequeue
- peek
- size
- isEmpty

Time Complexity:

| Operation | Complexity |
|-----------|------------|
| enqueue   | O(1)       |
| dequeue   | O(1)       |
| peek      | O(1)       |
| size      | O(1)       |
| isEmpty   | O(1)       |

Location:  
`src/main/java/datastructures/queue/LinkedListQueue.java`

---
### Binary Search Tree
Generic Binary Search Tree (BST) implementation storing elements in sorted order based on their natural ordering.

This implementation does not self-balance, meaning performance depends on tree height.

Properties:
- Left subtree contains smaller elements
- Right subtree contains larger elements
- No duplicate values stored

Operations:
- insert
- delete
- contains
- findMin
- findMax
- height
- size
- isEmpty
- isValidBST
- inOrder (sorted traversal)
- preOrder
- postOrder
- levelOrder (BFS traversal)
- iterator (in-order)

Time Complexity:

| Operation | Average  | Worst Case |
|-----------|----------|------------|
| insert    | O(log n) | O(n)       |
| delete    | O(log n) | O(n)       |
| search    | O(log n) | O(n)       |
| traversal | O(n)     | O(n)       |

Notes:
- Worst-case occurs when the tree becomes unbalanced (e.g., inserting sorted data)
- For guaranteed O(log n), use self-balancing trees like AVL or Red-Black Trees

Location:  
`src/main/java/datastructures/binarysearchtree/BinarySearchTree.java`

---
### Red-Black Tree
Generic Red-Black Tree implementation — a self-balancing Binary Search Tree that guarantees logarithmic height.

This structure maintains balance through coloring rules and rotations after insertion.

Properties:
- Each node is either red or black
- The root is always black
- No two consecutive red nodes
- Every path from a node to its leaves has the same number of black nodes
- Ensures height remains O(log n)

Operations:
- insert (with rebalancing)
- contains
- inOrder (sorted traversal)
- size
- isValidRedBlackTree
- iterator (in-order)

Time Complexity:

| Operation  | Complexity |
|------------|------------|
| insert     | O(log n)   |
| search     | O(log n)   |
| traversal  | O(n)       |
| validation | O(n)       |

Notes:
- Balancing is achieved using left and right rotations combined with recoloring
- Guarantees better worst-case performance compared to a standard Binary Search Tree
- Deletion is not implemented in this version (planned)

Location:  
`src/main/java/datastructures/redblacktree/RedBlackTree.java`

---
## Example Usage
```java
ArrayStack<Integer> stack = new ArrayStack<>();

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

```java
CircularArrayQueue<Integer> queue = new CircularArrayQueue<>();

queue.enqueue(10);
queue.enqueue(20);
queue.enqueue(30);

System.out.println(queue.dequeue()); // 10
System.out.println(queue.peek());    // 20
System.out.println(queue.size());    // 2
```

```java
LinkedListQueue<Integer> queue = new LinkedListQueue<>();

queue.enqueue(10);
queue.enqueue(20);
queue.enqueue(30);

System.out.println(queue.dequeue()); // 10
System.out.println(queue.peek());    // 20
System.out.println(queue.size());    // 2
```

```java
BinarySearchTree<Integer> bst = new BinarySearchTree<>();

bst.insert(50);
bst.insert(30);
bst.insert(70);
bst.insert(20);
bst.insert(40);

System.out.println(bst.inOrder());   // [20, 30, 40, 50, 70]
System.out.println(bst.contains(40)); // true

bst.delete(30);

System.out.println(bst.inOrder());   // [20, 40, 50, 70]
System.out.println(bst.findMin());   // 20
System.out.println(bst.findMax());   // 70
```

```java
RedBlackTree<Integer> tree = new RedBlackTree<>();

tree.insert(10);
tree.insert(20);
tree.insert(30);
tree.insert(15);

System.out.println(tree.inOrder()); // [10, 15, 20, 30]
System.out.println(tree.contains(15)); // true
System.out.println(tree.size()); // 4
System.out.println(tree.isValidRedBlackTree()); // true
```
---
## Planned Implementations

The following data structures will be added progressively:

- AVL Tree