package com.neu.csye6220.kampus2go.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.csye6220.kampus2go.service.UserService;

/**
 * @author pratiknakave
 *
 */

@Controller
public class AjaxController {
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/verify-username")
	@ResponseBody
	public String verifyUsername(HttpServletRequest request,@RequestParam("username") String username) {
		System.out.println("username details:"+username);
		if(userService.findByUsername(username)!=null) {
			return "Username exists. Please enter a different one!";
		}
		return "Username is available";
		
	}
}
