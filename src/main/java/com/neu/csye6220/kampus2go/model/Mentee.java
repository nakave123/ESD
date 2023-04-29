package com.neu.csye6220.kampus2go.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author pratiknakave
 *
 */
@Entity
@Table(name = "mentee")
@PrimaryKeyJoinColumn(name = "mentee_id", referencedColumnName = "user_id")
public class Mentee extends User {
	
	//One mentee will have many resumes
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mentee")
	private List<Resume> resumes;
	
	//One mentee can submit many applications
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mentee")
	private List<Application> applications;
	
	//Many mentee can be assigned to a mentor
	@ManyToOne()
    @JoinColumn(name="mentor_id")
    private Mentor mentor;
	
	//Many mentees can book the same time slot of a mentor
	@ManyToOne()
    @JoinColumn(name="slot_id")
    private TimeSlot timeSlot;


	public Mentee() {
		super();
	}


	public List<Resume> getResumes() {
		return resumes;
	}


	public void setResumes(List<Resume> resumes) {
		this.resumes = resumes;
	}


	public List<Application> getApplications() {
		return applications;
	}


	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}
	
	public Mentor getMentor() {
		return mentor;
	}


	public void setMentor(Mentor mentor) {
		this.mentor = mentor;
	}
	
	public TimeSlot getTimeSlot() {
		return timeSlot;
	}


	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

	
}
