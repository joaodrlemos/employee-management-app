package com.lemos.employeeManagementbackend.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lemos.employeeManagementbackend.model.Employee;
import com.lemos.employeeManagementbackend.service.EmployeeService;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	// Does'nt need field injection(@Autowired) because of the single constructor
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	// create employee REST API
	@RequestMapping(path="/employee",method=RequestMethod.POST)
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}
	
	// get all employees REST API
	@RequestMapping(path="/employees",method=RequestMethod.GET)
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}

	// get employee by id
	@RequestMapping(path="/employee/{id}",method=RequestMethod.GET)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long id){
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(id),HttpStatus.OK);
		
	}
	
	// update employee
	@RequestMapping(path="/employee/update/{id}",method=RequestMethod.PATCH)
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
	}
	
	// delete employee
	@RequestMapping(path="/employee/delete/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id){
		return new ResponseEntity<String>("Employee deleted successfully!", HttpStatus.OK);
	}
}
