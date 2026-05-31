# Spring REST using Spring Boot - Tasks and Solutions

## Part 1: Spring Boot & REST Basics (Hands-on 1 & 2)

### Create Spring Boot Project
**Objective:** Use Spring Initializr to create a `spring-learn` project with Spring Web.

**Solution:**
- Project: `spring-learn` with Spring Boot 2.7.18, Spring Web, DevTools
- Main class with `@SpringBootApplication`
- `application.properties` configured with `server.port=8083`, logging

### Hello World REST Service
**Objective:** Return "Hello World!!" from a GET endpoint.

**Solution:**
- `HelloController` with `@RestController` and `@GetMapping("/hello")`
- Returns string `"Hello World!!"`

### Country REST Service
**Objective:** Return country details as JSON.

**Solution:**
- `Country` model class with `code` and `name` fields
- `country.xml` defines country beans (IN, US, JP, DE)
- `CountryController` with endpoints:
  - `GET /country` → returns India
  - `GET /countries` → returns all countries
  - `GET /countries/{code}` → returns specific country (case-insensitive)
- `CountryService.getCountry()` throws `CountryNotFoundException` if not found
- `@ResponseStatus(NOT_FOUND)` on exception → 404 with JSON body

### MockMVC Testing
**Objective:** Test REST services with MockMVC.

**Solution:**
- `@AutoConfigureMockMvc` + `@Autowired MockMvc`
- Tests: controller loaded, `GET /country` returns status 200, `$.code = IN`, `$.name = India`
- Exception test: `GET /country/az` returns status 400, reason "Country not found"

---

## Part 2: Employee REST Services (Hands-on 3)

**Objective:** Expose employee and department data via REST.

**Solution:**
- `employee.xml` with employee data (departments, skills)
- `EmployeeDao` reads from XML into static `EMPLOYEE_LIST`
- `EmployeeService` (`@Service`) with `getAllEmployees()`
- `EmployeeController` with `GET /employees`
- `DepartmentController` with `GET /departments`

---

## Part 3: POST/PUT/DELETE & Validation (Hands-on 4)

**Objective:** Full CRUD with input validation and error handling.

**Solution:**
- `CountryController` with RESTful naming:
  - `POST /countries` with `@RequestBody @Valid Country`
  - `PUT /countries` with `@RequestBody @Valid Country`
  - `DELETE /countries/{code}`
- Validation annotations on model fields:
  - `@NotNull`, `@Size(min=2, max=2)`, `@NotBlank`, `@Min`, `@Max`, `@JsonFormat`
- `GlobalExceptionHandler` (`@ControllerAdvice`):
  - `handleMethodArgumentNotValid` → validation errors in JSON
  - `handleHttpMessageNotReadable` → type mismatch errors

---

## Part 4: JWT Authentication (Hands-on 5)

**Objective:** Secure REST APIs with JWT using Spring Security.

**Solution:**
- Add `spring-boot-starter-security` + `jjwt 0.9.0`
- `SecurityConfig` extends `WebSecurityConfigurerAdapter`:
  - In-memory users: `admin`/`pwd` (ADMIN), `user`/`pwd` (USER)
  - `POST /authenticate` accessible to USER/ADMIN
  - All other requests require authentication
- `AuthenticationController`:
  - `GET /authenticate` reads Basic Auth header, decodes Base64, generates JWT
  - JWT: subject=user, issuedAt=now, expiration=20min, signed with HS256 + "secretkey"
- `JwtAuthorizationFilter` extends `BasicAuthenticationFilter`:
  - Reads `Authorization: Bearer <token>` header
  - Parses and validates JWT, sets `SecurityContext`
- Test flow:
  1. `curl -u user:pwd http://localhost:8083/authenticate` → get token
  2. `curl -H "Authorization: Bearer <token>" http://localhost:8083/countries` → authorized access

---

## Project Structure

```
spring-learn/
├── pom.xml
└── src/main/
    ├── java/com/cognizant/springlearn/
    │   ├── SpringLearnApplication.java
    │   ├── GlobalExceptionHandler.java
    │   ├── controller/
    │   │   ├── HelloController.java
    │   │   ├── CountryController.java
    │   │   ├── EmployeeController.java
    │   │   ├── DepartmentController.java
    │   │   └── AuthenticationController.java
    │   ├── service/
    │   │   ├── CountryService.java
    │   │   ├── EmployeeService.java
    │   │   ├── DepartmentService.java
    │   │   └── exception/
    │   │       ├── CountryNotFoundException.java
    │   │       └── EmployeeNotFoundException.java
    │   ├── dao/
    │   │   ├── CountryDao.java
    │   │   ├── EmployeeDao.java
    │   │   └── DepartmentDao.java
    │   ├── model/
    │   │   ├── Country.java
    │   │   ├── Employee.java
    │   │   ├── Department.java
    │   │   └── Skill.java
    │   └── security/
    │       ├── SecurityConfig.java
    │       └── JwtAuthorizationFilter.java
    └── resources/
        ├── application.properties
        ├── country.xml
        └── employee.xml
```
