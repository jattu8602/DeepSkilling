package com.ems.service;

import com.ems.entity.Department;
import com.ems.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department getDepartment(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found: " + id));
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department updateDepartment(Long id, Department details) {
        Department dept = getDepartment(id);
        dept.setName(details.getName());
        return departmentRepository.save(dept);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
