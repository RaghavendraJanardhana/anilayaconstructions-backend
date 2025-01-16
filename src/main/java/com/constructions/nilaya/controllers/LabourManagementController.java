package com.constructions.nilaya.controllers;

import com.constructions.nilaya.models.LabourManagement;
import com.constructions.nilaya.services.LabourManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/labourmanagement")
public class LabourManagementController {

    @Autowired
    private LabourManagementService service;

    @PostMapping
    public ResponseEntity<LabourManagement> createLabourManagement(@RequestBody LabourManagement labourManagement) {
        LabourManagement created = service.createLabourManagement(labourManagement);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<LabourManagement>> getAllLabourManagement() {
        List<LabourManagement> allRecords = service.getAllLabourManagement();
        return ResponseEntity.ok(allRecords);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LabourManagement> getLabourManagementById(@PathVariable String id) {
        return service.getLabourManagementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LabourManagement> updateLabourManagement(@PathVariable String id, @RequestBody LabourManagement updatedData) {
        LabourManagement updated = service.updateLabourManagement(id, updatedData);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLabourManagement(@PathVariable String id) {
        service.deleteLabourManagement(id);
        return ResponseEntity.noContent().build();
    }
}
