package com.cognizant.springlearn.service;

import com.cognizant.springlearn.dao.EmployeeDao;
import com.cognizant.springlearn.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional
    public List<Employee> getAllEmployees() {
        LOGGER.debug("Start");
        return employeeDao.getAllEmployees();
    }

    @Transactional
    public Employee updateEmployee(Employee employee) {
        LOGGER.debug("Start");
        return employeeDao.updateEmployee(employee);
    }

    @Transactional
    public void deleteEmployee(int id) {
        LOGGER.debug("Start");
        employeeDao.deleteEmployee(id);
        LOGGER.debug("End");
    }
}
