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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.csye6220.kampus2go.model.Mentee;
import com.neu.csye6220.kampus2go.model.Mentor;
import com.neu.csye6220.kampus2go.model.Job;
import com.neu.csye6220.kampus2go.model.Admin;
import com.neu.csye6220.kampus2go.model.Resume;
import com.neu.csye6220.kampus2go.model.TimeSlot;
import com.neu.csye6220.kampus2go.model.User;
import com.neu.csye6220.kampus2go.service.MenteeService;
import com.neu.csye6220.kampus2go.service.MentorService;
import com.neu.csye6220.kampus2go.service.JobService;
import com.neu.csye6220.kampus2go.service.AdminService;
import com.neu.csye6220.kampus2go.service.ResumeService;
import com.neu.csye6220.kampus2go.service.TimeSlotService;

/**
 * @author pratiknakave
 *
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private MenteeService menteeService;
	
	@Autowired
	private MentorService mentorService;

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private ResumeService resumeService;
	
	@Autowired
	private TimeSlotService timeSlotService;
	
	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		//Session invalidation method to logout
		session.invalidate();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";
	}
	
	@GetMapping(value="/denied")
	public String denied() {
		return "denied";
	}

	@GetMapping(value = "/")
	public String landingUrl(HttpServletRequest request) {
		return "redirect:find-jobs";
	}

	@RequestMapping(value = "/find-jobs", method = RequestMethod.GET)
	public String findJobs(HttpServletRequest request, Model model) {
		List<Job> jobs = jobService.list();
		model.addAttribute("jobs", jobs);
		return "find-jobs";
	}

	@GetMapping(value = "/job/{jobId}")
	public String findJobById(HttpServletRequest request, @PathVariable String jobId, Model model) {
		Job job = jobService.findById(Integer.valueOf(jobId));
		model.addAttribute("job", job);
		return "view-job";
	}

	@GetMapping(value = "/find-jobs/search")
	public String findJobs(HttpServletRequest request, @RequestParam("keywords") String keywords, Model model) {
		List<Job> jobs = jobService.findByKeywords(keywords);
		model.addAttribute("jobs", jobs);
		return "find-jobs";
	}

	@GetMapping(value = "/find-jobs/filter")
	public String filterJobs(HttpServletRequest request, Model model) {
		String[] categories = request.getParameterValues("category");
		String[] jobTypes = request.getParameterValues("jobType");
		String[] levels = request.getParameterValues("level");
		String location = request.getParameter("location");
		List<Job> jobs = jobService.findByFilter(categories, jobTypes, levels, location);

		model.addAttribute("jobs", jobs);
		return "find-jobs";
	}

	@GetMapping(value = "/register")
	public String register(HttpServletRequest request) {
		return "register";
	}

	@PostMapping(value = "/register")
	public String userRegistration(HttpServletRequest request, Model model) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		if ("mentee".equals(role)) {
			menteeService.createMentee(username, password, role);
		} else if ("admin".equals(role)) {
			adminService.createAdmin(username, password, role);
		}
		else {
			mentorService.createMentor(username, password, role);
		}
		return "redirect:login";
	}

	@GetMapping(value = "/login")
	public String login(HttpServletRequest request) {
		return "login";
	}

	@GetMapping(value = "/dashboard")
	public String userDashboard(HttpServletRequest request, Model model) {
		User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		HttpSession session = request.getSession();
		String username = userDetails.getUsername();
		String role = userDetails.getRole().getRole();
		if ("mentee".equals(role)) {
			Mentee mentee = menteeService.findByUsername(username);
			session.setAttribute("mentee", mentee);
			return "redirect:/mentee-dashboard";
		} else if ("admin".equals(role)) {
			Admin admin = adminService.findByUsername(username);
			session.setAttribute("admin", admin);
			return "redirect:/admin-dashboard";
		}
		else if ("mentor".equals(role)){
			Mentor mentor = mentorService.findByUsername(username);
			session.setAttribute("mentor", mentor);
			return "redirect:/mentor-dashboard";
		}
		return "redirect:find-jobs";
	}

	@GetMapping(value = "/mentee-dashboard")
	public String menteeDashboard(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Mentee mentee = (Mentee)session.getAttribute("mentee");
		List <Resume> resumes = resumeService.findByMentee(mentee);
		if(mentee.getMentor()!=null) {
			Mentor mentor = mentorService.findById(mentee.getMentor().getId());
			model.addAttribute("mentor", mentor);
			List<TimeSlot> slots = timeSlotService.findByMentor(mentor);
			mentor.setTimeSlots(slots);
			model.addAttribute("slots",slots);
		}
		model.addAttribute("resumes", resumes);
		return "mentee-dashboard";
	}
	
	@GetMapping(value = "/mentor-dashboard")
	public String mentorDashboard(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Mentor mentor = (Mentor)session.getAttribute("mentor");
		List <Resume> resumes = resumeService.findByMentor(mentor);
		List<TimeSlot> slots = timeSlotService.findByMentor(mentor);
		model.addAttribute("resumes", resumes);
		model.addAttribute("slots", slots);
		return "mentor-dashboard";
	}

	@GetMapping(value = "/admin-dashboard")
	public String recruiterDashboard(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		List <Job> jobs = jobService.findByAdmin(admin);
		model.addAttribute("jobs", jobs);
		return "admin-dashboard";
	}
	
	@GetMapping(value = "/view-resume/{resumeId}")
	public String viewResume(HttpServletRequest request,@PathVariable("resumeId") String resumeId, Model model) {
		Resume resume = resumeService.findById(Integer.valueOf(resumeId));
		model.addAttribute("resume", resume);
		return "view-resume";
	}

}
