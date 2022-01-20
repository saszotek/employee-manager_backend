package com.daniel.employeemanager.model;

import com.daniel.employeemanager.model.dto.DepartmentDto;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "employees"})
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    @OneToMany(
            mappedBy = "department",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private List<Employee> employees = new ArrayList<>();

    public Department() {
    }

    public Department(Long id, String name, String city, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.employees = employees;
    }

    public static Department from(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setCity(departmentDto.getCity());
//        department.setEmployees(departmentDto.getEmployees());
        return department;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.setDepartment(null);
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
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", employees=" + employees +
                '}';
    }
}
