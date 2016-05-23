package com.capgemini.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.enumeration.ProjectRealisationEnum;
import com.capgemini.model.Department;
import com.capgemini.model.Employee;
import com.capgemini.model.EmployeeEngagement;
import com.capgemini.model.Project;
import com.capgemini.model.Role;
import com.capgemini.service.ProjectService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ProjectServiceTest {

	@Autowired
	private ProjectService projectService;

	@Before
	public void init(){
		if(!projectService.findProjectByName("Project 13").isEmpty()){
			projectService.deleteProject(projectService.findProjectByName("Project 13").get(0));
		}
	}
	
	@Test
	public void testShouldFindProjectByName() {
		// given
		
		// when
		List<Project> projects = projectService.findProjectByName("Project 7");
		int recordsFound = projects.size();
		// then
		assertEquals(1, recordsFound);
	}
	
	@Test
	public void testShouldFindProjectById() {
		// given
		
		// when
		List<Project> projects = projectService.findProjectById(2L);
		long idOfFound = projects.get(0).getId();
		// then
		assertEquals(2L,idOfFound);
	}
	

	@Test
	public void testShouldInsertProject() {
		// given
		
		// when
		projectService.insertProject(new Project(null, "Project 13", ProjectRealisationEnum.EXTERNAL.toString(), null));

		List<Project> projectsFound = projectService.findProjectByName("Project 13");
		// then
		assertFalse(projectsFound.isEmpty());

	}
	
	@Test
	public void testShouldFindEmployeeEngagement() {
		// given
		Employee employee = new Employee(null, new Date(), "Jas", "Fasola", "00000000005", null);
		Project project = new Project(null, "Project 1", ProjectRealisationEnum.INTERNAL.toString(), null);
		Role role = new Role(null, "PL");
		// when
		List<EmployeeEngagement> engagemets = projectService.findEmployeeEngagement(project, employee, role);
		boolean isEngagementFound = !engagemets.isEmpty();
		// then
		assertTrue(isEngagementFound);
	}
	
	@Test
	@Ignore
	public void testShouldInsertEmployeeEngagement() {
		// given
		Department department = new Department(3L, "PM Office");
		Employee employee = new Employee(6L, new Date(87084000000L), "Anna", "Bil", "00000000005", department);
		Project project = new Project(139L, "Project 12", ProjectRealisationEnum.EXTERNAL.toString(), null);
		Role role = new Role(1L, "PL");
		// when
		EmployeeEngagement engagement = projectService.insertEngagement(new EmployeeEngagement(null, new Date(), null, BigDecimal.valueOf(1000), employee, project, role));

		// then
		assertEquals("00000000005", engagement.getEmployee().getPesel());
	}
	
	@Test
	@Ignore
	public void testShouldRemoveEmployeeFromProject() {
		// given
		Department department = new Department(3L, "PM Office");
		Employee employee = new Employee(6L, new Date(87084000000L), "Anna", "Bil", "00000000005", department);
		Project project = new Project(139L, "Project 12", ProjectRealisationEnum.EXTERNAL.toString(), null);
		Role role = new Role(1L, "PL");
		// when
		EmployeeEngagement removedEngagement = projectService.removeEmployeeFromProject(project, employee, role);

		// then
		assertTrue(removedEngagement != null);
	}
}
