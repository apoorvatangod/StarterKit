package com.capgemini.service;

import java.util.List;

import com.capgemini.model.Employee;;

public interface EmployeeService {

	//public void createEmployee(String name, String surname, String pesel, Date dateOfBirth);

	//public void updateEmployee(String name, String surname, String pesel, Date dateOfBirth, int id);
	
	public List<Employee> findEmployeeByName(String firstName, String lastName);
	
	public List<Employee> findEmployeeById(Long id);
	
	public List<Employee> findEmployeeByPesel(String pesel);
	
	public Employee updateEmployee(Employee employee);
	
	public void deleteEmployee(Employee employee);
	
	public Employee insertEmployee(Employee employee);

}
