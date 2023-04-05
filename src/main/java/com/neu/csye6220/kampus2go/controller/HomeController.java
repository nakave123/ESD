package com.neu.csye6220.kampus2go.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.csye6220.kampus2go.model.Applicant;
import com.neu.csye6220.kampus2go.model.Mentor;
import com.neu.csye6220.kampus2go.model.Position;
import com.neu.csye6220.kampus2go.model.Recruiter;
import com.neu.csye6220.kampus2go.model.Resume;
import com.neu.csye6220.kampus2go.model.User;
import com.neu.csye6220.kampus2go.service.ApplicantService;
import com.neu.csye6220.kampus2go.service.MentorService;
import com.neu.csye6220.kampus2go.service.PositionService;
import com.neu.csye6220.kampus2go.service.RecruiterService;
import com.neu.csye6220.kampus2go.service.ResumeService;

/**
 * @author pratiknakave
 *
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private ApplicantService applicantService;
	
	@Autowired
	private MentorService mentorService;

	@Autowired
	private RecruiterService recruiterService;

	@Autowired
	private PositionService positionService;
	
	@Autowired
	private ResumeService resumeService;
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		//Session invalidation method to logout
		session.invalidate();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";
	}
	
	@RequestMapping(value="/denied",method=RequestMethod.GET)
	public String accessDenied() {
		return "denied";
	}


	//Landing url
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String hello(HttpServletRequest request) {
//		return "Hello World!";
//	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		return "redirect:seek-jobs";
	}

	@RequestMapping(value = "/seek-jobs", method = RequestMethod.GET)
	public String seekJobs(HttpServletRequest request, Model model) {
		List<Position> positions = positionService.list();
		model.addAttribute("positions", positions);
		return "seek-jobs";
	}

	@RequestMapping(value = "/position/{positionId}", method = RequestMethod.GET)
	public String findJobById(HttpServletRequest request, @PathVariable String positionId, Model model) {
		Position position = positionService.findById(Integer.valueOf(positionId));
		model.addAttribute("position", position);
		return "view-position";
	}

	@RequestMapping(value = "/seek-jobs/search", method = RequestMethod.GET)
	public String searchJobs(HttpServletRequest request, @RequestParam("keywords") String keywords, Model model) {
		System.out.println("searched for: "+keywords);
		List<Position> positions = positionService.findByKeywords(keywords);
		model.addAttribute("positions", positions);
		return "seek-jobs";
	}

	@RequestMapping(value = "/seek-jobs/filter", method = RequestMethod.GET)
	public String filterJobs(HttpServletRequest request, Model model) {
		String[] categories = request.getParameterValues("category");
		String[] jobTypes = request.getParameterValues("jobType");
		String[] levels = request.getParameterValues("level");
		String location = request.getParameter("location");
		List<Position> positions = positionService.findByFilter(categories, jobTypes, levels, location);

		model.addAttribute("positions", positions);
		return "seek-jobs";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(HttpServletRequest request) {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(HttpServletRequest request, Model model) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		if (role.equals("applicant")) {
			applicantService.create(username, password, role);
		} else if (role.equals("recruiter")) {
			recruiterService.create(username, password, role);
		}
		else {
			mentorService.create(username, password, role);
		}
		return "redirect:login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		
		return "login";
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dispatcherUser(HttpServletRequest request, Model model) {
		User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		HttpSession session = request.getSession();
		String username = userDetails.getUsername();
		String role = userDetails.getRole().getRole();
		if (role.equals("applicant")) {
			Applicant applicant = applicantService.findByUsername(username);
			session.setAttribute("applicant", applicant);
			return "redirect:/applicant-dashboard";
		} else if (role.equals("recruiter")) {
			Recruiter recruiter = recruiterService.findByUsername(username);
			session.setAttribute("recruiter", recruiter);
			return "redirect:/recruiter-dashboard";
		}
		else if (role.equals("mentor")){
			Mentor mentor = mentorService.findByUsername(username);
			session.setAttribute("mentor", mentor);
			return "redirect:/mentor-dashboard";
		}
		return "redirect:seek-jobs";
	}

	@RequestMapping(value = "/applicant-dashboard", method = RequestMethod.GET)
	public String applicantDashboard(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Applicant applicant = (Applicant)session.getAttribute("applicant");
		List <Resume> resumes = resumeService.findByApplicant(applicant);
		model.addAttribute("resumes", resumes);
		return "applicant-dashboard";
	}
	
	@RequestMapping(value = "/mentor-dashboard", method = RequestMethod.GET)
	public String mentorDashboard(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Mentor mentor = (Mentor)session.getAttribute("mentor");
		//List <Applicant> applicants = applicantService.findByMentor(mentor.getId());
		//model.addAttribute("applicants", applicants);
		return "mentor-dashboard";
	}

	@RequestMapping(value = "/recruiter-dashboard", method = RequestMethod.GET)
	public String recruiterDashboard(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Recruiter recruiter = (Recruiter) session.getAttribute("recruiter");
		List <Position> positions = positionService.findByRecruiter(recruiter);
		model.addAttribute("positions", positions);
		return "recruiter-dashboard";
	}
	
	@RequestMapping(value = "/view-resume/{resumeId}", method = RequestMethod.GET)
	public String newPosition(HttpServletRequest request,@PathVariable("resumeId") String resumeId, Model model) {
		Resume resume = resumeService.findById(Integer.valueOf(resumeId));
		model.addAttribute("resume", resume);
		return "view-resume";
	}

}
