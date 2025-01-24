package com.constructions.nilaya.models;

import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "masterdata")
public class MasterData {

    private String id; // For the "_id" field from MongoDB, you can use @Id annotation if you wish to use it for persistence
    private List<String> typeOfLabour;
    private List<String> engineerList;
    private List<String> projectList;
    private List<String> typeOfMaterial;
    private List<String> unitInfo;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getTypeOfLabour() {
        return typeOfLabour;
    }

    public void setTypeOfLabour(List<String> typeOfLabour) {
        this.typeOfLabour = typeOfLabour;
    }

    public List<String> getEngineerList() {
        return engineerList;
    }

    public void setEngineerList(List<String> engineerList) {
        this.engineerList = engineerList;
    }

    public List<String> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<String> projectList) {
        this.projectList = projectList;
    }

    public List<String> getTypeOfMaterial() {
        return typeOfMaterial;
    }

    public void setTypeOfMaterial(List<String> typeOfMaterial) {
        this.typeOfMaterial = typeOfMaterial;
    }

    public List<String> getUnitInfo() {
        return unitInfo;
    }

    public void setUnitInfo(List<String> unitInfo) {
        this.unitInfo = unitInfo;
    }
}
