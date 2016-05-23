package com.capgemini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.model.EmployeeEngagement;

@Repository
@Transactional(readOnly = true)
public interface EmployeeEngagementRepository extends JpaRepository<EmployeeEngagement, Long> {

	@Query("SELECT ee FROM EmployeeEngagement ee JOIN FETCH ee.project p JOIN FETCH ee.role r JOIN FETCH ee.employee e"
			+ " WHERE p.name LIKE :projectName AND e.pesel LIKE :employeePesel AND r.name LIKE :roleName")
	List<EmployeeEngagement> findEmployeeEngagement(@Param("projectName") String projectName,
			@Param("employeePesel") String employeePesel, @Param("roleName") String roleName);

}
