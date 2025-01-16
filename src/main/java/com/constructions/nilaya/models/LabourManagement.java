package com.constructions.nilaya.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "labourmanagement")
public class LabourManagement {

    @Id
    private String id;
    private String projectName;
    private String engineerName;
    private LocalDate createdDate;
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
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
