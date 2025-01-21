package com.constructions.nilaya.services;

import com.constructions.nilaya.models.Employees;
import com.constructions.nilaya.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Save employee
    public Employees saveEmployee(Employees employee) {
    	try {
            return employeeRepository.save(employee);
        } catch (DuplicateKeyException ex) {
            throw ex;
        }
    }

    // Find employee by name
    public List<Employees> findEmployeeByName(String name) {
        return employeeRepository.findByNameIgnoreCase(name);
    }

    // Find all employees
    public List<Employees> findAllEmployees() {
        return employeeRepository.findAll();
    }

	public Employees findEmployeeByUserNameAndPassword(String userName, String password) {
		return employeeRepository.findByUserNameAndPassword(userName, password);		
	}
}