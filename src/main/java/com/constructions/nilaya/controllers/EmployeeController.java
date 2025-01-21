package com.constructions.nilaya.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.constructions.nilaya.models.Employees;
import com.constructions.nilaya.services.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Create employee
    @PostMapping
    public ResponseEntity<Employees> createEmployee(@Valid @RequestBody Employees employee) {
        Employees savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }
    
    
    @GetMapping("/login")
    public ResponseEntity<?> findEmployeeByCredentials(
            @RequestParam String userName,
            @RequestParam String password) {
    	Employees employee=employeeService.findEmployeeByUserNameAndPassword(userName, password);
    	if (employee == null) {
    		Map<String, String> response = new HashMap<>();
            response.put("message", "No employee found with the provided username and password.");
            response.put("code", "401");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(response);
        }else
        {
        	return  ResponseEntity.ok(employee);
        }
		
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
