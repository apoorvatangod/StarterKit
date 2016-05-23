package com.capgemini.repository;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.model.Employee;

@Repository
@Transactional(readOnly = true)
public class EmployeeEntityManagerRepositoryImpl implements EmployeeEntityMangerReporistory {

	@PersistenceContext
	private EntityManager entityManager;
	@Override
	@Modifying
	@Transactional(readOnly = false)
	public void insertEmployee(String firstName, String lastName, String pesel, Date birthDate) {
		Employee employee = new Employee(null, birthDate, firstName, lastName, pesel, null);
		entityManager.persist(employee);
		entityManager.close();
	}

}
