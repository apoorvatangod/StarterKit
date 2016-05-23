package com.capgemini.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.model.Employee;
import com.capgemini.model.EmployeeEngagement;
import com.capgemini.model.Project;
import com.capgemini.model.Role;
import com.capgemini.repository.EmployeeEngagementRepository;
import com.capgemini.repository.ProjectRepository;
import com.capgemini.service.ProjectService;

@Service
@Transactional(readOnly = true)
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private EmployeeEngagementRepository employeeEngagementRepository;

	@Override
	public List<Project> findProjectByName(String name) {
		return projectRepository.findProjectByName(name);
	}

	@Override
	public List<Project> findProjectById(Long id) {
	return projectRepository.findProjectById(id);
	}

	@Override
	public Project insertProject(Project project) {
		project.setId(null);
		return projectRepository.save(project);
	}

	@Override
	public List<EmployeeEngagement> findEmployeeEngagement(Project project, Employee employee, Role role) {
		return employeeEngagementRepository.findEmployeeEngagement(project.getName(), employee.getPesel(), role.getName());
	}

	@Override
	public void deleteProject(Project project) {
		projectRepository.delete(project);
	}

	@Override
	public EmployeeEngagement insertEngagement(EmployeeEngagement engagement) {
		engagement.setId(null);
		return employeeEngagementRepository.save(engagement);
	}

	@Override
	public EmployeeEngagement removeEmployeeFromProject(Project project, Employee employee, Role role) {
		 List<EmployeeEngagement> foundEngagements = findEmployeeEngagement(project, employee, role);
		for(EmployeeEngagement engagement : foundEngagements){
			if(engagement.getDateTo() == null){
				engagement.setDateTo(new Date());
				return employeeEngagementRepository.save(engagement);
			}
		}
		return null;
	}

}
