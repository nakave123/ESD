package com.neu.csye6220.kampus2go.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.neu.csye6220.kampus2go.dao.MenteeDAO;
import com.neu.csye6220.kampus2go.dao.RoleDAO;
import com.neu.csye6220.kampus2go.model.Mentee;
import com.neu.csye6220.kampus2go.model.Role;

/**
 * @author pratiknakave
 *
 */
@Service
public class MenteeService {
	
	@Autowired
    private MenteeDAO menteeDAO;
	
	@Autowired
    private RoleDAO roleDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void create(String username, String password, String roleName) {
		Role role = roleDAO.findByRoleName(roleName);
		if(role==null) {
			Role applicantRole = new Role();
			applicantRole.setId(1);
			applicantRole.setRole("mentee");
			roleDAO.create(applicantRole);
		}
		Mentee mentee = new Mentee();
		mentee.setUsername(username);
		mentee.setPassword(passwordEncoder.encode(password));
		mentee.setRole(roleDAO.findByRoleName(roleName));
		if(mentee.getMentor()==null) {
			mentee.setMentor(null);
		}
		
		menteeDAO.create(mentee);	
	}
	
	public Mentee findByUsername(String username) {
		return menteeDAO.findByUsername(username);
		
	}
	
	public void merge(Mentee mentee) {
		menteeDAO.merge(mentee);
	}
	
	public void delete(Mentee mentee) {
		menteeDAO.delete(mentee);
	}
	
	public List<Mentee> findByMentor(int id) {
		return menteeDAO.findByMentor(id);
		
	}

}
