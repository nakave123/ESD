package com.neu.csye6220.kampus2go.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.csye6220.kampus2go.service.UserService;

/**
 * @author pratiknakave
 *
 */

@Controller
public class AjaxController {
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/check-username")
	public String checkUsername(HttpServletRequest request,@RequestParam("username") String username) {
		System.out.println("got:"+username);
		if(userService.findByUsername(username)!=null) {
			return "Username already exists";
		}
		return "Username is available";
		
	}
}
