# Data Structure & Graph Performance Analyzer

**Module:** CIT300 Data Structures and Algorithms
**Assignment:** Graded Practical Assignment 2 (Week 12) - 10% of the final grade
**Language:** Java 17+ (tested on Java 21)
**Application type:** Console-based system, plus an optional JavaFX graphical version

---

## 1. Project Description

This project is a single integrated Java console application that demonstrates the practical
use of data structures, algorithms, searching, graph concepts and algorithmic complexity.

The user can work with an array, a stack, a queue, a singly linked list, a search module
(linear vs binary search), and an undirected graph with BFS and DFS traversal. A performance
comparison module reports the number of steps and execution time for each algorithm, and the
program explains how those results relate to Big-O complexity.

The application uses no external libraries for the console version. An optional JavaFX GUI
(`javafx/`) provides the same graph functionality in a visual interface.

---

## 2. Team Members

| # | Student Name | Student ID | Assigned Responsibility | Main files |
|---|--------------|------------|-------------------------|------------|
| 1 | A.F.Ruja | 23da2-0734 | Array and Searching implementation | `ArrayOperations.java`, `SearchOperations.java` |
| 2 | M.F.F.Aksha | 23da2-0741 | Stack and Queue implementation | `StackOperations.java`, `QueueOperations.java` |
| 3 | A.S Farwin | 23da2-0787 | Linked List implementation | `LinkedListOperations.java` |
| 4 | M.E.F.Asika | 23da2-1078 | Graph implementation and traversal | `Graph.java` |
| 5 | A.F.Ruja | 23da2-0734 | Performance comparison, main menu, integration and testing | `Main.java`, `PerformanceComparison.java`, `InputHelper.java`, `ResultLog.java` |

> **Note:** Member 5 has been entered as A.F.Ruja (23da2-0734), as provided. This matches Member 1's ID; verify the duplicate ID before submission.

---

## 3. Individual Contributions

### Member 1 - Array and Searching
- Student Name: A.F.Ruja | Student ID: 23da2-0734
- Implemented a fixed-capacity array with insert at end, insert at index, delete by index, delete by value, search and display.
- Implemented linear search (O(n), works on unsorted data) and binary search (O(log n), works on sorted data).
- Built the search menu, including a linear vs binary comparison that shows steps and time on the same key.
- Tested empty, full and invalid-index cases.

### Member 2 - Stack and Queue
- Student Name: M.F.F.Aksha | Student ID: 23da2-0741
- Implemented an array-based stack (LIFO) with push, pop, peek and display. The array grows automatically.
- Implemented a circular-array queue (FIFO) with enqueue, dequeue, peek/front and display. The array grows automatically.
- Handled underflow on pop, dequeue and peek with clear error messages.
- Tested empty-structure cases.

### Member 3 - Linked List
- Student Name: A.S Farwin | Student ID: 23da2-0787
- Implemented a singly linked list with a `Node` class.
- Implemented insert at beginning, insert at end, insert at position, delete first, delete by value, search and display.
- Added step counters that the performance comparison uses.
- Tested empty-list and invalid-position cases.

### Member 4 - Graph
- Student Name: M.E.F.Asika | Student ID: 23da2-1078
- Implemented an undirected graph using an adjacency list.
- Implemented add vertex and add edge, with validation for duplicate vertices and edges, self-loops and missing vertices.
- Implemented display, BFS (queue-based) and DFS (stack-based), each reporting visit order, steps and time.
- Implemented vertex search, a path-existence check and a sample-graph loader.
- Tested graph functionality, including missing-vertex handling.

### Member 5 - Integration, Main Menu and Performance
- Student Name: A.F.Ruja | Student ID: 23da2-0734
- Built the main menu and integrated all five components.
- Built the shared, validated input helper (`InputHelper`) and the results log (`ResultLog`).
- Built the performance comparison: linear vs binary search, array vs linked-list front insert, and BFS vs DFS.
- Ran full-system tests and wrote this README.

---

## 4. Technologies Used

- **Java 17+** (console application, standard library only)
- **JavaFX 21** and **Maven** (optional GUI in `javafx/`)
- **Git and GitHub** for version control, branches and pull requests

