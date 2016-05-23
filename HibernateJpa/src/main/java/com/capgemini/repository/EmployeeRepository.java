package com.capgemini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.model.Employee;

@Repository
@Transactional(readOnly = true)
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	
	/* Dziala, inny sposob
	@Modifying
	@Transactional(readOnly=false)
	@Query("UPDATE Employee e SET e.firstName = :firstName, e.lastName = :lastName, e.pesel = :pesel, e.birthDate = :birthDate WHERE e.id = :id")
	void updateEmployee(@Param("id") Long id, @Param("firstName") String firstName, @Param("lastName") String lastName, 
			@Param("pesel") String pesel, @Param("birthDate")Date birthDate);*/
	
	@Query("SELECT e FROM Employee e WHERE UPPER(e.firstName) like CONCAT(UPPER(:firstName), '%') and UPPER(e.lastName) like CONCAT('%', UPPER(:lastName), '%')")
	List<Employee> findEmployeeByName( @Param("firstName") String firstName, @Param("lastName") String lastName);

	@Query("SELECT e FROM Employee e WHERE e.id = :id")
	List<Employee> findEmployeeById(@Param("id") Long id);
	
	@Query("SELECT e FROM Employee e WHERE e.pesel = :pesel")
	List<Employee> findEmployeeByPesel(@Param("pesel") String pesel);
	
}
