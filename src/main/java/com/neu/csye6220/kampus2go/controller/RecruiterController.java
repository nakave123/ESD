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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.neu.csye6220.kampus2go.model.Application;
import com.neu.csye6220.kampus2go.model.Position;
import com.neu.csye6220.kampus2go.model.Recruiter;
import com.neu.csye6220.kampus2go.model.Resume;
import com.neu.csye6220.kampus2go.service.ApplicationService;
import com.neu.csye6220.kampus2go.service.PositionService;
import com.neu.csye6220.kampus2go.service.ResumeService;

/**
 * @author pratiknakave
 *
 */
@Controller
public class RecruiterController {

	@Autowired
	private PositionService positionService;

	@Autowired
	private ResumeService resumeService;

	@Autowired
	private ApplicationService applicationService;

	@RequestMapping(value = "/new-position", method = RequestMethod.GET)
	public String newPosition(HttpServletRequest request, Model model) {
		model.addAttribute("position", new Position());
		return "new-position";
	}

	@RequestMapping(value = "/new-position", method = RequestMethod.POST)
	public String createNewPosition(HttpServletRequest request, @ModelAttribute("position") Position position,@RequestParam("file") MultipartFile file,
			Model model) {
		HttpSession session = request.getSession();
		Recruiter recruiter = (Recruiter) session.getAttribute("recruiter");
		position.setRecruiter(recruiter);
		position.setNumberOfApplications(0);
		//upload file
		if(!file.isEmpty() && file.getOriginalFilename()!=null) {
	        String filename = UUID.randomUUID()+file.getOriginalFilename();
	        String localDir = "C:\\jobboardUpload\\";
	        String pathUrl ="/jobboard/upload/"+filename;
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

	@RequestMapping(value = "/seek-talents", method = RequestMethod.GET)
	public String seekTalents(HttpServletRequest request, Model model) {
		List<Resume> resumes = resumeService.list();
		model.addAttribute("resumes", resumes);
		return "seek-talents";
	}

	@RequestMapping(value = "/seek-talents/filter", method = RequestMethod.GET)
	public String filterTalents(HttpServletRequest request, Model model) {
		String[] objectives = request.getParameterValues("objective");
		String experience = request.getParameter("experience");
		String[] degrees = request.getParameterValues("degree");
		String target = request.getParameter("target");
		List<Resume> resumes = resumeService.findByFilter(objectives, experience, degrees, target);
		model.addAttribute("resumes", resumes);
		return "seek-talents";
	}

	@RequestMapping(value = "/position/{positionId}/applications", method = RequestMethod.GET)
	protected String reviewApplications(HttpServletRequest request, @PathVariable String positionId, ModelMap model) {
		List<Application> applications = applicationService.findByPosition(Integer.valueOf(positionId));
		model.addAttribute("applications", applications);
		return "review-applications";
	}

	@RequestMapping(value = "/applications/{applicationId}/process", method = RequestMethod.POST)
	protected String processApplication(HttpServletRequest request, @PathVariable String applicationId,
			@RequestParam("status") String status, @RequestParam("message") String message, ModelMap model) {

		applicationService.processApplication(applicationId, status, message);
		
		return "redirect:/position/" + applicationId + "/applications";
	}

}
