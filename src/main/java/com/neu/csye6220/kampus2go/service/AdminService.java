package com.neu.csye6220.kampus2go.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.neu.csye6220.kampus2go.dao.AdminDAO;
import com.neu.csye6220.kampus2go.dao.RoleDAO;
import com.neu.csye6220.kampus2go.model.Admin;
import com.neu.csye6220.kampus2go.model.Role;

/**
 * @author pratiknakave
 *
 */
@Service
public class AdminService {
	
	@Autowired
    private AdminDAO adminDAO;
	
	@Autowired
    private RoleDAO roleDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void create(String username, String password, String roleName) {
		Role role = roleDAO.findByRoleName(roleName);
		if(role==null) {
			Role adminRole = new Role();
			adminRole.setId(2);
			adminRole.setRole("admin");
			roleDAO.create(adminRole);
		}
		
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(passwordEncoder.encode(password));
		admin.setRole(roleDAO.findByRoleName(roleName));
		adminDAO.create(admin);	
	} 
	public Admin findByUsername(String username) {
		return adminDAO.findByUsername(username);
		
	}
	
	public void merge(Admin admin) {
		adminDAO.merge(admin);
	}
	
	public void delete(Admin admin) {
		adminDAO.delete(admin);
	}
}
