
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


## Week 2

1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

    - Things that i change based on the sonarcloud is imporving how some of the code are writen to increase the realiability and the maintainability of the project. This include grouping things based on what they do and also removing unnececary field injection and by using constructor injection insted.

2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment?

    - Based on the things that i have implement in my project for the CI part where when each push happen the code is always tested to ensure that there is nothing wrong with the file and to be perfectly running. but i have not implement the CD part becasue for the web app i have chosen koyeb where once the github is connected it will automaticly depoly the newst version.


## Week 3
1) Explain what principles you apply to your project!
- Single Responsibility Principle (SRP)
    * Each class has a single responsibility and reason to change
    * Examples include separating repositories, services, and controllers
- Open/Closed Principle (OCP)
    * Classes are open for extension but closed for modification
    * Implementation through interfaces and abstraction
- Liskov Substitution Principle (LSP)
    * Subtypes can replace their base types without altering program correctness
    * Properly implemented inheritance hierarchies



2) Explain the advantages of applying SOLID principles to your project with examples.
- Single Responsibility Principle
    * Advantage: Better organization and easier maintenance
```java
@Repository
public class ProductRepository implements ProductRepoInterface {
    private final List<Product> productData = new ArrayList<>();
    
    @Override
    public Product create(Product product) {
        product.setProductId("P" + (productData.size() + 1));
        productData.add(product);
        return product;
    }
}
```

- Open/Closed Principle
    * Advantage: Extensibility without changing existing code
```java
// Interface defines contract
public interface ProductRepoInterface {
    Product create(Product product);
    Iterator<Product> findAll();
    Product findById(String productId);
    Product edit(Product Product);
    void delete(String productId);
}

// Implementation adheres to contract
@Repository
public class ProductRepository implements ProductRepoInterface {
    // Implementation details
}
```

- Liskov Substitution Principle
    * Advantage: Substitutability of implementations


```java
// Both interfaces have similar structure but are specific to their domains
public interface ProductRepoInterface {
    Product create(Product product);
    // Other methods...
}

public interface CarRepoInterface {
    Car create(Car car);
    // Other methods...
}

// Services can work with any implementation of the interfaces
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepoInterface productRepository; // Can be any implementation
}
```

3) Explain the disadvantages of not applying SOLID principles to your project with examples.

- Without Single Responsibility Principle
    * Disadvantage: Tangled concerns make maintenance difficult

```java
public class ProductManager {
    private List<Product> products = new ArrayList<>();
    
    public Product createProduct(Product product) {
        // Generate ID (repository concern)
        product.setProductId("P" + (products.size() + 1));
        
        // Validate product (service concern)
        if (product.getProductQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        
        return product;
    }
}
```

- Without Open/Closed Principle
    * Disadvantage: Modifying existing code to add functionality


## WEEK 4

1. Reflect based on Percival (2017) proposed self-reflective questions (in “Principles and Best Practice of Testing” submodule, chapter “Evaluating Your Testing Objectives”), whether this TDD flow is useful enough for you or not. If not, explain things that you need to do next time you make more tests.

    Its usfule in the ability of bug detectin. with TDD it help catches issues early with the consideration to cover both happt and edge cases. other then that is force us to consider the interface bfore we implemate it. 


2. 
