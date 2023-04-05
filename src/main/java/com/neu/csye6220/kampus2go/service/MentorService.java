package com.neu.csye6220.kampus2go.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.neu.csye6220.kampus2go.dao.MentorDAO;
import com.neu.csye6220.kampus2go.dao.RoleDAO;
import com.neu.csye6220.kampus2go.model.Mentor;
import com.neu.csye6220.kampus2go.model.Role;

@Service
@Component
public class MentorService {
	
	@Autowired
    private MentorDAO mentorDAO;
	
	@Autowired
    private RoleDAO roleDAO;
	
	//@Autowired
	//private BCryptPasswordEncoder bcryptEncoder;
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
	public void create(String username, String password, String roleName) {
		Role role = roleDAO.findByRoleName(roleName);
		if(role==null) {
			Role mentorRole = new Role();
			mentorRole.setId(3);
			mentorRole.setRole("mentor");
			roleDAO.create(mentorRole);
		}
		Mentor mentor = new Mentor();
		mentor.setUsername(username);
		mentor.setPassword(encoder().encode(password));
		//mentor.setPassword(bcryptEncoder.encode(password));
		//mentor.setPassword(password);
		mentor.setRole(roleDAO.findByRoleName(roleName));
		mentorDAO.create(mentor);	
	}
	
	public Mentor findByUsername(String username) {
		return mentorDAO.findByUsername(username);
		
	}
	
	public List<Mentor> getAllMentors(){
		return mentorDAO.list();
	}
}
