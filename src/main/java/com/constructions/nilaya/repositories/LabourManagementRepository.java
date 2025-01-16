package com.constructions.nilaya.repositories;

import com.constructions.nilaya.models.LabourManagement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabourManagementRepository extends MongoRepository<LabourManagement, String> {
}
