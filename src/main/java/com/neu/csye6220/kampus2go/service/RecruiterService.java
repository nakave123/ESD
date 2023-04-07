package com.neu.csye6220.kampus2go.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.neu.csye6220.kampus2go.dao.RecruiterDAO;
import com.neu.csye6220.kampus2go.dao.RoleDAO;
import com.neu.csye6220.kampus2go.model.Applicant;
import com.neu.csye6220.kampus2go.model.Recruiter;
import com.neu.csye6220.kampus2go.model.Role;

@Service
public class RecruiterService {
	
	@Autowired
    private RecruiterDAO recruiterDAO;
	
	@Autowired
    private RoleDAO roleDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void create(String username, String password, String roleName) {
		Role role = roleDAO.findByRoleName(roleName);
		if(role==null) {
			Role recruiterRole = new Role();
			recruiterRole.setId(2);
			recruiterRole.setRole("recruiter");
			roleDAO.create(recruiterRole);
		}
		
		Recruiter recruiter = new Recruiter();
		recruiter.setUsername(username);
		recruiter.setPassword(passwordEncoder.encode(password));
		recruiter.setRole(roleDAO.findByRoleName(roleName));
		recruiterDAO.create(recruiter);	
	} 
	public Recruiter findByUsername(String username) {
		return recruiterDAO.findByUsername(username);
		
	}
}
