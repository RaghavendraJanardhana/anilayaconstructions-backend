package com.constructions.nilaya.services;

import com.constructions.nilaya.models.LabourManagement;
import com.constructions.nilaya.repositories.LabourManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabourManagementService {

    @Autowired
    private LabourManagementRepository repository;

    public LabourManagement createLabourManagement(LabourManagement labourManagement) {
        return repository.save(labourManagement);
    }

    public List<LabourManagement> getAllLabourManagement() {
        return repository.findAll();
    }

    public Optional<LabourManagement> getLabourManagementById(String id) {
        return repository.findById(id);
    }

    public LabourManagement updateLabourManagement(String id, LabourManagement updatedData) {
        Optional<LabourManagement> existing = repository.findById(id);

        if (existing.isPresent()) {
            LabourManagement labourManagement = existing.get();
            labourManagement.setProjectName(updatedData.getProjectName());
            labourManagement.setEngineerName(updatedData.getEngineerName());
            labourManagement.setCreatedDate(updatedData.getCreatedDate());
            labourManagement.setCreatedBy(updatedData.getCreatedBy());
            labourManagement.setTypeOfLabour(updatedData.getTypeOfLabour());
            labourManagement.setLabourCount(updatedData.getLabourCount());
            return repository.save(labourManagement);
        } else {
            throw new RuntimeException("Labour Management record not found with ID: " + id);
        }
    }

    public void deleteLabourManagement(String id) {
        repository.deleteById(id);
    }
}
