package com.constructions.nilaya.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.constructions.nilaya.models.MaterialsManagement;

@Repository
public interface MaterialsManagementRepository extends MongoRepository<MaterialsManagement, String> {

	List<MaterialsManagement> findByCreatedDateBetween(LocalDateTime atStartOfDay, LocalDateTime atTime);

	List<MaterialsManagement> findByProjectNameIgnoreCaseAndCreatedDateBetween(String projectName, LocalDateTime startDateTime,
			LocalDateTime endDateTime);

	List<MaterialsManagement> findByEngineerNameIgnoreCase(String engineerName);
}
