# Spring Core and Maven - Tasks and Solutions

## Exercise 1: Configuring a Basic Spring Application

**Objective:** Set up a Maven project with Spring Core and configure beans via XML.

**Solution:**
- Created Maven project `LibraryManagement` with `pom.xml` containing Spring Context dependency
- Added `applicationContext.xml` in `src/main/resources` defining beans for `BookService` and `BookRepository`
- Created `com.library.service.BookService` and `com.library.repository.BookRepository`
- Created `LibraryManagementApplication` main class to load context and test

**Key files:** `pom.xml`, `applicationContext.xml`, `BookService.java`, `BookRepository.java`, `LibraryManagementApplication.java`

---

## Exercise 2: Implementing Dependency Injection

**Objective:** Wire `BookRepository` into `BookService` using Spring DI.

**Solution:**
- Updated `applicationContext.xml` to inject `BookRepository` into `BookService` via `<property>`
- Added setter method `setBookRepository(BookRepository)` in `BookService`
- Verified injection by running the main class

**Key change:** `<property name="bookRepository" ref="bookRepository"/>` in `applicationContext.xml`

---

## Exercise 3: Implementing Logging with Spring AOP

**Objective:** Add AOP-based logging to track method execution times.

**Solution:**
- Added Spring AOP and AspectJ dependencies to `pom.xml`
- Created `com.library.aspect.LoggingAspect` with `@Around` advice to log method execution time
- Enabled AspectJ support in `applicationContext.xml`
- Verified logs appear on console

**Key files:** `LoggingAspect.java`, updated `pom.xml` and `applicationContext.xml`

---

## Exercise 4: Creating and Configuring a Maven Project

**Objective:** Set up a Maven project with Spring dependencies and compiler plugin.

**Solution:**
- Created `pom.xml` with Spring Context, Spring AOP, and Spring WebMVC dependencies
- Configured Maven Compiler Plugin for source/target compatibility

**Key change:** Compiler plugin in `<build><plugins>` section

---

## Exercise 5: Configuring the Spring IoC Container

**Objective:** Central bean configuration using XML.

**Solution:**
- Same approach as Exercise 1-2 — `applicationContext.xml` defines beans and wires dependencies
- `BookService` has setter for `BookRepository`
- Main class loads context and tests wiring

---

## Exercise 6: Configuring Beans with Annotations

**Objective:** Replace XML bean definitions with component scanning and annotations.

**Solution:**
- Added `<context:component-scan base-package="com.library"/>` to `applicationContext.xml`
- Added `@Service` on `BookService` and `@Repository` on `BookRepository`
- Used `@Autowired` on setter for injection
- Added Spring Context namespace to XML root element

**Key change:** Annotations replace explicit `<bean>` definitions

---

## Exercise 7: Implementing Constructor and Setter Injection

**Objective:** Use both constructor and setter injection.

**Solution:**
- Added constructor argument in `BookService` and configured `<constructor-arg>` in XML
- Kept setter injection for `BookRepository`
- Verified both injection types work

**Key change:** `<constructor-arg ref="..."/>` in addition to `<property>` in XML

---

## Exercise 8: Implementing Basic AOP with Spring

**Objective:** Create aspects for cross-cutting concerns.

**Solution:**
- Created `LoggingAspect` with `@Before` and `@After` advice methods
- Registered aspect in `applicationContext.xml` with `<aop:aspectj-autoproxy/>`
- Verified logging output before and after method execution

**Key change:** Multiple advice methods in the aspect

---

## Exercise 9: Creating a Spring Boot Application

**Objective:** Build a RESTful library management system with Spring Boot.

**Solution:**
- Created `LibraryManagementApplication` with `@SpringBootApplication`
- Added dependencies: Spring Web, Spring Data JPA, H2 Database
- Configured `application.properties` with H2 in-memory DB settings
- Created `Book` entity with JPA annotations, `BookRepository` interface extending `JpaRepository`
- Created `BookController` with CRUD REST endpoints (`GET`, `POST`, `PUT`, `DELETE`)
- Tested endpoints via curl

**Key files:** `LibraryManagementApplication.java`, `Book.java`, `BookRepository.java`, `BookController.java`, `application.properties`

---

## Full Project Structure (LibraryManagement)

```
LibraryManagement/
├── pom.xml
└── src/main/
    ├── java/com/library/
    │   ├── LibraryManagementApplication.java
    │   ├── repository/
    │   │   └── BookRepository.java
    │   ├── service/
    │   │   └── BookService.java
    │   └── aspect/
    │       └── LoggingAspect.java
    └── resources/
        └── applicationContext.xml
```
