package com.daniel.employeemanager.controller;

import com.daniel.employeemanager.model.Department;
import com.daniel.employeemanager.model.Title;
import com.daniel.employeemanager.model.dto.DepartmentDto;
import com.daniel.employeemanager.model.dto.TitleDto;
import com.daniel.employeemanager.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/title")
public class TitleController {
    private final TitleService titleService;

    @Autowired
    public TitleController(TitleService titleService) {
        this.titleService = titleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TitleDto>> getAllTitle() {
        List<Title> titles = titleService.findAllTitles();
        List<TitleDto> titlesDto = titles.stream().map(TitleDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(titlesDto, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<TitleDto> getTitleById(@PathVariable("id") Long id) {
        Title title = titleService.findTitleById(id);
        return new ResponseEntity<>(TitleDto.from(title), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<TitleDto> addTitle(@RequestBody TitleDto titleDto) {
        Title newTitle = titleService.addTitle(Title.from(titleDto));
        return new ResponseEntity<>(TitleDto.from(newTitle), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TitleDto> updateTitle(@PathVariable("id") Long id, @RequestBody TitleDto titleDto) {
        Title updateTitle = titleService.updateTitle(id, Title.from(titleDto));
        return new ResponseEntity<>(TitleDto.from(updateTitle), HttpStatus.OK);
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<TitleDto> deleteTitle(@PathVariable("id") Long id) {
        Title title = titleService.deleteTitle(id);
        return new ResponseEntity<>(TitleDto.from(title), HttpStatus.OK);
    }

    @PostMapping("/add/{titleId}/employee/{employeeId}")
    public ResponseEntity<TitleDto> addEmployeeToTitle(@PathVariable("titleId") Long titleId, @PathVariable("employeeId") Long employeeId) {
        Title title = titleService.addEmployeeToTitle(titleId, employeeId);
        return new ResponseEntity<>(TitleDto.from(title), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{titleId}/employee/{employeeId}")
    public ResponseEntity<TitleDto> removeEmployeeToTitle(@PathVariable("titleId") Long titleId, @PathVariable("employeeId") Long employeeId) {
        Title title = titleService.removeEmployeeFromTitle(titleId, employeeId);
        return new ResponseEntity<>(TitleDto.from(title), HttpStatus.OK);
    }
}