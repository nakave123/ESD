package com.neu.csye6220.kampus2go.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.neu.csye6220.kampus2go.model.Application;
import com.neu.csye6220.kampus2go.model.Position;
import com.neu.csye6220.kampus2go.model.Admin;
import com.neu.csye6220.kampus2go.model.Resume;
import com.neu.csye6220.kampus2go.service.ApplicationService;
import com.neu.csye6220.kampus2go.service.PositionService;
import com.neu.csye6220.kampus2go.service.AdminService;
import com.neu.csye6220.kampus2go.service.ResumeService;

/**
 * @author pratiknakave
 *
 */
@Controller
public class AdminController {

	@Autowired
	private PositionService positionService;

	@Autowired
	private ResumeService resumeService;
	
	@Autowired
	private AdminService adminService;

	@Autowired
	private ApplicationService applicationService;

	@GetMapping(value = "/new-position")
	public String newPosition(HttpServletRequest request, Model model) {
		model.addAttribute("position", new Position());
		return "new-position";
	}

	@PostMapping(value = "/new-position")
	public String createNewPosition(HttpServletRequest request, @ModelAttribute("position") Position position,@RequestParam("file") MultipartFile file,
			Model model) {
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		position.setAdmin(admin);
		position.setNumberOfApplications(0);
		//upload file
		if(!file.isEmpty() && file.getOriginalFilename()!=null) {
	        String filename = UUID.randomUUID()+file.getOriginalFilename();
	        String localDir = "C:\\kampus2goUpload\\";
	        String pathUrl ="/kampus2go/upload/"+filename;
			try {
				byte[] bytes = file.getBytes();
				FileCopyUtils.copy(bytes, new File(localDir+filename));
				position.setLogo(pathUrl);
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		
		LocalDate localDate = LocalDate.now();
		position.setPostDate(DateTimeFormatter.ofPattern("yyyy/MM/dd").format(localDate));
		positionService.create(position);
		model.addAttribute("message", "Successfully Created a Position");
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
		String target = request.getParameter("target");
		List<Resume> resumes = resumeService.findByFilter(objectives, experience, degrees, target);
		model.addAttribute("resumes", resumes);
		return "find-talents";
	}

	@GetMapping(value = "/position/{positionId}/applications")
	protected String reviewApplications(HttpServletRequest request, @PathVariable String positionId, ModelMap model) {
		List<Application> applications = applicationService.findByPosition(Integer.valueOf(positionId));
		model.addAttribute("applications", applications);
		return "review-applications";
	}

	@PostMapping(value = "/applications/{applicationId}/process")
	protected String processApplication(HttpServletRequest request, @PathVariable String applicationId,
			@RequestParam("status") String status, @RequestParam("message") String message, ModelMap model) {

		applicationService.processApplication(applicationId, status, message);
		
		return "redirect:/position/" + applicationId + "/applications";
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
