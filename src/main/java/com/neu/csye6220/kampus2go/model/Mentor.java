package com.neu.csye6220.kampus2go.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author pratiknakave
 *
 */
@Entity
@Table(name="mentor")
@PrimaryKeyJoinColumn(name = "mentor_id", referencedColumnName = "user_id")
public class Mentor extends User{
	

	@OneToMany(mappedBy="mentor")
    private List<Applicant> applicants = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mentor")
	private List<Resume> resumes;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mentor")
	private List<TimeSlot> timeSlots;

	public Mentor() {
		super();
	}

	public List<Applicant> getApplicants() {
		return applicants;
	}


	public void setApplicants(List<Applicant> applicants) {
		this.applicants = applicants;
	}
	
	public List<Resume> getResumes() {
		return resumes;
	}


	public void setResumes(List<Resume> resumes) {
		this.resumes = resumes;
	}
	
	public List<TimeSlot> getTimeSlots() {
		return timeSlots;
	}

	public void setTimeSlots(List<TimeSlot> timeSlots) {
		this.timeSlots = timeSlots;
	}
}
