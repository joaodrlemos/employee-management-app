package com.lemos.employeeManagementbackend.service;

import java.util.List;

import com.lemos.employeeManagementbackend.model.Employee;

public interface EmployeeService {
	Employee saveEmployee(Employee employee);
	List<Employee> getAllEmployees();
	Employee getEmployeeById(Long id);
	Employee updateEmployee(Employee employee, Long id);
	void deleteEmployee(Long id);
}
