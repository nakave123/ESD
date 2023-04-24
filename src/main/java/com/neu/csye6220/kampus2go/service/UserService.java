package com.neu.csye6220.kampus2go.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.csye6220.kampus2go.dao.UserDAO;
import com.neu.csye6220.kampus2go.model.User;

/**
 * @author pratiknakave
 *
 */
@Service
public class UserService {
	@Autowired
    private UserDAO userDAO;
	
	public User findByUsername(String username) {
		return userDAO.findByUsername(username);
		
	}
}
