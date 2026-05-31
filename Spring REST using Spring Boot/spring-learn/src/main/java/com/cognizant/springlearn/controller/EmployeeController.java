package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        LOGGER.debug("Start");
        return employeeService.getAllEmployees();
    }

    @PutMapping
    public void updateEmployee(@RequestBody @Valid Employee employee) {
        LOGGER.debug("Start");
        employeeService.updateEmployee(employee);
        LOGGER.debug("End");
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) {
        LOGGER.debug("Start - Deleting employee: {}", id);
        employeeService.deleteEmployee(id);
        LOGGER.debug("End");
    }
}
