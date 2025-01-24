package com.constructions.nilaya.controllers;

import com.constructions.nilaya.models.MasterData;
import com.constructions.nilaya.services.MasterDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/masterdata")
public class MasterDataController {

    private final MasterDataService masterDataService;

    @Autowired
    public MasterDataController(MasterDataService masterDataService) {
        this.masterDataService = masterDataService;
    }

    // Get all MasterData
    @GetMapping
    public ResponseEntity<List<MasterData>> getAllMasterData() {
        List<MasterData> masterDataList = masterDataService.getAllMasterData();
        return ResponseEntity.ok(masterDataList);
    }

    // Get MasterData by ID
    @GetMapping("/{id}")
    public ResponseEntity<MasterData> getMasterDataById(@PathVariable String id) {
        return masterDataService.getMasterDataById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Save new MasterData
    @PostMapping
    public ResponseEntity<MasterData> createMasterData(@RequestBody MasterData masterData) {
        MasterData savedMasterData = masterDataService.saveMasterData(masterData);
        return ResponseEntity.ok(savedMasterData);
    }

    // Update MasterData
    @PutMapping("/{id}")
    public ResponseEntity<MasterData> updateMasterData(@PathVariable String id, @RequestBody MasterData updatedMasterData) {
        return masterDataService.getMasterDataById(id)
                .map(existingData -> {
                    updatedMasterData.setTypeOfLabour(updatedMasterData.getTypeOfLabour() != null 
                        ? updatedMasterData.getTypeOfLabour() 
                        : existingData.getTypeOfLabour());
                    updatedMasterData.setEngineerList(updatedMasterData.getEngineerList() != null 
                        ? updatedMasterData.getEngineerList() 
                        : existingData.getEngineerList());
                    updatedMasterData.setProjectList(updatedMasterData.getProjectList() != null 
                        ? updatedMasterData.getProjectList() 
                        : existingData.getProjectList());
                    return ResponseEntity.ok(masterDataService.saveMasterData(updatedMasterData));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete MasterData by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMasterData(@PathVariable String id) {
        masterDataService.deleteMasterDataById(id);
        return ResponseEntity.noContent().build();
    }
}
