package com.lemos.employeeManagementbackend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemos.employeeManagementbackend.exception.ResourceNotFoundException;
import com.lemos.employeeManagementbackend.model.Employee;
import com.lemos.employeeManagementbackend.repository.EmployeeRepository;
import com.lemos.employeeManagementbackend.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
		
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(Long id) {
//		Optional<Employee> employee = employeeRepository.findById(id);
//		if(employee.isPresent())
//			return employee.get();
//		else
//			throw new ResourceNotFoundException("Employee", "id", id);

	return employeeRepository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Employee", "id", id));
	}

	@Override
	public Employee updateEmployee(Employee employee, Long id) {
		
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() ->
				new ResourceNotFoundException("Employee", "id", id));
		
		if (employee.getFirstName() != null) 
			existingEmployee.setFirstName(employee.getFirstName());
		if(employee.getLastName() != null)
			existingEmployee.setLastName(employee.getLastName());
		if(employee.getEmail() != null)
			existingEmployee.setEmail(employee.getEmail());
		
		return employeeRepository.save(existingEmployee);
		
	}

	@Override
	public void deleteEmployee(Long id) {
		employeeRepository.findById(id).orElseThrow(() ->
				new ResourceNotFoundException("Employee", "id", id));
		
		employeeRepository.deleteById(id);
	}
}
