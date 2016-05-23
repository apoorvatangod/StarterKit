package com.capgemini.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the projects database table.
 * 
 */
@Entity
@Table(name="projects")
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="realisation")
	private String realisation;

	//uni-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="manager_id")
	private Employee employee;

	public Project() {
	}

	
	public Project(Long id, String name, String realisation, Employee employee) {
		this.id = id;
		this.name = name;
		this.realisation = realisation;
		this.employee = employee;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRealisation() {
		return this.realisation;
	}

	public void setRealisation(String realisation) {
		this.realisation = realisation;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}