---

## 5. Main System Features

- Menu-driven console interface with input validation on every prompt
- Array: insert, insert at index, delete, linear search, display, random fill
- Stack and queue: push/pop/peek and enqueue/dequeue/peek, with underflow handling
- Linked list: insert (front, end, position), delete, search, display
- Searching: linear and binary search with step counts and timing
- Graph: add vertex, add edge, display, BFS, DFS, path check, sample graph
- Performance comparison table (steps and nanoseconds) and an explanation of the complexity
- "Display All Results" view that stores every search, traversal and comparison result
- Optional JavaFX GUI with tabs for Array, Stack, Queue, Linked List, Search, Graph, Performance and Results

---

## 6. Project Structure

```
.
├── README.md
├── GIT_GUIDE.md             Git workflow for the team
├── ASSIGNMENT_SUMMARY.md    Short summary of the assignment and implementation
├── .gitignore               Ignores compiled output (out/)
├── src/                     Console application (default package)
│   ├── Main.java
│   ├── ArrayOperations.java
│   ├── StackOperations.java
│   ├── QueueOperations.java
│   ├── LinkedListOperations.java
│   ├── SearchOperations.java
│   ├── Graph.java
│   ├── PerformanceComparison.java
│   ├── InputHelper.java
│   └── ResultLog.java
└── javafx/                  Optional JavaFX GUI
    ├── pom.xml
    └── src/main/java/graphfx/
        ├── GraphFxApp.java
        └── Graph.java
```

---

## 7. How to Run

### Console application (required)

Requirements: JDK 17 or newer (check with `java -version` and `javac -version`).

```bash
git clone https://github.com/fathima-ruja/Java-Based-console-application.git
cd Java-Based-console-application/project
mkdir out
javac -d out src/*.java
java -cp out Main
```

On Windows PowerShell use `dir src` instead of `ls src`, and the same commands otherwise.

In VS Code, open the project folder, press **Ctrl + `** to open the terminal, and run the commands above.

### JavaFX GUI (optional)

Requirements: JDK 17+ and Maven.

```bash
cd project/javafx
mvn clean javafx:run
```

---

## 8. Menu Guide (console)

```
1. Array Operations       6. Graph Operations
2. Stack Operations       7. Performance Comparison
3. Queue Operations       8. Display All Results
4. Linked List Operations 9. Exit
5. Searching Operations
```

Each component opens its own submenu, and each submenu has a "Return to Main Menu" option.
Searching runs on the data stored in the Array component, so add values there first.

---

## 9. Complexity Summary

| Operation | Time complexity |
|-----------|-----------------|
| Array insert at end | O(1) |
| Array insert at index / delete at index | O(n) (elements shift) |
| Array search (linear) | O(n) |
| Binary search (sorted data) | O(log n) |
| Stack push / pop / peek | O(1) amortised |
| Queue enqueue / dequeue / peek | O(1) amortised |
| Linked list insert at beginning | O(1) |
| Linked list insert at end / search / delete by value | O(n) |
| Graph add vertex / add edge (adjacency list) | O(1) / O(1) to O(degree) for duplicate check |
| Graph BFS / DFS | O(V + E) |

**Why results differ:** the performance table shows that binary search takes far fewer steps
than linear search on large data, because each comparison halves the remaining range. Inserting
at the front of an array requires shifting every element, while a linked list only updates the
head pointer. BFS and DFS visit the same vertices in O(V + E), but in different orders because
BFS uses a queue (level by level) and DFS uses a stack (deep first).

---

## 10. Collaboration and Git Workflow

Each member works on their own branch, commits their own files, and merges through a Pull Request.
See `GIT_GUIDE.md` for the commands. The repository history is the evidence of individual work.
Do not commit compiled files (`out/`) or temporary test files.

---

## 11. Known Limitations

- Stacks and queues only hold integers.
- The graph is undirected and unweighted.
- The array has a fixed capacity set at start-up (100 in the console version).
- The JavaFX GUI is an extra and is not required by the assignment brief.
