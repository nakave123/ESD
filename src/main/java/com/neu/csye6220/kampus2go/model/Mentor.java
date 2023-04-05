package com.neu.csye6220.kampus2go.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="mentor")
@Component
@PrimaryKeyJoinColumn(name = "mentor_id", referencedColumnName = "user_id")
public class Mentor extends User{
	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mentor")
//	private List<Resume> resumes;
//	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "mentor")
//	private List<Application> applications;

	@OneToMany(mappedBy="mentor", fetch = FetchType.LAZY)
	//To test - @JoinColumn(name="applicant_id")
    private List<Applicant> applicants = new ArrayList<>();
	
	public Mentor() {
		super();
	}


//	public List<Resume> getResumes() {
//		return resumes;
//	}
//
//
//	public void setResumes(List<Resume> resumes) {
//		this.resumes = resumes;
//	}
//
//
//	public List<Application> getApplications() {
//		return applications;
//	}
//
//
//	public void setApplications(List<Application> applications) {
//		this.applications = applications;
//	}
	
	public List<Applicant> getApplicants() {
		return applicants;
	}


	public void setApplicants(List<Applicant> applicants) {
		this.applicants = applicants;
	}
}
