package com.capgemini.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the employee_engagement database table.
 * 
 */
@Entity
@Table(name="employee_engagement")
@NamedQuery(name="EmployeeEngagement.findAll", query="SELECT e FROM EmployeeEngagement e")
public class EmployeeEngagement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name="date_from")
	private Date dateFrom;

	@Temporal(TemporalType.DATE)
	@Column(name="date_to")
	private Date dateTo;
	
	@Column(name="salary")
	private BigDecimal salary;

	//uni-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;

	//uni-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="project_id")
	private Project project;

	//uni-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;

	public EmployeeEngagement() {
	}

	
	public EmployeeEngagement(Long id, Date dateFrom, Date dateTo, BigDecimal salary, Employee employee,
			Project project, Role role) {
		this.id = id;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.salary = salary;
		this.employee = employee;
		this.project = project;
		this.role = role;
	}


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateFrom() {
		return this.dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return this.dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public BigDecimal getSalary() {
		return this.salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}