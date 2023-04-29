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

import com.neu.csye6220.kampus2go.model.Mentee;
import com.neu.csye6220.kampus2go.model.Education;
import com.neu.csye6220.kampus2go.model.Experience;
import com.neu.csye6220.kampus2go.model.Mentor;
import com.neu.csye6220.kampus2go.model.Resume;
import com.neu.csye6220.kampus2go.model.TimeSlot;
import com.neu.csye6220.kampus2go.service.MenteeService;
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
	private MenteeService menteeService;
	
	@Autowired
	private ResumeService resumeService;
	
	@Autowired
	private MentorService mentorService;
	
	@Autowired
	private TimeSlotService timeSlotService;

	@GetMapping(value="/view-mentees")
	protected String viewMentors(HttpServletRequest request,ModelMap model){
		HttpSession session = request.getSession();
		Mentor mentor = (Mentor) session.getAttribute("mentor");
		List<Mentee> mentees = menteeService.findByMentor(mentor.getId());
		model.addAttribute("mentees",mentees);
		return "view-mentees";
	}
	
	@GetMapping(value = "/new-resume-mentor")
	public String newMentorResume(HttpServletRequest request, Model model) {
		model.addAttribute("resume", new Resume());
		return "new-resume-mentor";
	}
	
	@GetMapping(value = "/new-mentor-slot")
	public String newTimeSlot(HttpServletRequest request, Model model) {
		model.addAttribute("timeslot", new TimeSlot());
		return "new-mentor-slot";
	}
	
	@PostMapping(value = "/new-mentor-slot")
	public String createTimeSlot(HttpServletRequest request, @ModelAttribute("timeslot") Object slot) {
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
	public String createMentorResume(HttpServletRequest request, @ModelAttribute("resume") Resume resume) {
		HttpSession session = request.getSession();
		Mentor mentor = (Mentor) session.getAttribute("mentor");
		resume.setMentor(mentor);

		//Need to test
		String[] educationFrom = request.getParameterValues("educationFrom");
		String[] educationTo = request.getParameterValues("educationTo");
		String[] university = request.getParameterValues("university");
		String[] degree = request.getParameterValues("degree");
		String[] course = request.getParameterValues("course");
		

		List<Education> edus = new ArrayList<Education>();
		for (int i = 0; i < educationFrom.length; i++) {
			Education education = new Education();
			education.setStartYear(educationFrom[i]);
			education.setEndYear(educationTo[i]);
			education.setUniversity(university[i]);
			education.setDegree(degree[i]);
			education.setCourse(course[i]);
			education.setResume(resume);
			edus.add(education);
		}
		resume.setEducations(edus);

		//Need to test
		String[] experienceFrom = request.getParameterValues("experienceFrom");
		String[] experienceTo = request.getParameterValues("experienceTo");
		String[] organization = request.getParameterValues("organization");
		String[] job = request.getParameterValues("job");
		String[] category = request.getParameterValues("category");
		String[] responsibilities = request.getParameterValues("responsibilities");
		
		List<Experience> experiences = new ArrayList<Experience>();
		for (int i = 0; i < experienceFrom.length; i++) {
			Experience experience = new Experience();
			experience.setStartYear(experienceFrom[i]);
			experience.setEndYear(experienceTo[i]);
			experience.setOrganization(organization[i]);
			experience.setJob(job[i]);
			experience.setCategory(category[i]);
			experience.setResponsibilities(responsibilities[i]);
			experience.setResume(resume);
			experiences.add(experience);
		}
		resume.setExperiences(experiences);
		
		//creation date
		LocalDateTime date = LocalDateTime.now();
		System.out.println("Date is: "+DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm").format(date));
		resume.setCreateDate(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm").format(date));
		resumeService.create(resume);
		return "redirect:/mentor-dashboard";
	}
	
	@DeleteMapping(value = "/delete-mentor/{mentorId}")
	public String deleteMentor(HttpServletRequest request, @PathVariable("mentorId") String mentorId, Model model) {
		HttpSession session = request.getSession();
		Mentor mentor = (Mentor)session.getAttribute("mentor");
		
		mentorService.delete(mentor);
		
		model.addAttribute("message", "Successfully deactivated this account!");
		
		return "message";
	}
	
	@DeleteMapping(value = "/delete-slot/{slotId}")
	public String deleteTimeSlot(HttpServletRequest request, @PathVariable("slotId") String slotId, Model model) {
		HttpSession session = request.getSession();
		Mentor mentor = (Mentor)session.getAttribute("mentor");
		
		TimeSlot slot = timeSlotService.findById(Integer.parseInt(slotId));
		
		for(Mentee m:slot.getMentees()) {
			m.setTimeSlot(null);
			menteeService.merge(m);
		}
		
		timeSlotService.delete(slot);
		
		model.addAttribute("message", "Successfully deleted the slot!");
		
		return "message";
	}
}
