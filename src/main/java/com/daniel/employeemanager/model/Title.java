package com.daniel.employeemanager.model;

import com.daniel.employeemanager.model.dto.TitleDto;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "employees"})
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(
            mappedBy = "title",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private List<Employee> employees = new ArrayList<>();

    public Title() {
    }

    public Title(Long id, String name, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }

    public static Title from(TitleDto titleDto) {
        Title title = new Title();
        title.setName(titleDto.getName());
//        title.setEmployees(titleDto.getEmployees());
        return title;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.setTitle(null);
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Title{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employees=" + employees +
                '}';
    }
}
