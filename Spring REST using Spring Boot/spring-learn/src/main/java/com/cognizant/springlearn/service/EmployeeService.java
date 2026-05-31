package com.cognizant.springlearn.service;

import com.cognizant.springlearn.dao.EmployeeDao;
import com.cognizant.springlearn.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeDao employeeDao;

    public List<Employee> getAllEmployees() {
        LOGGER.debug("Start");
        return employeeDao.getAllEmployees();
    }

    public Employee updateEmployee(Employee employee) {
        LOGGER.debug("Start");
        return employeeDao.updateEmployee(employee);
    }

    public void deleteEmployee(int id) {
        LOGGER.debug("Start");
        employeeDao.deleteEmployee(id);
        LOGGER.debug("End");
    }
}
