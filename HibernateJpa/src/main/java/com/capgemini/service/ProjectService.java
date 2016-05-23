package com.capgemini.service;

import java.util.List;

import com.capgemini.model.Employee;
import com.capgemini.model.EmployeeEngagement;
import com.capgemini.model.Project;
import com.capgemini.model.Role;

public interface ProjectService {

	
	public List<Project> findProjectByName(String name);
	
	public List<Project> findProjectById(Long id);
	
	public Project insertProject(Project project);
	
	public void deleteProject(Project project);
	
	public List<EmployeeEngagement> findEmployeeEngagement(Project project, Employee employee, Role role);
	
	public EmployeeEngagement insertEngagement(EmployeeEngagement engagement);
	
	public EmployeeEngagement removeEmployeeFromProject(Project project, Employee employee, Role role);

}
