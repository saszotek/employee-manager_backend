package com.daniel.employeemanager.controller;

import com.daniel.employeemanager.model.Department;
import com.daniel.employeemanager.model.dto.DepartmentDto;
import com.daniel.employeemanager.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DepartmentDto>> getAllDepartment() {
        List<Department> departments = departmentService.findAllDepartments();
        List<DepartmentDto> departmentsDto = departments.stream().map(DepartmentDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(departmentsDto, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Long id) {
        Department department = departmentService.findDepartmentById(id);
        return new ResponseEntity<>(DepartmentDto.from(department), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<DepartmentDto> addDepartment(@RequestBody DepartmentDto departmentDto) {
        Department newDepartment = departmentService.addDepartment(Department.from(departmentDto));
        return new ResponseEntity<>(DepartmentDto.from(newDepartment), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id") Long id, @RequestBody DepartmentDto departmentDto) {
        Department updateDepartment = departmentService.updateDepartment(id, Department.from(departmentDto));
        return new ResponseEntity<>(DepartmentDto.from(updateDepartment), HttpStatus.OK);
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<DepartmentDto> deleteDepartment(@PathVariable("id") Long id) {
        Department department = departmentService.deleteDepartment(id);
        return new ResponseEntity<>(DepartmentDto.from(department), HttpStatus.OK);
    }

    @PostMapping("/add/{departmentId}/employee/{employeeId}")
    public ResponseEntity<DepartmentDto> addEmployeeToDepartment(@PathVariable("departmentId") Long departmentId, @PathVariable("employeeId") Long employeeId) {
        Department department = departmentService.addEmployeeToDepartment(departmentId, employeeId);
        return new ResponseEntity<>(DepartmentDto.from(department), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{departmentId}/employee/{employeeId}")
    public ResponseEntity<DepartmentDto> removeEmployeeFromDepartment(@PathVariable("departmentId") Long departmentId, @PathVariable("employeeId") Long employeeId) {
        Department department = departmentService.removeEmployeeFromDepartment(departmentId, employeeId);
        return new ResponseEntity<>(DepartmentDto.from(department), HttpStatus.OK);
    }
}