package com.daniel.employeemanager.service;

import com.daniel.employeemanager.exception.EmployeeAlreadyAssignedDepartmentException;
import com.daniel.employeemanager.exception.EmployeeAlreadyAssignedTitleException;
import com.daniel.employeemanager.exception.TitleNotFoundException;
import com.daniel.employeemanager.model.Employee;
import com.daniel.employeemanager.model.Title;
import com.daniel.employeemanager.repo.TitleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class TitleService {
    private final TitleRepo titleRepo;
    private final EmployeeService employeeService;

    @Autowired
    public TitleService(TitleRepo titleRepo, EmployeeService employeeService) {
        this.titleRepo = titleRepo;
        this.employeeService = employeeService;
    }

    public List<Title> findAllTitles() {
        return titleRepo.findAll();
    }

    public Title findTitleById(Long id) {
        return titleRepo.findById(id).orElseThrow(() -> new TitleNotFoundException(id));
    }

    public Title addTitle(Title title) {
        return titleRepo.save(title);
    }

    @Transactional
    public Title updateTitle(Long id, Title title) {
        Title updatedTitle = findTitleById(id);
        updatedTitle.setName(title.getName());
        return updatedTitle;
    }

    public Title deleteTitle(Long id) {
        Title title = findTitleById(id);
        titleRepo.delete(title);
        return title;
    }

    @Transactional
    public Title addEmployeeToTitle(Long titleId, Long employeeId) {
        Title title = findTitleById(titleId);
        Employee employee = employeeService.findEmployeeById(employeeId);
        if (Objects.nonNull(employee.getTitle())) {
            throw new EmployeeAlreadyAssignedTitleException(employeeId, employee.getTitle().getId());
        }
        title.addEmployee(employee);
        employee.setTitle(title);
        return title;
    }

    @Transactional
    public Title removeEmployeeFromTitle(Long titleId, Long employeeId) {
        Title title = findTitleById(titleId);
        Employee employee = employeeService.findEmployeeById(employeeId);
        title.removeEmployee(employee);
        return title;
    }
}
