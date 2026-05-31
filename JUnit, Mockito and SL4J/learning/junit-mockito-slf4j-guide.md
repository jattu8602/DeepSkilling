# JUnit, Mockito & SLF4J - Simple Learning Guide

## What We Built

A **Spring Boot** project with **54 tests** covering JUnit 5, Mockito, Spring testing, and logging. All tests pass.

---

## 1. JUnit 5 Basics (8 test classes)

### What is JUnit?
A framework to write and run **repeatable tests** in Java.

### Key Annotations
| Annotation | What it does |
|-----------|-------------|
| `@Test` | Marks a method as a test |
| `@BeforeEach` | Runs before each test (setup) |
| `@AfterEach` | Runs after each test (cleanup) |
| `@BeforeAll` | Runs once before all tests |
| `@AfterAll` | Runs once after all tests |

### Assertions - Checking Results
```java
assertEquals(5, 2 + 3);       // Check if equal
assertTrue(5 > 3);            // Check if true
assertFalse(5 < 3);           // Check if false
assertNull(null);             // Check if null
assertNotNull(new Object());  // Check if not null
assertThrows(Exception.class, () -> method()); // Check exception thrown
```

### AAA Pattern (Arrange-Act-Assert)
```java
@Test
void testAdd() {
    // Arrange - setup
    Calculator calc = new Calculator();
    // Act - do the action
    int result = calc.add(2, 3);
    // Assert - check result
    assertEquals(5, result);
}
```

### Parameterized Tests - Same Test, Different Data
```java
@ParameterizedTest
@ValueSource(ints = {2, 4, 6, 8})
void testIsEven(int number) {
    assertTrue(evenChecker.isEven(number));
}
```

### Test Suites - Group Tests Together
```java
@Suite
@SelectClasses({CalculatorTest.class, AssertionsTest.class})
public class AllTests {}
```

### Test Ordering
```java
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderedTests {
    @Test @Order(1) void testFirst() {}
    @Test @Order(2) void testSecond() {}
}
```

### Timeout Tests
```java
@Test
@Timeout(value = 2, unit = TimeUnit.SECONDS)
void testPerformance() {
    // Fails if takes longer than 2 seconds
}
```

---

## 2. Mockito (6 test classes)

### What is Mockito?
A library to create **fake objects (mocks)** so you can test a class without its real dependencies (like databases, APIs, etc.)

### Creating Mocks
```java
@ExtendWith(MockitoExtension.class)  // Enable Mockito
class MyTest {
    @Mock
    private ExternalApi mockApi;  // Creates a fake ExternalApi
}
```

### Stubbing - Make Mock Return What You Want
```java
when(mockApi.getData()).thenReturn("Mock Data");
// Now mockApi.getData() always returns "Mock Data"
```

### Different Returns on Consecutive Calls
```java
when(mockApi.getData())
    .thenReturn("First")
    .thenReturn("Second");
// 1st call → "First", 2nd call → "Second"
```

### Verification - Check if Method Was Called
```java
verify(mockApi).getData();           // Was called exactly once
verify(mockApi, times(2)).getData(); // Was called 2 times
verify(mockApi, never()).getData();  // Was never called
```

### What We Mocked (Exercises)
| Exercise | Mock Object | What we tested |
|----------|------------|---------------|
| External API | `ExternalApi` | Service that calls external API |
| Repository | `Repository` | Service that gets data from DB |
| REST Client | `RestClient` | Service calling REST API |
| File I/O | `FileReader` + `FileWriter` | Service reading/writing files |
| Network | `NetworkClient` | Service connecting to network |

---

## 3. Spring Testing (3 test classes)

### Service Test - Mock Repository
```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock UserRepository userRepository;
    @InjectMocks UserService userService;  // Injects mock into service
    
    @Test
    void testGetUser() {
        when(userRepository.findById(1L))
            .thenReturn(Optional.of(new User(1L, "John")));
        User result = userService.getUserById(1L);
        assertEquals("John", result.getName());
    }
}
```

### Controller Test - MockMvc
```java
@WebMvcTest(UserController.class)  // Load only web layer
class UserControllerTest {
    @Autowired MockMvc mockMvc;
    @MockBean UserService userService;  // Mock the service
    
    @Test
    void testGetUser() throws Exception {
        when(userService.getUserById(1L))
            .thenReturn(new User(1L, "John"));
        
        mockMvc.perform(get("/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("John"));
    }
}
```

### Integration Test - Full App
```java
@SpringBootTest         // Load full application
@AutoConfigureMockMvc   // Auto-configure MockMvc
class UserIntegrationTest {
    @Autowired MockMvc mockMvc;
    
    @Test
    void testFullFlow() throws Exception {
        mockMvc.perform(post("/users")
            .content("{\"name\":\"Test\"}")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
```

---

## 4. SLF4J Logging (1 test + 1 example)

### What is SLF4J?
A logging framework with different levels: `ERROR` > `WARN` > `INFO` > `DEBUG` > `TRACE`

### How to Use
```java
private static final Logger logger = LoggerFactory.getLogger(MyClass.class);

logger.error("Error message");     // For errors
logger.warn("Warning message");    // For warnings
logger.info("Info message");       // General info
logger.debug("Debug message");     // Debug details

// Parameterized - better than string concatenation
logger.info("User {} logged in {} times", username, count);
```

### Logback Config (logback.xml)
```xml
<appender name="console" class="...ConsoleAppender">
    <!-- Logs to console -->
</appender>
<appender name="file" class="...FileAppender">
    <file>app.log</file>
    <!-- Logs to file -->
</appender>
<root level="debug">
    <appender-ref ref="console" />
    <appender-ref ref="file" />
</root>
```

---

## Project Structure Map

```
project/
├── pom.xml                 ✅ Spring Boot 3.2 + JUnit 5 + Mockito + H2
├── src/main/java/
│   ├── LearningApplication.java        (Starts Spring Boot)
│   ├── junit/     → Calculator, EvenChecker, etc.    (Code to test)
│   ├── mockito/   → ExternalApi, Service, etc.       (Interfaces + Services)
│   ├── spring/    → User, UserService, UserController (Full Spring app)
│   └── logging/   → LoggingExample                    (SLF4J demo)
└── src/test/java/
    ├── junit/     → 8 test classes   (54 individual tests)
    ├── mockito/   → 5 test classes
    ├── spring/    → 3 test classes
    └── logging/   → 1 test class
```

---

## Run Tests Yourself
```bash
cd "JUnit, Mockito and SL4J/project"
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home mvn test
```

All **54 tests** pass.
