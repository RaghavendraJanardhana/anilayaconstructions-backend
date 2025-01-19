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

import com.constructions.nilaya.models.MaterialsManagement;
import com.constructions.nilaya.repositories.MaterialsManagementRepository;

@Service
public class MaterialsManagementService {

    @Autowired
    private MaterialsManagementRepository repository;

    public MaterialsManagement createMaterialsManagement(MaterialsManagement materialsManagement) {
        return repository.save(materialsManagement);
    }

    public List<MaterialsManagement> getAllMaterialsManagement() {
        return repository.findAll();
    }

    public Optional<MaterialsManagement> getMaterialsManagementById(String id) {
        return repository.findById(id);
    }

    public MaterialsManagement updateMaterialsManagement(String id, MaterialsManagement updatedData) {
        Optional<MaterialsManagement> existing = repository.findById(id);

        if (existing.isPresent()) {
        	MaterialsManagement materialsManagement = existing.get();
        	materialsManagement.setProjectName(updatedData.getProjectName());
        	materialsManagement.setEngineerName(updatedData.getEngineerName());
        	materialsManagement.setCreatedBy(updatedData.getCreatedBy());
        	materialsManagement.setTypeOfMaterial(updatedData.getTypeOfMaterial());
        	materialsManagement.setQuantity(updatedData.getQuantity());
        	materialsManagement.setUnit(updatedData.getUnit());
        	materialsManagement.setAmount(updatedData.getAmount());

            return repository.save(materialsManagement);
        } else {
            throw new RuntimeException("Materials Management record not found with ID: " + id);
        }
    }

    public void deleteMaterialsManagement(String id) {
        repository.deleteById(id);
    }

    public List<MaterialsManagement> findRecordsBetweenDates(LocalDate startDate, LocalDate endDate) {
        // Filter records between startDate and endDate
        return repository.findByCreatedDateBetween(startDate.atStartOfDay(), endDate.atTime(23, 59, 59));
    }

    
    public List<MaterialsManagement> findRecordsByProjectAndDates(String projectName, LocalDate startDate, LocalDate endDate) {
        // Convert LocalDate to LocalDateTime (start of day, end of day)
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);
        return repository.findByProjectNameIgnoreCaseAndCreatedDateBetween(projectName, startDateTime, endDateTime);
    }
    public ByteArrayInputStream generateCSV(List<MaterialsManagement> records) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(out);

        // Write CSV header
        writer.println("ID,Project Name,Engineer Name,Created Date,Created By,Type Of Material,Quantity,Unit,Amount");

        // Write each record
        for (MaterialsManagement record : records) {
            writer.println(String.format(
                    "%s,%s,%s,%s,%s,%s,%s,%s,%s",
                    record.getId(),
                    record.getProjectName(),
                    record.getEngineerName(),
                    record.getCreatedDate(),
                    record.getCreatedBy(),
                    record.getTypeOfMaterial(),
                    record.getQuantity(),
                    record.getUnit(),
                    record.getAmount()
            ));
        }

        writer.flush();
        return new ByteArrayInputStream(out.toByteArray());
    }
    
    
    public ByteArrayInputStream generateExcel(List<MaterialsManagement> records) {
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
        header.createCell(5).setCellValue("Type Of Material");
        header.createCell(6).setCellValue("Quantity");
        header.createCell(7).setCellValue("Unit");
        header.createCell(8).setCellValue("Amount");

        // Create data rows
        int rowNum = 1;  // Start from the second row (index 1)
        for (MaterialsManagement record : records) {
            Row row = sheet.createRow(rowNum++);
            
            row.createCell(0).setCellValue(record.getId());
            row.createCell(1).setCellValue(record.getProjectName());
            row.createCell(2).setCellValue(record.getEngineerName());
            row.createCell(3).setCellValue(record.getCreatedDate().toString()); // Customize if needed
            row.createCell(4).setCellValue(record.getCreatedBy());
            row.createCell(5).setCellValue(record.getTypeOfMaterial());
            row.createCell(6).setCellValue(record.getQuantity());
            row.createCell(7).setCellValue(record.getUnit());
            row.createCell(8).setCellValue( record.getAmount());
           
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

    public List<MaterialsManagement> findRecordsByEngineer(String engineerName) {
        // Query the repository to find records by engineerName
        return repository.findByEngineerNameIgnoreCase(engineerName);
    }
}
