# Engineering Concepts - Learning Summary

## Part 1: Algorithms & Data Structures

### Exercise 1 - Inventory Management System
- **Data structures** like ArrayList, HashMap are essential for efficient storage/retrieval
- **Product** class with attributes: productId, productName, quantity, price
- Operations: add, update, delete - analyze time complexity of each

### Exercise 2 - E-commerce Search Function
- **Big O notation**: analyzes algorithm performance (best, average, worst case)
- **Linear Search** (O(n)) vs **Binary Search** (O(log n))
- Binary search requires sorted data but is much faster for large datasets

### Exercise 3 - Sorting Customer Orders
- Sorting algorithms: **Bubble Sort** (O(n²)), **Insertion Sort**, **Quick Sort** (O(n log n)), **Merge Sort** (O(n log n))
- Quick Sort is generally preferred over Bubble Sort due to better time complexity

### Exercise 4 - Employee Management System
- **Arrays**: stored contiguously in memory, O(1) access by index
- Operations: add (O(1) at end / O(n) at arbitrary position), search (O(n)), traverse (O(n)), delete (O(n))
- Limitations: fixed size, costly insert/delete operations

### Exercise 5 - Task Management System (Linked Lists)
- **Singly Linked List**: each node has data + pointer to next node
- **Doubly Linked List**: each node has prev + next pointers
- Advantages over arrays: dynamic size, efficient insert/delete (O(1) at head)

### Exercise 6 - Library Management System (Search)
- Linear Search: simple, works on unsorted data, O(n)
- Binary Search: requires sorted data, O(log n), much faster for large datasets

### Exercise 7 - Financial Forecasting (Recursion)
- **Recursion**: method calls itself to break down complex problems
- Time complexity analysis is important; optimize with memoization to avoid redundant calculations

---

## Part 2: Design Patterns & Principles

### 1. Singleton Pattern
- Ensures only **one instance** of a class (e.g., Logger)
- Private constructor, static instance, public static getInstance() method

### 2. Factory Method Pattern
- Defines an interface for creating objects but lets subclasses decide which class to instantiate
- Example: DocumentFactory -> WordDocument, PdfDocument, ExcelDocument

### 3. Builder Pattern
- Separates construction of complex objects from their representation
- Example: Computer with optional parts (CPU, RAM, Storage) via a Builder class

### 4. Adapter Pattern
- Allows incompatible interfaces to work together
- Example: PaymentProcessor adapter for different payment gateways (PayPal, Credit Card)

### 5. Decorator Pattern
- Dynamically adds responsibilities to objects (more flexible than inheritance)
- Example: Notification system with Email + SMS + Slack decorators

### 6. Proxy Pattern
- Provides a surrogate/placeholder for another object to control access
- Example: ProxyImage for lazy loading and caching remote images

### 7. Observer Pattern
- Defines a one-to-many dependency where observers are notified of state changes
- Example: StockMarket notifies MobileApp and WebApp when stock prices change

### 8. Strategy Pattern
- Defines a family of algorithms, encapsulates each, and makes them interchangeable
- Example: PaymentStrategy with CreditCardPayment and PayPalPayment

### 9. Command Pattern
- Encapsulates a request as an object, parameterizing clients with different requests
- Example: RemoteControl with LightOnCommand and LightOffCommand

### 10. MVC Pattern
- **Model**: data and business logic (e.g., Student)
- **View**: presentation/UI (e.g., StudentView)
- **Controller**: handles input and updates model/view (e.g., StudentController)

### 11. Dependency Injection
- Dependencies are "injected" into a class rather than created internally
- **Constructor injection** is a common approach
- Example: CustomerService depends on CustomerRepository (injected via constructor)

---

## Key Takeaways

| Topic | Core Concept |
|-------|-------------|
| **Data Structures** | Choose right structure (ArrayList vs HashMap vs LinkedList) based on operations |
| **Big O** | Analyze time/space complexity to optimize performance |
| **Search/Sort** | Binary Search + Quick Sort preferred for large datasets |
| **Creational Patterns** | Singleton, Factory, Builder - object creation flexibility |
| **Structural Patterns** | Adapter, Decorator, Proxy - class/object composition |
| **Behavioral Patterns** | Observer, Strategy, Command - communication between objects |
| **Architectural** | MVC (separation of concerns), DI (loose coupling) |
