package com.constructions.nilaya.controllers;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.constructions.nilaya.models.MaterialsManagement;
import com.constructions.nilaya.services.MaterialsManagementService;

@RestController
@RequestMapping("/api/materialsmanagement")
public class MaterialsManagementController {

    @Autowired
    private MaterialsManagementService service;

    @PostMapping
    public ResponseEntity<MaterialsManagement> createMaterialsManagement(@RequestBody MaterialsManagement materialsManagement) {
    	MaterialsManagement created = service.createMaterialsManagement(materialsManagement);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<MaterialsManagement>> getAllMaterialsManagement() {
        List<MaterialsManagement> allRecords = service.getAllMaterialsManagement();
        return ResponseEntity.ok(allRecords);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialsManagement> getMaterialsManagementById(@PathVariable String id) {
        return service.getMaterialsManagementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialsManagement> updateMaterialsManagement(@PathVariable String id, @RequestBody MaterialsManagement updatedData) {
    	MaterialsManagement updated = service.updateMaterialsManagement(id, updatedData);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterialsManagement(@PathVariable String id) {
        service.deleteMaterialsManagement(id);
        return ResponseEntity.noContent().build();
    }
    
    
    @GetMapping("/exportByStartEndDate")
    public ResponseEntity<byte[]> exportByStartEndDateRecordsToExcel(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        // Fetch records within the given date range
        List<MaterialsManagement> filteredRecords = service.findRecordsBetweenDates(startDate, endDate);

        // Generate Excel content (use the Excel generation method)
        ByteArrayInputStream excelContent = service.generateExcel(filteredRecords);

        // Set headers for Excel file download
        String fileName = "material_management_" + startDate + "_to_" + endDate + ".xlsx";  // Excel extension
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Return the Excel file as response
        return ResponseEntity.ok()
                .headers(headers)
                .body(excelContent.readAllBytes());
    }
    
    @GetMapping("/getByStartEndDate")
    public ResponseEntity<List<MaterialsManagement>> getByStartEndDateRecords(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        // Fetch records within the given date range
        List<MaterialsManagement> filteredRecords = service.findRecordsBetweenDates(startDate, endDate);
        return ResponseEntity.ok()               
                .body(filteredRecords);
    }
    
    @GetMapping("/exportByStartEndDateProjectName")
    public ResponseEntity<byte[]> exportByStartEndDateProjectNameRecordsToExcel( @RequestParam("projectName") String projectName, // Added projectName filter
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        // Fetch records within the given date range
        List<MaterialsManagement> filteredRecords = service.findRecordsByProjectAndDates(projectName, startDate, endDate);

        // Generate Excel content (use the Excel generation method)
        ByteArrayInputStream excelContent = service.generateExcel(filteredRecords);

        // Set headers for Excel file download
        String fileName = "material_management_" + startDate + "_to_" + endDate + ".xlsx";  // Excel extension
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Return the Excel file as response
        return ResponseEntity.ok()
                .headers(headers)
                .body(excelContent.readAllBytes());
    }
    
    @GetMapping("/getByStartEndDateProjectName")
    public ResponseEntity<List<MaterialsManagement>> getByStartEndDateProjectNameRecords( @RequestParam("projectName") String projectName, // Added projectName filter
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        // Fetch records within the given date range
        List<MaterialsManagement> filteredRecords = service.findRecordsByProjectAndDates(projectName, startDate, endDate);
        return ResponseEntity.ok()
                .body(filteredRecords);
    }
    
    @GetMapping("/exportByEngineerName")
    public ResponseEntity<byte[]> exportByEngineerNameRecordsToExcel(
            @RequestParam("engineerName") String engineerName) { 
    	// Only engineerName filter now
        // Fetch records for the given engineerName
        List<MaterialsManagement> filteredRecords = service.findRecordsByEngineer(engineerName);

        // Generate Excel content (use the Excel generation method)
        ByteArrayInputStream excelContent = service.generateExcel(filteredRecords);

        // Set headers for Excel file download
        String fileName = "material_management_" + engineerName + ".xlsx";  // Excel extension
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Return the Excel file as response
        return ResponseEntity.ok()
                .headers(headers)
                .body(excelContent.readAllBytes());
    }
    @GetMapping("/getByEngineerName")
    public ResponseEntity<List<MaterialsManagement>> getByEngineerNameRecords(
            @RequestParam("engineerName") String engineerName) { 
    	// Only engineerName filter now
        // Fetch records for the given engineerName
        List<MaterialsManagement> filteredRecords = service.findRecordsByEngineer(engineerName);
        // Return the Excel file as response
        return ResponseEntity.ok()
                .body(filteredRecords);
    }

    

}

