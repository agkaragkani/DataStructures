# Data Structures: Comparative Analysis of Search Trees

**Student:** Gkaragkani Alexandra (AM: 2019030020) 
**Course:** Data Structures
**Exercise:** Assignment 2 

## 📌 Project Overview
This project implements and compares three different data structures to analyze their efficiency in terms of insertions, random searches, and range searches. The goal is to measure the average number of comparisons required for these operations across different implementations.

The data structures examined are:
1.  **Binary Search Tree (Array Implementation)**
2.  **Threaded Binary Search Tree** 
3.  **Sorted Array (Binary Search)** 

---

## 🏗️ Data Structures & Implementation

### 1. Array-Based Binary Search Tree (`ArrayBST`)
A BST implemented using an array where pointers are simulated by array indices.

* **`public ArrayBST(int N)`**: Constructor initializing the tree with size `N`.
* **`insert(int key)`**: Inserts a key into the tree by finding the correct position starting from the root.
    * *Logic:* If the array is empty, the key becomes the root. Otherwise, it traverses the tree to find the correct spot and places the key in the next free position provided by the stack.
* **`find(int root, int key)`**: Searches for a key. If the key is not at the root, it traverses the tree.
* **`printRange(int root, int low, int high)`**: Traverses the tree recursively to find and optionally print keys within the specified `[low, high]` range.

### 2. Threaded Binary Search Tree (`ThreadedBST`)
An optimized BST that uses "threads" (boolean fields in the array) to allow faster in-order traversal without recursion.

* **`insert(int key)`**: Similar to the standard BST but utilizes boolean fields during comparisons for faster processing.
* **`findNext(int current)` / `findPrev(int current)`**: Helper methods that use the threaded structure to find the in-order successor or predecessor.
* **`printRange(int root, int low, int high)`**: Efficiently traverses the tree using `findNext` and `findPrev`, comparing keys against the range bounds.

### 3. Sorted Array (`BinarySearch`)
A simple sorted array implementation utilizing standard binary search algorithms.

* **`binarySearch(int left, int right, int key)`**: Standard binary search implementation. Returns `Integer.MIN_VALUE` if the key is not found.
* **`binaryRange(int left, int right, int min, int max)`**: Finds all keys within a specific range using a logic similar to binary search.

---

## 📊 Performance Analysis

The project runs specific experiments to measure "Average Comparisons" for different operations.

### Key Experiments
1.  **Insert Info:** Inserts 100,000 random integers and calculates average comparisons per insertion.
2.  **Search Random:** Performs 100 searches for random keys.
3.  **Range Search:** Performs searches for random ranges of size $K=100$ and $K=1000$ (and extended tests for $K=1$ and $K=100,000$).

### Experimental Results Table
| Method | Avg Comparisons / Insertion | Avg Comparisons / Random Search | Avg Comparisons / Range Search (K=100) | Avg Comparisons / Range Search (K=1000) |
| :--- | :---: | :---: | :---: | :---: |
| **BST (Array)** | 122 | 168 | 205 | 935 |
| **Threaded BST** | 129 | 177 | 304 | 896 |
| **Sorted Array** | - | 77 | 54 | 142 |


---

## 💡 Conclusions
* **Insertions & Random Search:** There is no significant difference in performance between the standard BST and the Threaded BST for insertions and single-key searches.
* **Range Search (Small Range):** For smaller ranges (e.g., 100), the Threaded BST required *more* comparisons than the standard BST.
* **Range Search (Large Range):** The Threaded BST becomes significantly more efficient as the range size increases.
    * For a range of 1,000, the Threaded BST had ~50 fewer comparisons.
    * For a massive range of 100,000, the Threaded BST had ~10,000 fewer comparisons compared to the standard BST.
* **Overall:** The Threaded Binary Search Tree is more advantageous for large range searches as it reduces disk accesses/comparisons significantly.

---

## 🔗 References
* [Threaded Binary Tree Insertion - GeeksforGeeks](https://www.geeksforgeeks.org/threaded-binary-tree-insertion/) 
* [Print BST keys in the given range - GeeksforGeeks](https://www.geeksforgeeks.org/print-bst-keys-in-given-range-01-space/) 