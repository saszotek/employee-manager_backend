package com.daniel.employeemanager.model.dto;

import com.daniel.employeemanager.model.Employee;
import com.daniel.employeemanager.model.Title;

import java.util.ArrayList;
import java.util.List;

public class TitleDto {
    private Long id;
    private String name;
    private List<Employee> employees = new ArrayList<>();

    public TitleDto() {
    }

    public TitleDto(Long id, String name, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }

    public static TitleDto from(Title title) {
        TitleDto titleDto = new TitleDto();
        titleDto.setId(title.getId());
        titleDto.setName(title.getName());
        titleDto.setEmployees(title.getEmployees());
        return titleDto;
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
        return "TitleDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employees=" + employees +
                '}';
    }
}
