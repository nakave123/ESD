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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.neu.csye6220.kampus2go.model.Applicant;
import com.neu.csye6220.kampus2go.model.Education;
import com.neu.csye6220.kampus2go.model.Experience;
import com.neu.csye6220.kampus2go.model.Mentor;
import com.neu.csye6220.kampus2go.model.Resume;
import com.neu.csye6220.kampus2go.model.TimeSlot;
import com.neu.csye6220.kampus2go.service.ApplicantService;
import com.neu.csye6220.kampus2go.service.MentorService;
import com.neu.csye6220.kampus2go.service.ResumeService;
import com.neu.csye6220.kampus2go.service.TimeSlotService;

/**
 * @author pratiknakave
 *
 */
@Controller
public class MentorController {
	
	@Autowired
	private ApplicantService applicantService;
	
	@Autowired
	private ResumeService resumeService;
	
	@Autowired
	private MentorService mentorService;
	
	@Autowired
	private TimeSlotService timeSlotService;

	@GetMapping(value="/view-applicants")
	protected String viewMentors(HttpServletRequest request,ModelMap model){
		HttpSession session = request.getSession();
		Mentor mentor = (Mentor) session.getAttribute("mentor");
		List<Applicant> applicants = applicantService.findByMentor(mentor.getId());
		model.addAttribute("applicants",applicants);
		return "view-applicants";
	}
	
	@GetMapping(value = "/new-resume-mentor")
	public String newResume(HttpServletRequest request, Model model) {
		model.addAttribute("resume", new Resume());
		return "new-resume-mentor";
	}
	
	@GetMapping(value = "/new-mentor-slot")
	public String newTimeSlot(HttpServletRequest request, Model model) {
		model.addAttribute("timeslot", new TimeSlot());
		return "new-mentor-slot";
	}
	
	@PostMapping(value = "/new-mentor-slot")
	public String postTimeSlot(HttpServletRequest request, @ModelAttribute("timeslot") Object slot) {
		HttpSession session = request.getSession();
		Mentor mentor = (Mentor) session.getAttribute("mentor");

		String[] date = request.getParameterValues("date");
		String[] start = request.getParameterValues("start");
		String[] end = request.getParameterValues("end");
		String[] capacity = request.getParameterValues("capacity");
		
		List<TimeSlot> slots = new ArrayList<TimeSlot>();
		for (int i = 0; i < date.length; i++) {
			TimeSlot timeSlot = new TimeSlot();
			timeSlot.setDate(date[0]);
			timeSlot.setStart(start[0]);
			timeSlot.setEnd(end[0]);
			timeSlot.setCapacity(capacity[0]);
			timeSlot.setMentor(mentor);
			slots.add(timeSlot);
		}
		
		
		timeSlotService.createSlots(slots);
		return "redirect:/mentor-dashboard";
	}
	
	@PostMapping(value = "/new-resume-mentor")
	public String postResume(HttpServletRequest request, @ModelAttribute("resume") Resume resume) {
		HttpSession session = request.getSession();
		Mentor mentor = (Mentor) session.getAttribute("mentor");
		resume.setMentor(mentor);

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
		return "redirect:/mentor-dashboard";
	}
	
	@DeleteMapping(value = "/delete-mentor/{mentorId}")
	public String deleteMentor(HttpServletRequest request, @PathVariable("mentorId") String mentorId, Model model) {
		HttpSession session = request.getSession();
		Mentor mentor = (Mentor)session.getAttribute("mentor");
		
		//Need to fix session related issue (might be user props?)
		//mentor.getApplicants().clear();
		
		mentorService.delete(mentor);
		
		model.addAttribute("message", "Successfully deactivated this account!");
		
		return "message";
	}
	
	@DeleteMapping(value = "/delete-slot/{slotId}")
	public String deleteTimeSlot(HttpServletRequest request, @PathVariable("slotId") String slotId, Model model) {
		HttpSession session = request.getSession();
		Mentor mentor = (Mentor)session.getAttribute("mentor");
		
		TimeSlot slot = timeSlotService.findById(Integer.parseInt(slotId));	
		timeSlotService.delete(slot);
		
		model.addAttribute("message", "Successfully deleted the slot!");
		
		return "message";
	}
}
