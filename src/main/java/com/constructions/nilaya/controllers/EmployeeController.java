package com.constructions.nilaya.controllers;

import com.constructions.nilaya.models.Employees;
import com.constructions.nilaya.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Create employee
    @PostMapping
    public ResponseEntity<Employees> createEmployee(@RequestBody Employees employee) {
        Employees savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    // Get employees by name
    @GetMapping("/search")
    public ResponseEntity<List<Employees>> getEmployeesByName(@RequestParam String name) {
        List<Employees> employees = employeeService.findEmployeeByName(name);
        return ResponseEntity.ok(employees);
    }

    // Get all employees
    @GetMapping
    public ResponseEntity<List<Employees>> getAllEmployees() {
        List<Employees> employees = employeeService.findAllEmployees();
        return ResponseEntity.ok(employees);
    }
}
