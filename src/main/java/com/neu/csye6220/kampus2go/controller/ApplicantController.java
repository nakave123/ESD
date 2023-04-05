package com.neu.csye6220.kampus2go.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.csye6220.kampus2go.model.Applicant;
import com.neu.csye6220.kampus2go.model.Application;
import com.neu.csye6220.kampus2go.model.Education;
import com.neu.csye6220.kampus2go.model.Experience;
import com.neu.csye6220.kampus2go.model.Mentor;
import com.neu.csye6220.kampus2go.model.Resume;
import com.neu.csye6220.kampus2go.service.ApplicantService;
import com.neu.csye6220.kampus2go.service.ApplicationService;
import com.neu.csye6220.kampus2go.service.MentorService;
import com.neu.csye6220.kampus2go.service.ResumeService;

/**
 * @author pratiknakave
 *
 */
@Controller
public class ApplicantController {
	
	@Autowired
	private MentorService mentorService;
	
	@Autowired
	private ResumeService resumeService;
	
	@Autowired
	private ApplicantService applicantService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@RequestMapping(value = "/new-resume", method = RequestMethod.GET)
	public String newResume(HttpServletRequest request, Model model) {
		model.addAttribute("resume", new Resume());
		return "new-resume";
	}
	
	@RequestMapping(value = "/new-resume", method = RequestMethod.POST)
	public String postResume(HttpServletRequest request, @ModelAttribute("resume") Resume resume) {
		HttpSession session = request.getSession();
		Applicant applicant = (Applicant) session.getAttribute("applicant");
		resume.setApplicant(applicant);

		//Hardcoded data
		//String[] eduFrom = new String[]{"2021-09","2014-06"};
		//String[] eduTo = new String[]{"2023-05","2018-05"};
		//String[] university = new String[]{"NEU","SPPU"};
		//String[] degree = new String[]{"MS","BE"};
		//String[] major = new String[]{"SES","CE"};
		
		
		//Need to test
		String[] eduFrom = request.getParameterValues("eduFrom");
		String[] eduTo = request.getParameterValues("eduTo");
		String[] university = request.getParameterValues("university");
		String[] degree = request.getParameterValues("degree");
		String[] major = request.getParameterValues("major");
		

		List<Education> edus = new ArrayList<Education>();
		for (int i = 0; i < eduFrom.length; i++) {
			Education edu = new Education();
			edu.setStartYear(eduFrom[i]);
			edu.setEndYear(eduTo[i]);
			edu.setUniversity(university[i]);
			edu.setDegree(degree[i]);
			edu.setMajor(major[i]);
			edu.setResume(resume);
			edus.add(edu);
		}
		resume.setEducations(edus);

		//Hardcoded data
		//String[] expFrom = new String[]{"2018-04","2022-05"};
		//String[] expTo = new String[]{"2021-08","2022-12"};
		//String[] company = new String[]{"TIBCO","BBC"};
		//String[] position = new String[]{"Software Engg","Full Stack Engg"};
		//String[] category = new String[]{"SDE","SDE"};
		//String[] responsibilities = new String[]{"Coding","Coding"};
		
		//Need to test
		String[] expFrom = request.getParameterValues("expFrom");
		String[] expTo = request.getParameterValues("expTo");
		String[] company = request.getParameterValues("company");
		String[] position = request.getParameterValues("position");
		String[] category = request.getParameterValues("category");
		String[] responsibilities = request.getParameterValues("responsibilities");
		
		List<Experience> exps = new ArrayList<Experience>();
		for (int i = 0; i < expFrom.length; i++) {
			Experience exp = new Experience();
			exp.setStartYear(expFrom[i]);
			exp.setEndYear(expTo[i]);
			exp.setCompany(company[i]);
			exp.setPosition(position[i]);
			exp.setCategory(category[i]);
			exp.setResponsibilities(responsibilities[i]);
			exp.setResume(resume);
			exps.add(exp);
		}
		resume.setExperiences(exps);
		
		//creation date
		LocalDateTime localDate = LocalDateTime.now();
		System.out.println("Date: "+DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm").format(localDate));
		resume.setCreateDate(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm").format(localDate));
		resumeService.create(resume);
		return "redirect:/applicant-dashboard";
	}

	@RequestMapping(value = "/position/{positionId}/apply", method = RequestMethod.POST)
	public String apply(HttpServletRequest request, @PathVariable String positionId,
			@RequestParam("resumeId") String resumeId, Model model) {
		HttpSession session = request.getSession();
		Applicant applicant = (Applicant) session.getAttribute("applicant");
		if (applicationService.create(applicant, Integer.valueOf(resumeId), Integer.valueOf(positionId))) {
			model.addAttribute("message", "Successfully applied!");
		} else {
			model.addAttribute("message", "Already applied once.");
		}
		return "message";
	}
	
	@RequestMapping(value="/check-applications",method=RequestMethod.GET)
	protected String checkApplications(HttpServletRequest request,ModelMap model){
		HttpSession session = request.getSession();
		Applicant applicant = (Applicant) session.getAttribute("applicant");
		List<Application> applications = applicationService.findByApplicant(applicant);
		model.addAttribute("applications",applications);
		return "check-applications";
	}
	
	@RequestMapping(value = "/seek-mentors", method = RequestMethod.GET)
	public String seekMentors(HttpServletRequest request, Model model) {
		List<Mentor> mentors = mentorService.getAllMentors();
		model.addAttribute("mentors", mentors);
		return "seek-mentors";
	}

//	@RequestMapping(value = "/seek-mentors/filter", method = RequestMethod.GET)
//	public String filterMentors(HttpServletRequest request, Model model) {
//		String[] objectives = request.getParameterValues("objective");
//		String experience = request.getParameter("experience");
//		String[] degrees = request.getParameterValues("degree");
//		String target = request.getParameter("target");
//		List<Resume> resumes = resumeService.findByFilter(objectives, experience, degrees, target);
//		model.addAttribute("resumes", resumes);
//		return "seek-mentors";
//	}

}
