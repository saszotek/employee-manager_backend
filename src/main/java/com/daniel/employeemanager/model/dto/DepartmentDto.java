package com.daniel.employeemanager.model.dto;

import com.daniel.employeemanager.model.Department;
import com.daniel.employeemanager.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class DepartmentDto {
    private Long id;
    private String name;
    private String city;
    private List<Employee> employees = new ArrayList<>();

    public DepartmentDto() {
    }

    public DepartmentDto(Long id, String name, String city, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.employees = employees;
    }

    public static DepartmentDto from(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());
        departmentDto.setCity(department.getCity());
        departmentDto.setEmployees(department.getEmployees());
        return departmentDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "DepartmentDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", employees=" + employees +
                '}';
    }
}
