package com.capgemini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.model.Project;

@Repository
@Transactional(readOnly = true)
public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	@Query("SELECT p FROM Project p WHERE UPPER(p.name) like CONCAT(UPPER(:name), '%')")
	List<Project> findProjectByName( @Param("name") String name);

	@Query("SELECT p FROM Project p WHERE p.id = :id")
	List<Project> findProjectById(@Param("id") Long id);
	
	
}
