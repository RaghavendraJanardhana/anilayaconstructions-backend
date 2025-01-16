package com.constructions.nilaya.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
public class Employees {

    @Id
    private String id;
    private String name;
    private List<String> projects;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getProjects() {
		return projects;
	}
	public void setProjects(List<String> projects) {
		this.projects = projects;
	}

    
}
