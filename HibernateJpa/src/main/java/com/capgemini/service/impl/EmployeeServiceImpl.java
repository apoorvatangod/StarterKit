package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.model.Employee;
import com.capgemini.repository.EmployeeRepository;
import com.capgemini.service.EmployeeService;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> findEmployeeByName(String firstName, String lastName) {
		return employeeRepository.findEmployeeByName(firstName, lastName);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		//employeeRepository.updateEmployee(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getPesel(), employee.getBirthDate());
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> findEmployeeById(Long id) {
		return employeeRepository.findEmployeeById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public Employee insertEmployee(Employee employee) {
		employee.setId(null);
		//employeeRepository.insertEmployee(firstName, lastName, pesel, birthDate);		
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> findEmployeeByPesel(String pesel) {
		return employeeRepository.findEmployeeByPesel(pesel);
	}

	@Override
	public void deleteEmployee(Employee employee) {
		employeeRepository.delete(employee);
	}

}
