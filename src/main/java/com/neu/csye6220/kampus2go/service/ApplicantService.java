package com.neu.csye6220.kampus2go.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.neu.csye6220.kampus2go.dao.ApplicantDAO;
import com.neu.csye6220.kampus2go.dao.RoleDAO;
import com.neu.csye6220.kampus2go.model.Applicant;
import com.neu.csye6220.kampus2go.model.Role;

@Service
public class ApplicantService {
	
	@Autowired
    private ApplicantDAO applicantDAO;
	
	@Autowired
    private RoleDAO roleDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void create(String username, String password, String roleName) {
		Role role = roleDAO.findByRoleName(roleName);
		if(role==null) {
			Role applicantRole = new Role();
			applicantRole.setId(1);
			applicantRole.setRole("applicant");
			roleDAO.create(applicantRole);
		}
		Applicant applicant = new Applicant();
		applicant.setUsername(username);
		applicant.setPassword(passwordEncoder.encode(password));
		applicant.setRole(roleDAO.findByRoleName(roleName));
		if(applicant.getMentor()==null) {
			applicant.setMentor(null);
		}
		
		applicantDAO.create(applicant);	
	}
	
	public Applicant findByUsername(String username) {
		return applicantDAO.findByUsername(username);
		
	}
	
	public List<Applicant> findByMentor(int id) {
		return applicantDAO.findByMentor(id);
		
	}

}
