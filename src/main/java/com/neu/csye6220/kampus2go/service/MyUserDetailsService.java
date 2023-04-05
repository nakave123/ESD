package com.neu.csye6220.kampus2go.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.neu.csye6220.kampus2go.dao.UserDAO;
@Service
@Component
public class MyUserDetailsService implements UserDetailsService {
	

	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("load user by username");
		UserDetails userDetails = userDAO.findByUsername(username);
		System.out.println("userdetails:"+userDetails);
		return userDAO.findByUsername(username);
	}

}
