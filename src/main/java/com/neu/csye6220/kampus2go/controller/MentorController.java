package com.neu.csye6220.kampus2go.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.csye6220.kampus2go.model.Mentor;
import com.neu.csye6220.kampus2go.model.Applicant;
import com.neu.csye6220.kampus2go.service.ApplicantService;

/**
 * @author pratiknakave
 *
 */
@Controller
public class MentorController {
	
	@Autowired
	private ApplicantService applicantService;

	@RequestMapping(value="/view-applicants",method=RequestMethod.GET)
	protected String viewApplicants(HttpServletRequest request,ModelMap model){
		HttpSession session = request.getSession();
		Mentor mentor = (Mentor) session.getAttribute("mentor");
		List<Applicant> applicants = applicantService.findByMentor(mentor.getId());
		model.addAttribute("applicants",applicants);
		return "view-applicants";
	}
}
