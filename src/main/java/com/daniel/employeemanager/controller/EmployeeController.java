package com.daniel.employeemanager.controller;

import com.daniel.employeemanager.model.Employee;
import com.daniel.employeemanager.model.dto.EmployeeDto;
import com.daniel.employeemanager.service.DepartmentService;
import com.daniel.employeemanager.service.EmployeeService;
import com.daniel.employeemanager.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> getAllEmployee(@RequestParam(required = false) String surname,
                                                              @RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "3") int size) {
        List<Employee> employees = new ArrayList<Employee>();
        Pageable paging = PageRequest.of(page, size);

        Page<Employee> pageEmployees;
        if (surname == null) {
            pageEmployees = employeeService.findAllEmployees(paging);
        } else {
            pageEmployees = employeeService.findAllEmployeesBySurname(surname, paging);
        }

        employees = pageEmployees.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("employees", employees);
        response.put("currentPage", pageEmployees.getNumber());
        response.put("totalItems", pageEmployees.getTotalElements());
        response.put("totalPages", pageEmployees.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
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
