# Spring Data JPA with Hibernate - Tasks and Solutions

---

## Part 1: Employee Management System (Spring Boot Project)

### Exercise 1: Overview and Setup
**Objective:** Initialize a Spring Boot project with JPA, H2, Web, Lombok.

**Solution:**
- Created `EmployeeManagementSystem` with Spring Boot parent
- Dependencies: `spring-boot-starter-data-jpa`, `spring-boot-starter-web`, `h2`, `lombok`
- Configured `application.properties` with H2 in-memory database

### Exercise 2: Creating Entities
**Objective:** Define JPA entities for Employee and Department with relationships.

**Solution:**
- `Employee` entity with `@ManyToOne` to `Department`
- `Department` entity with `@OneToMany(mappedBy="department")` to `Employee`
- Used Lombok annotations (`@Data`, `@NoArgsConstructor`, etc.)

### Exercise 3: Creating Repositories
**Objective:** Create JPA repositories with derived query methods.

**Solution:**
- `EmployeeRepository extends JpaRepository<Employee, Long>`
- `DepartmentRepository extends JpaRepository<Department, Long>`
- Custom methods: `findByNameContaining`, `findByDepartmentName`, etc.

### Exercise 4: CRUD Operations
**Objective:** RESTful CRUD operations for employees and departments.

**Solution:**
- `EmployeeController` with `GET/POST/PUT/DELETE /api/employees`
- `DepartmentController` with `GET/POST/PUT/DELETE /api/departments`
- Uses service layer for business logic

### Exercise 5: Query Methods
**Objective:** Custom queries using method names and `@Query`.

**Solution:**
- Derived query methods: `findByEmail`, `findByNameContainingIgnoreCase`
- `@Query` annotations: `findEmployeesByDepartmentId`, custom JPQL queries
- Named queries via `@NamedQuery` on entity

### Exercise 6: Pagination and Sorting
**Objective:** Add pagination and sorting to employee search.

**Solution:**
- `GET /api/employees?page=0&size=5&sort=name,asc`
- `Page<Employee>` return type with `Pageable` parameter
- Custom query with `@Query` accepting `Pageable`

### Exercise 7: Entity Auditing
**Objective:** Track creation/modification timestamps.

**Solution:**
- `@EnableJpaAuditing` in main class
- `@CreatedDate`, `@LastModifiedDate` on entity fields
- `@EntityListeners(AuditingEntityListener.class)` on entity

### Exercise 8: Projections
**Objective:** Fetch subsets of entity data.

**Solution:**
- Interface-based projection: `EmployeeProjection` with `getName()` and `getEmail()`
- Repository method returning `List<EmployeeProjection>`

### Exercise 9: Data Source Configuration
**Objective:** Externalize and customize data source config.

**Solution:**
- Configured additional datasource properties in `application.properties`
- Demonstrated multi-profile configuration (`application-dev.properties`, `application-prod.properties`)

### Exercise 10: Hibernate-Specific Features
**Objective:** Use Hibernate-specific annotations and batching.

**Solution:**
- `@BatchSize`, `@Fetch(FetchMode.JOIN)` for performance
- Configured `hibernate.jdbc.batch_size` in properties

---

## Part 2: Hands-on Tasks (OrmLearn Project)

### Hands-on 1: Query Methods - Country Table
- `findByNameContaining(String infix)` — search by text
- `findByNameContainingOrderByNameAsc(String infix)` — sorted result
- `findByNameStartingWith(String prefix)` — alphabet index search

### Hands-on 2: Query Methods - Stock Table
- `findByCodeAndDateBetween(String code, Date from, Date to)` — monthly FB data
- `findByCodeAndOpenGreaterThan(String code, double price)` — stocks > 1250
- `findTop3ByOrderByVolumeDesc()` — top 3 highest volume
- `findTop3ByCodeOrderByCloseAsc(String code)` — 3 lowest Netflix closes

### Hands-on 3: Payroll Tables and Bean Mapping
- Created `Employee`, `Department`, `Skill` entities with `@Entity`, `@Table`, `@Id`, `@GeneratedValue`
- Repositories: `EmployeeRepository`, `DepartmentRepository`, `SkillRepository`

### Hands-on 4: Many-to-One (Employee ↔ Department)
- `@ManyToOne` + `@JoinColumn` on `Employee.department`
- `testGetEmployee()` — fetches employee with department (EAGER by default)
- `testAddEmployee()` — creates and saves new employee
- `testUpdateEmployee()` — updates employee's department

### Hands-on 5: One-to-Many (Department ↔ Employee)
- `@OneToMany(mappedBy="department")` on `Department.employeeList`
- Initially LAZY — causes `LazyInitializationException`
- Solution: `@OneToMany(mappedBy="department", fetch=FetchType.EAGER)`

### Hands-on 6: Many-to-Many (Employee ↔ Skill)
- `@ManyToMany` + `@JoinTable` on `Employee.skillList`
- `@ManyToMany(mappedBy="skillList")` on `Skill.employeeList`
- Add fetch type EAGER to avoid `LazyInitializationException`

### Part 3: HQL / JPQL / Native / Criteria

### Hands-on 1: HQL vs JPQL
- HQL = Hibernate Query Language (superset)
- JPQL = Java Persistence Query Language (subset of HQL)
- Both object-oriented, work with entity names and fields not tables

### Hands-on 2: HQL - Get All Permanent Employees
```java
@Query("SELECT e FROM Employee e left join fetch e.department d left join fetch e.skillList WHERE e.permanent = 1")
```
- `fetch` populates joined beans (without it, only link, not data)

### Hands-on 3: Quiz Attempt Details HQL
- Schema: `user → attempt → attempt_question → question`, `attempt_option → options`
- Complex HQL with multiple `fetch` joins for nested details

### Hands-on 4: Aggregate Functions in HQL
```java
@Query("SELECT AVG(e.salary) FROM Employee e WHERE e.department.id = :id")
double getAverageSalary(@Param("id") int id);
```

### Hands-on 5: Native Queries
```java
@Query(value = "SELECT * FROM employee", nativeQuery = true)
List<Employee> getAllEmployeesNative();
```

### Hands-on 6: Criteria Query
- Dynamic query building with `CriteriaBuilder`, `CriteriaQuery`, `Root`, `Predicate`
- Useful for dynamic filters (e.g., Amazon product search with variable criteria)
