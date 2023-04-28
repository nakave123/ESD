package com.neu.csye6220.kampus2go.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.csye6220.kampus2go.model.Admin;
import com.neu.csye6220.kampus2go.model.Application;
import com.neu.csye6220.kampus2go.model.Job;
import com.neu.csye6220.kampus2go.model.Resume;
import com.neu.csye6220.kampus2go.service.AdminService;
import com.neu.csye6220.kampus2go.service.ApplicationService;
import com.neu.csye6220.kampus2go.service.JobService;
import com.neu.csye6220.kampus2go.service.ResumeService;

/**
 * @author pratiknakave
 *
 */
@Controller
public class AdminController {

	@Autowired
	private JobService jobService;

	@Autowired
	private ResumeService resumeService;
	
	@Autowired
	private AdminService adminService;

	@Autowired
	private ApplicationService applicationService;

	@GetMapping(value = "/new-job")
	public String newJob(HttpServletRequest request, Model model) {
		model.addAttribute("job", new Job());
		return "new-job";
	}

	@PostMapping(value = "/new-job")
	public String createNewJob(HttpServletRequest request, @ModelAttribute("job") Job job,
			Model model) {
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		job.setAdmin(admin);
		job.setNumberOfApplications(0);
		
		LocalDate localDate = LocalDate.now();
		job.setPostDate(DateTimeFormatter.ofPattern("yyyy/MM/dd").format(localDate));
		jobService.create(job);
		model.addAttribute("message", "Successfully Created a Job");
		return "message";
	}

	@GetMapping(value = "/find-talents")
	public String seekTalents(HttpServletRequest request, Model model) {
		List<Resume> resumes = resumeService.list();
		model.addAttribute("resumes", resumes);
		return "find-talents";
	}

	@GetMapping(value = "/find-talents/filter")
	public String filterTalents(HttpServletRequest request, Model model) {
		String[] objectives = request.getParameterValues("objective");
		String experience = request.getParameter("experience");
		String[] degrees = request.getParameterValues("degree");
		List<Resume> resumes = resumeService.findByFilter(objectives, experience, degrees);
		model.addAttribute("resumes", resumes);
		return "find-talents";
	}

	@GetMapping(value = "/job/{jobId}/applications")
	protected String reviewApplications(HttpServletRequest request, @PathVariable String jobId, ModelMap model) {
		List<Application> applications = applicationService.findByJob(Integer.valueOf(jobId));
		model.addAttribute("applications", applications);
		return "review-applications";
	}

	@PostMapping(value = "/applications/{applicationId}/process")
	protected String processApplication(HttpServletRequest request, @PathVariable String applicationId,
			@RequestParam("status") String status, @RequestParam("message") String message, ModelMap model) {

		applicationService.processApplication(applicationId, status, message);
		
		return "redirect:/job/" + applicationId + "/applications";
	}
	
	@PutMapping(value = "/update-admin/{adminId}")
	public String updateAdmin(HttpServletRequest request, @PathVariable("adminId") String mentorId, Model model) {
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		
		adminService.merge(admin);
		
		model.addAttribute("message", "Successfully updated the account!");
		
		return "message";
	}
	
	@DeleteMapping(value = "/delete-admin/{adminId}")
	public String deleteAdmin(HttpServletRequest request, @PathVariable("adminId") String mentorId, Model model) {
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		adminService.delete(admin);
		
		model.addAttribute("message", "Successfully deactivated this account!");
		
		return "message";
	}

}
