package com.capgemini.repository;

import java.util.Date;

public interface EmployeeEntityMangerReporistory {

	 void insertEmployee(String firstName, String lastName, String pesel, Date birthDate);
}
