package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDao.class);

    private static List<Employee> EMPLOYEE_LIST;

    public EmployeeDao() {
        ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
        EMPLOYEE_LIST = (List<Employee>) context.getBean("employeeList");
        LOGGER.debug("Employee List: {}", EMPLOYEE_LIST);
        ((ClassPathXmlApplicationContext) context).close();
    }

    public List<Employee> getAllEmployees() {
        return EMPLOYEE_LIST;
    }

    public Employee updateEmployee(Employee employee) {
        LOGGER.debug("Start");
        for (int i = 0; i < EMPLOYEE_LIST.size(); i++) {
            if (EMPLOYEE_LIST.get(i).getId().equals(employee.getId())) {
                EMPLOYEE_LIST.set(i, employee);
                LOGGER.debug("End");
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Employee not found with id: " + employee.getId());
    }

    public void deleteEmployee(int id) {
        LOGGER.debug("Start");
        Employee toRemove = null;
        for (Employee e : EMPLOYEE_LIST) {
            if (e.getId().equals(id)) {
                toRemove = e;
                break;
            }
        }
        if (toRemove != null) {
            EMPLOYEE_LIST.remove(toRemove);
            LOGGER.debug("End - Employee {} removed", id);
        } else {
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }
    }
}
