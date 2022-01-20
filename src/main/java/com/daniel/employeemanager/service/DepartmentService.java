package com.daniel.employeemanager.service;

import com.daniel.employeemanager.exception.DepartmentNotFoundException;
import com.daniel.employeemanager.exception.EmployeeAlreadyAssignedDepartmentException;
import com.daniel.employeemanager.model.Department;
import com.daniel.employeemanager.model.Employee;
import com.daniel.employeemanager.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class DepartmentService {
    private final DepartmentRepo departmentRepo;
    private final EmployeeService employeeService;

    @Autowired
    public DepartmentService(DepartmentRepo departmentRepo, EmployeeService employeeService) {
        this.departmentRepo = departmentRepo;
        this.employeeService = employeeService;
    }

    public List<Department> findAllDepartments() {
        return departmentRepo.findAll();
    }

    public Department findDepartmentById(Long id) {
        return departmentRepo.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    public Department addDepartment(Department department) {
        return departmentRepo.save(department);
    }

    @Transactional
    public Department updateDepartment(Long id, Department department) {
        Department updatedDepartment = findDepartmentById(id);
        updatedDepartment.setName(department.getName());
        updatedDepartment.setCity(department.getCity());
        return updatedDepartment;
    }

    public Department deleteDepartment(Long id) {
        Department department = findDepartmentById(id);
        departmentRepo.delete(department);
        return department;
    }

    @Transactional
    public Department addEmployeeToDepartment(Long departmentId, Long employeeId) {
        Department department = findDepartmentById(departmentId);
        Employee employee = employeeService.findEmployeeById(employeeId);
        if (Objects.nonNull(employee.getDepartment())) {
            throw new EmployeeAlreadyAssignedDepartmentException(employeeId, employee.getDepartment().getId());
        }
        department.addEmployee(employee);
        employee.setDepartment(department);
        return department;
    }

    @Transactional
    public Department removeEmployeeFromDepartment(Long departmentId, Long employeeId) {
        Department department = findDepartmentById(departmentId);
        Employee employee = employeeService.findEmployeeById(employeeId);
        department.removeEmployee(employee);
        return department;
    }
}
