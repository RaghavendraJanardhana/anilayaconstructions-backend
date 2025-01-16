package com.constructions.nilaya.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constructions.nilaya.models.LabourManagement;
import com.constructions.nilaya.repositories.LabourManagementRepository;

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

    public List<LabourManagement> findRecordsBetweenDates(LocalDate startDate, LocalDate endDate) {
        // Filter records between startDate and endDate
        return repository.findByCreatedDateBetween(startDate.atStartOfDay(), endDate.atTime(23, 59, 59));
    }

    
    public List<LabourManagement> findRecordsByProjectAndDates(String projectName, LocalDate startDate, LocalDate endDate) {
        // Convert LocalDate to LocalDateTime (start of day, end of day)
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);
        return repository.findByProjectNameIgnoreCaseAndCreatedDateBetween(projectName, startDateTime, endDateTime);
    }
    public ByteArrayInputStream generateCSV(List<LabourManagement> records) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(out);

        // Write CSV header
        writer.println("ID,Project Name,Engineer Name,Created Date,Created By,Type Of Labour,Labour Count");

        // Write each record
        for (LabourManagement record : records) {
            writer.println(String.format(
                    "%s,%s,%s,%s,%s,%s,%d",
                    record.getId(),
                    record.getProjectName(),
                    record.getEngineerName(),
                    record.getCreatedDate(),
                    record.getCreatedBy(),
                    record.getTypeOfLabour(),
                    record.getLabourCount()
            ));
        }

        writer.flush();
        return new ByteArrayInputStream(out.toByteArray());
    }
    
    
    public ByteArrayInputStream generateExcel(List<LabourManagement> records) {
        // Create a Workbook (Excel file)
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Labour Management");

        // Create a header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Project Name");
        header.createCell(2).setCellValue("Engineer Name");
        header.createCell(3).setCellValue("Created Date");
        header.createCell(4).setCellValue("Created By");
        header.createCell(5).setCellValue("Type Of Labour");
        header.createCell(6).setCellValue("Labour Count");

        // Create data rows
        int rowNum = 1;  // Start from the second row (index 1)
        for (LabourManagement record : records) {
            Row row = sheet.createRow(rowNum++);
            
            row.createCell(0).setCellValue(record.getId());
            row.createCell(1).setCellValue(record.getProjectName());
            row.createCell(2).setCellValue(record.getEngineerName());
            row.createCell(3).setCellValue(record.getCreatedDate().toString()); // Customize if needed
            row.createCell(4).setCellValue(record.getCreatedBy());
            row.createCell(5).setCellValue(record.getTypeOfLabour());
            row.createCell(6).setCellValue(record.getLabourCount());
        }

        // Resize columns to fit the content
        for (int i = 0; i < 7; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write Excel content to a ByteArrayOutputStream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            workbook.write(out);
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();  // Handle exception
        }

        // Return the Excel content as ByteArrayInputStream
        return new ByteArrayInputStream(out.toByteArray());
    }

    public List<LabourManagement> findRecordsByEngineer(String engineerName) {
        // Query the repository to find records by engineerName
        return repository.findByEngineerNameIgnoreCase(engineerName);
    }
}
