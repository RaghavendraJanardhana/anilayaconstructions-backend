package com.constructions.nilaya.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "labourmanagement")
public class LabourManagement {

    @Id
    private String id;
    private String projectName;
    private String engineerName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss a")
    @CreatedDate
    private LocalDateTime createdDate;
    private String createdBy;
    private String typeOfLabour;
    private int labourCount;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getEngineerName() {
        return engineerName;
    }

    public void setEngineerName(String engineerName) {
        this.engineerName = engineerName;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getTypeOfLabour() {
        return typeOfLabour;
    }

    public void setTypeOfLabour(String typeOfLabour) {
        this.typeOfLabour = typeOfLabour;
    }

    public int getLabourCount() {
        return labourCount;
    }

    public void setLabourCount(int labourCount) {
        this.labourCount = labourCount;
    }

}
