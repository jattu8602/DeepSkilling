package com.ems.service;

import com.ems.dto.EmployeeProjection;
import com.ems.entity.Employee;
import com.ems.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + id));
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(Long id, Employee details) {
        Employee emp = getEmployee(id);
        emp.setName(details.getName());
        emp.setEmail(details.getEmail());
        emp.setDepartment(details.getDepartment());
        return employeeRepository.save(emp);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> searchByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Employee> findByDepartment(String deptName) {
        return employeeRepository.findByDepartmentName(deptName);
    }

    public Page<Employee> getEmployeesPaginated(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public List<EmployeeProjection> getEmployeeProjections(Long deptId) {
        return employeeRepository.findProjectedByDepartmentId(deptId);
    }
}
