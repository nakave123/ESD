package com.neu.csye6220.kampus2go.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.csye6220.kampus2go.dao.EmployeeDAO;
import com.neu.csye6220.kampus2go.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	EmployeeDAO employeeDAO;
	
	@Transactional
    public void createUser(Employee user) throws Exception {
//		Employee userDB = employeeDAO.getUserByUsername(user.getName());
//        if(userDB!=null) {
//            System.out.println("User already exists");
//            throw new Exception("User already exists");
//        }
        employeeDAO.insertNewUser(user);
    }
	
	@Transactional
	public List<Employee> getAllEmployees(){
		return employeeDAO.getAllEmployees();
	}
	
	@Transactional
	public void updateEmployee(Employee e) {
		employeeDAO.update(e);
	}
}
