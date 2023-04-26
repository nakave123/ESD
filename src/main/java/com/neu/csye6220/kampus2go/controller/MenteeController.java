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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.csye6220.kampus2go.model.Mentee;
import com.neu.csye6220.kampus2go.model.Application;
import com.neu.csye6220.kampus2go.model.Education;
import com.neu.csye6220.kampus2go.model.Experience;
import com.neu.csye6220.kampus2go.model.Mentor;
import com.neu.csye6220.kampus2go.model.Resume;
import com.neu.csye6220.kampus2go.model.TimeSlot;
import com.neu.csye6220.kampus2go.service.MenteeService;
import com.neu.csye6220.kampus2go.service.ApplicationService;
import com.neu.csye6220.kampus2go.service.MentorService;
import com.neu.csye6220.kampus2go.service.ResumeService;
import com.neu.csye6220.kampus2go.service.TimeSlotService;

/**
 * @author pratiknakave
 *
 */
@Controller
public class MenteeController {
	
	@Autowired
	private MentorService mentorService;
	
	@Autowired
	private ResumeService resumeService;
	
	@Autowired
	private MenteeService menteeService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private TimeSlotService timeSlotService;
	
	@GetMapping(value = "/new-resume-mentee")
	public String newResume(HttpServletRequest request, Model model) {
		model.addAttribute("resume", new Resume());
		return "new-resume-mentee";
	}
	
	@PostMapping(value = "/new-resume-mentee")
	public String postResume(HttpServletRequest request, @ModelAttribute("resume") Resume resume) {
		HttpSession session = request.getSession();
		Mentee mentee = (Mentee) session.getAttribute("mentee");
		resume.setMentee(mentee);

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
		return "redirect:/mentee-dashboard";
	}

	@PostMapping(value = "/position/{positionId}/apply")
	public String apply(HttpServletRequest request, @PathVariable String positionId,
			@RequestParam("resumeId") String resumeId, Model model) {
		HttpSession session = request.getSession();
		Mentee mentee = (Mentee) session.getAttribute("mentee");
		if (applicationService.create(mentee, Integer.valueOf(resumeId), Integer.valueOf(positionId))) {
			model.addAttribute("message", "Successfully applied!");
		} else {
			model.addAttribute("message", "Already applied once.");
		}
		return "message";
	}
	
	@GetMapping(value="/check-applications")
	protected String checkApplications(HttpServletRequest request,ModelMap model){
		HttpSession session = request.getSession();
		Mentee mentee = (Mentee) session.getAttribute("mentee");
		List<Application> applications = applicationService.findByMentee(mentee);
		model.addAttribute("applications",applications);
		return "check-applications";
	}
	
	@GetMapping(value = "/find-mentors")
	public String findkMentors(HttpServletRequest request, Model model) {
		List<Mentor> mentors = mentorService.getAllMentors();
		model.addAttribute("mentors", mentors);
		return "find-mentors";
	}

	@GetMapping(value = "/find-mentors/filter")
	public String filterMentors(HttpServletRequest request, Model model) {
		String[] objectives = request.getParameterValues("objective");
		String experience = request.getParameter("experience");
		String[] degrees = request.getParameterValues("degree");
		List<Resume> resumes = resumeService.findByFilter(objectives, experience, degrees);
		model.addAttribute("resumes", resumes);
		return "find-mentors";
	}
	
	@PatchMapping(value = "/request-mentor/{mentorId}")
	public String requestMentor(HttpServletRequest request, @PathVariable("mentorId") String mentorId, Model model) {
		HttpSession session = request.getSession();
		Mentee mentee = (Mentee)session.getAttribute("mentee");
		Mentor mentor = mentorService.findById(Integer.valueOf(mentorId));
		mentee.setMentor(mentor);
		
		mentor.getMentees().add(mentee);
		//mentor merge??
		
		//mentorService.merge(mentor);
		menteeService.merge(mentee);
		
		Mentee newMentee = menteeService.findByUsername(mentee.getUsername());
		model.addAttribute("mentee", newMentee);
		model.addAttribute("message", "Successfully set a mentor to this account!");
		
		return "message";
		//return "redirect:/mentee-dashboard";
	}
	
	@PutMapping(value = "/mentee-slot-book/{slotId}")
	public String bookTimeSlot(HttpServletRequest request, @PathVariable("slotId") String slotId, Model model) {
		HttpSession session = request.getSession();
		Mentee mentee = (Mentee)session.getAttribute("mentee");
		TimeSlot timeSlot = timeSlotService.findById(Integer.parseInt(slotId));
		mentee.setTimeSlot(timeSlot);
		timeSlot.getMentees().add(mentee);
		//updating capacity after booking
		int newCapacity = Integer.parseInt(timeSlot.getCapacity())-1;
		timeSlot.setCapacity(String.valueOf(newCapacity));
		//timeslot merge??
		timeSlotService.merge(timeSlot);
		menteeService.merge(mentee);
		
		model.addAttribute("message", "Successfully set a time-slot to this account!");
		return "message";
	}
	
	@PutMapping(value = "/mentee-slot-cancel/{slotId}")
	public String cancelTimeSlot(HttpServletRequest request, @PathVariable("slotId") String slotId, Model model) {
		HttpSession session = request.getSession();
		Mentee mentee = (Mentee)session.getAttribute("mentee");
		TimeSlot timeSlot = timeSlotService.findById(Integer.parseInt(slotId));
		mentee.setTimeSlot(null);
		timeSlot.getMentees().remove(mentee);
		//updating capacity after booking
		int newCapacity = Integer.parseInt(timeSlot.getCapacity())+1;
		timeSlot.setCapacity(String.valueOf(newCapacity));
		//timeslot merge??
		timeSlotService.merge(timeSlot);
		menteeService.merge(mentee);
		
		model.addAttribute("message", "Successfully cancelled a time-slot to this account!");
		return "message";
	}
	
	@PutMapping(value = "/update-mentee/{menteeId}")
	public String updateMentee(HttpServletRequest request, @PathVariable("menteeId") String menteeId, Model model) {
		HttpSession session = request.getSession();
		Mentee mentee = (Mentee)session.getAttribute("mentee");
		int mentorId = mentee.getMentor().getId();
		Mentor mentor = mentorService.findById(mentorId);
		
		if(mentee.getTimeSlot()!=null) {
			TimeSlot slot = timeSlotService.findById(mentee.getTimeSlot().getId());
			slot.setCapacity(String.valueOf(Integer.parseInt(slot.getCapacity())+1));
			timeSlotService.merge(slot);
			//mentee.setTimeSlot(null);
		}
		
		mentee.setTimeSlot(null);
		
		for(Mentee a:mentor.getMentees()) {
			if(a.getId()==mentee.getId()) {
				mentor.getMentees().remove(a);
				break;
			}
		}
		mentee.setMentor(null);
		
		menteeService.merge(mentee);
		
		model.addAttribute("message", "Successfully removed mentor!");
		return "message";
		
	}
	
	@DeleteMapping(value = "/delete-mentee/{menteeId}")
	public String deleteMentee(HttpServletRequest request, @PathVariable("menteeId") String menteeId, Model model) {
		HttpSession session = request.getSession();
		Mentee mentee = (Mentee)session.getAttribute("mentee");
		
		//Need to investigate on how to delete and resolve session error
		//Illegal attempt to associate a collection with two open sessions
		menteeService.delete(mentee);
		
		model.addAttribute("message", "Successfully deactivated this account!");
		
		return "message";
	}

}
