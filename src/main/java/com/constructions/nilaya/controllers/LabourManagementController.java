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

import com.constructions.nilaya.models.LabourManagement;
import com.constructions.nilaya.services.LabourManagementService;

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
    
    
    @GetMapping("/exportByStartEndDate")
    public ResponseEntity<byte[]> exportByStartEndDateRecordsToExcel(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        // Fetch records within the given date range
        List<LabourManagement> filteredRecords = service.findRecordsBetweenDates(startDate, endDate);

        // Generate Excel content (use the Excel generation method)
        ByteArrayInputStream excelContent = service.generateExcel(filteredRecords);

        // Set headers for Excel file download
        String fileName = "labour_management_" + startDate + "_to_" + endDate + ".xlsx";  // Excel extension
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Return the Excel file as response
        return ResponseEntity.ok()
                .headers(headers)
                .body(excelContent.readAllBytes());
    }
    
    @GetMapping("/getByStartEndDate")
    public ResponseEntity<List<LabourManagement>> getByStartEndDateRecords(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        // Fetch records within the given date range
        List<LabourManagement> filteredRecords = service.findRecordsBetweenDates(startDate, endDate);
        return ResponseEntity.ok()               
                .body(filteredRecords);
    }
    
    @GetMapping("/exportByStartEndDateProjectName")
    public ResponseEntity<byte[]> exportByStartEndDateProjectNameRecordsToExcel( @RequestParam("projectName") String projectName, // Added projectName filter
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        // Fetch records within the given date range
        List<LabourManagement> filteredRecords = service.findRecordsByProjectAndDates(projectName, startDate, endDate);

        // Generate Excel content (use the Excel generation method)
        ByteArrayInputStream excelContent = service.generateExcel(filteredRecords);

        // Set headers for Excel file download
        String fileName = "labour_management_" + startDate + "_to_" + endDate + ".xlsx";  // Excel extension
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Return the Excel file as response
        return ResponseEntity.ok()
                .headers(headers)
                .body(excelContent.readAllBytes());
    }
    
    @GetMapping("/getByStartEndDateProjectName")
    public ResponseEntity<List<LabourManagement>> getByStartEndDateProjectNameRecords( @RequestParam("projectName") String projectName, // Added projectName filter
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        // Fetch records within the given date range
        List<LabourManagement> filteredRecords = service.findRecordsByProjectAndDates(projectName, startDate, endDate);
        return ResponseEntity.ok()
                .body(filteredRecords);
    }
    
    @GetMapping("/exportByEngineerName")
    public ResponseEntity<byte[]> exportByEngineerNameRecordsToExcel(
            @RequestParam("engineerName") String engineerName) { 
    	// Only engineerName filter now
        // Fetch records for the given engineerName
        List<LabourManagement> filteredRecords = service.findRecordsByEngineer(engineerName);

        // Generate Excel content (use the Excel generation method)
        ByteArrayInputStream excelContent = service.generateExcel(filteredRecords);

        // Set headers for Excel file download
        String fileName = "labour_management_" + engineerName + ".xlsx";  // Excel extension
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Return the Excel file as response
        return ResponseEntity.ok()
                .headers(headers)
                .body(excelContent.readAllBytes());
    }
    @GetMapping("/getByEngineerName")
    public ResponseEntity<List<LabourManagement>> getByEngineerNameRecords(
            @RequestParam("engineerName") String engineerName) { 
    	// Only engineerName filter now
        // Fetch records for the given engineerName
        List<LabourManagement> filteredRecords = service.findRecordsByEngineer(engineerName);
        // Return the Excel file as response
        return ResponseEntity.ok()
                .body(filteredRecords);
    }

    

}

