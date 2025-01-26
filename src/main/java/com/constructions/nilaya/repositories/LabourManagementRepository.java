package com.constructions.nilaya.repositories;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.constructions.nilaya.models.LabourManagement;

@Repository
public interface LabourManagementRepository extends MongoRepository<LabourManagement, String> {

	List<LabourManagement> findByCreatedDateBetween(LocalDateTime atStartOfDay, LocalDateTime atTime);

	List<LabourManagement> findByProjectNameIgnoreCaseAndCreatedDateBetween(String projectName, LocalDateTime startDateTime,
			LocalDateTime endDateTime);

	List<LabourManagement> findByEngineerNameIgnoreCase(String engineerName);
	
	List<LabourManagement> findRecordsByProjectNameOrEngineerNameOrCreatedDateBetween(
	        String projectName, 
	        String engineerName, 
	        Date startDate, 
	        Date endDate);
	}

