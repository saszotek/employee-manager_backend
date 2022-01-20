package com.daniel.employeemanager.controller;

import com.daniel.employeemanager.model.Employee;
import com.daniel.employeemanager.model.dto.EmployeeDto;
import com.daniel.employeemanager.service.DepartmentService;
import com.daniel.employeemanager.service.EmployeeService;
import com.daniel.employeemanager.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final TitleService titleService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService, TitleService titleService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.titleService = titleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> getAllEmployee() {
        List<Employee> employees = employeeService.findAllEmployees();
        List<EmployeeDto> employeesDto = employees.stream().map(EmployeeDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(employeesDto, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(EmployeeDto.from(employee), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee newEmployee = employeeService.addEmployee(Employee.from(employeeDto));
        return new ResponseEntity<>(EmployeeDto.from(newEmployee), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDto employeeDto) {
        Employee updateEmployee = employeeService.updateEmployee(id, Employee.from(employeeDto));
        return new ResponseEntity<>(EmployeeDto.from(updateEmployee), HttpStatus.OK);
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable("id") Long id) {
        Employee employee = employeeService.deleteEmployee(id);
        return new ResponseEntity<>(EmployeeDto.from(employee), HttpStatus.OK);
    }
}
