
# ADV-shop
adpro


## 1

I applied multiple clean code principles to enhance readability and maintainability, using descriptive names for functions and variables to clarify their purpose. Each function follows the “Do one thing” rule, ensuring modularity and focus. In addition, I introduced input validation to confirm that forms receive the correct data types.


But there things that i still need to improve in the code on how i commit files and also give more proper documantation on the file with cleare comments but it will not clutter the file.

## 2

**Unit Tests**  
the unit tests helps validate each module and increases confidence about the code’s reliability. There is no strict rule on how many tests to include in a class; the goal is to cover different paths, edge cases, and common scenarios. Code coverage tools provide insight into tested parts of the codebase, but 100% coverage does not guarantee freedom from bugs—it only confirms that those lines have been executed in tests.

**New Functional Test Suite**  
Adding a new suite for a similar task might lead to repeated setup and code. This duplication reduces clarity and violates the DRY (Don’t Repeat Yourself) principle.

**Areas to Improve**  
1. **Code Duplication**: Extract shared setup into a base class or helper methods.  
2. **Class Responsibilities**: Keep test classes focused. Break them down if they grow large.  
3. **Naming and Organization**: Use descriptive names, group related tests, and maintain a consistent structure.
