package com.neu.csye6220.kampus2go.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.neu.csye6220.kampus2go.dao.UserDAO;

/**
 * @author pratiknakave
 *
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
	

	@Autowired
	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = userDAO.findByUsername(username);
		System.out.println("userdetails from db:"+userDetails);
		return userDAO.findByUsername(username);
	}

}
