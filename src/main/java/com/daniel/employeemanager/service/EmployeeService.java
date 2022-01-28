package com.daniel.employeemanager.service;

import com.daniel.employeemanager.exception.EmployeeNotFoundException;
import com.daniel.employeemanager.model.Department;
import com.daniel.employeemanager.model.Employee;
import com.daniel.employeemanager.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Page<Employee> findAllEmployees(Pageable paging) {
        return employeeRepo.findAll(paging);
    }

    public Page<Employee> findAllEmployeesBySurname(String surname, Pageable pageable) {
        return employeeRepo.findBySurnameContaining(surname, pageable);
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Transactional
    public Employee updateEmployee(Long id, Employee employee) {
        Employee updatedEmployee = findEmployeeById(id);
        updatedEmployee.setEmail(employee.getEmail());
        updatedEmployee.setName(employee.getName());
        updatedEmployee.setSurname(employee.getSurname());
        updatedEmployee.setUsername(employee.getUsername());
        updatedEmployee.setPassword(employee.getPassword());
        updatedEmployee.setPhone(employee.getPhone());
//        updatedEmployee.setDepartment(employee.getDepartment());
//        updatedEmployee.setTitle(employee.getTitle());
        return updatedEmployee;
    }

    public Employee deleteEmployee(Long id) {
        Employee employee = findEmployeeById(id);
        employeeRepo.delete(employee);
        return employee;
    }
}
