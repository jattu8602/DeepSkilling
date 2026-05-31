package com.ems.controller;

import com.ems.dto.EmployeeProjection;
import com.ems.entity.Employee;
import com.ems.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/search")
    public List<Employee> searchByName(@RequestParam String name) {
        return employeeService.searchByName(name);
    }

    @GetMapping("/department/{deptName}")
    public List<Employee> findByDepartment(@PathVariable String deptName) {
        return employeeService.findByDepartment(deptName);
    }

    @GetMapping("/paginated")
    public Page<Employee> getPaginated(Pageable pageable) {
        return employeeService.getEmployeesPaginated(pageable);
    }

    @GetMapping("/projections/{deptId}")
    public List<EmployeeProjection> getProjections(@PathVariable Long deptId) {
        return employeeService.getEmployeeProjections(deptId);
    }
}
