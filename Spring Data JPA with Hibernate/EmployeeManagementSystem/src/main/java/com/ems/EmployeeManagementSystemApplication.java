package com.ems;

import com.ems.entity.Department;
import com.ems.entity.Employee;
import com.ems.repository.DepartmentRepository;
import com.ems.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Set;

@SpringBootApplication
@EnableJpaAuditing
public class EmployeeManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementSystemApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(DepartmentRepository deptRepo, EmployeeRepository empRepo) {
        return args -> {
            Department it = deptRepo.save(
                    Department.builder().name("IT").build());
            Department hr = deptRepo.save(
                    Department.builder().name("HR").build());

            empRepo.save(Employee.builder()
                    .name("Alice")
                    .email("alice@company.com")
                    .department(it).build());
            empRepo.save(Employee.builder()
                    .name("Bob")
                    .email("bob@company.com")
                    .department(it).build());
            empRepo.save(Employee.builder()
                    .name("Carol")
                    .email("carol@company.com")
                    .department(hr).build());

            System.out.println("--- Sample data loaded ---");
            System.out.println("Departments: " + deptRepo.count());
            System.out.println("Employees: " + empRepo.count());
        };
    }
}
