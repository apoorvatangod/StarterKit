package com.capgemini.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.model.Employee;
import com.capgemini.service.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;

	@Before
	public void init(){
		if(!employeeService.findEmployeeByPesel("01002404500").isEmpty()){
			employeeService.deleteEmployee(employeeService.findEmployeeByPesel("01002404500").get(0));
		}
	}
	@Test
	public void testShouldFindTwoEmployees() {
		// given
		
		// when
		List<Employee> emlpoyees = employeeService.findEmployeeByName("J", "");
		int recordsFound = emlpoyees.size();
		// then
		assertEquals(2, recordsFound);
	}
	
	@Test
	public void testShouldFindEmployeeById() {
		// given
		
		// when
		List<Employee> emlpoyees = employeeService.findEmployeeById(2L);
		long idOfFound = emlpoyees.get(0).getId();
		// then
		assertEquals(2L,idOfFound);
	}
	
	@Test
	public void testShouldUpdateEmployee() {
		// given
		
		// when
		Employee employee = employeeService.updateEmployee(new Employee(1L, new Date(), "Lolek", "Bolkowaty", "10000000000", null));

		// then
		assertEquals("Lolek", employee.getFirstName());
		assertEquals("Bolkowaty", employee.getLastName());
		assertEquals("10000000000", employee.getPesel());

		assertTrue(TimeUnit.MILLISECONDS.toDays(new Date().getTime() - employee.getBirthDate().getTime()) < 1);
	}
	@Test
	public void testShouldInsertEmployee() {
		// given
		
		// when
		String pesel = "01002404500";
		Employee foundEmployee = employeeService.insertEmployee(new Employee(null, new Date(), "Hello", "World", pesel, null));

		// then
		assertEquals(pesel, foundEmployee.getPesel());

	}
}
