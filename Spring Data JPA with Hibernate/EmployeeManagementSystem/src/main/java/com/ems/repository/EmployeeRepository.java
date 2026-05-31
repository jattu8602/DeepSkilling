package com.ems.repository;

import com.ems.dto.EmployeeProjection;
import com.ems.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByNameContainingIgnoreCase(String name);

    List<Employee> findByDepartmentName(String departmentName);

    @Query("SELECT e FROM Employee e WHERE e.department.id = :deptId")
    List<Employee> findByDepartmentId(@Param("deptId") Long deptId);

    @Query("SELECT e.name AS name, e.email AS email FROM Employee e WHERE e.department.id = :deptId")
    List<EmployeeProjection> findProjectedByDepartmentId(@Param("deptId") Long deptId);

    Page<Employee> findAll(Pageable pageable);

    List<Employee> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
