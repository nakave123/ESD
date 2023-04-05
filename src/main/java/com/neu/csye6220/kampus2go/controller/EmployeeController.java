package com.neu.csye6220.kampus2go.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RestController;

import com.neu.csye6220.kampus2go.dao.EmployeeDAO;
import com.neu.csye6220.kampus2go.dao.UserDAO;
import com.neu.csye6220.kampus2go.model.Employee;
import com.neu.csye6220.kampus2go.model.User;
import com.neu.csye6220.kampus2go.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	@Autowired
	EmployeeDAO employeeDAO;
	
	@Autowired
	UserDAO userDAO;

	@PostMapping(value = "/new-emp/", consumes={"application/json"})
    @ResponseBody
    public void createNewUser(@RequestBody Employee userForm) throws Exception {
        
        empService.createUser(userForm);
    }
	
	@GetMapping(value = "/getUser")
    @ResponseBody
    public User getUser() throws Exception {
        
		return userDAO.findByUsername("Pratik");
    }
	
	@GetMapping(value = "/getEmployees")
    @ResponseBody
    public List<Employee> getAllEmployees() throws Exception {
        
		return employeeDAO.getAllEmployees();
    }
	
	@PutMapping(value = "/update-emp", consumes={"application/json"})
	public void updateEmployee(@RequestBody Employee userForm) {
			empService.updateEmployee(userForm);
	}
}
