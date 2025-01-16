package com.constructions.nilaya.repositories;

import com.constructions.nilaya.models.LabourManagement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabourManagementRepository extends MongoRepository<LabourManagement, String> {

	List<LabourManagement> findByCreatedDateBetween(LocalDateTime atStartOfDay, LocalDateTime atTime);

	List<LabourManagement> findByProjectNameIgnoreCaseAndCreatedDateBetween(String projectName, LocalDateTime startDateTime,
			LocalDateTime endDateTime);

	List<LabourManagement> findByEngineerNameIgnoreCase(String engineerName);
}
