package com.constructions.nilaya.repositories;

import com.constructions.nilaya.models.Employees; // Import your Employee model

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employees, String> {
	
	List<Employees> findByNameIgnoreCase(String name); 
	List<Employees> findByNameIgnoreCaseContaining(String partialName);


}
