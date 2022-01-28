package com.daniel.employeemanager.model.dto;

import com.daniel.employeemanager.model.Department;
import com.daniel.employeemanager.model.Employee;
import com.daniel.employeemanager.model.Title;

public class EmployeeDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String username;
    private String password;
    private Department department;
    private Title title;

    public EmployeeDto() {
    }

    public EmployeeDto(Long id, String name, String surname, String email, String phone, String username, String password, Department department, Title title) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.department = department;
        this.title = title;
    }

    public static EmployeeDto from(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setSurname(employee.getSurname());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPhone(employee.getPhone());
        employeeDto.setUsername(employee.getUsername());
        employeeDto.setPassword(employee.getPassword());
        employeeDto.setDepartment(employee.getDepartment());
        employeeDto.setTitle(employee.getTitle());
        return employeeDto;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", department=" + department +
                ", title=" + title +
                '}';
    }
}
