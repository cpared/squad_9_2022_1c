package com.support.aninfosupportmodule.controller;

import com.support.aninfosupportmodule.dto.Employee;
import com.support.aninfosupportmodule.rest.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/support/employee")
@CrossOrigin
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping()
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }
}
