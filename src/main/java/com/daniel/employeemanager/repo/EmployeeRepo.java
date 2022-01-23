package com.daniel.employeemanager.repo;

import com.daniel.employeemanager.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Page<Employee> findBySurnameContaining(String surname, Pageable pageable);
}
