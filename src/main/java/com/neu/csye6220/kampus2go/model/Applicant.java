package com.neu.csye6220.kampus2go.model;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import com.neu.csye6220.kampus2go.model.Resume;
import com.neu.csye6220.kampus2go.model.Application;

@Entity
@Table(name = "applicant")
@PrimaryKeyJoinColumn(name = "applicant_id", referencedColumnName = "user_id")
public class Applicant extends User {
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "applicant")
	private List<Resume> resumes;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "applicant")
	private List<Application> applications;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mentor_id", nullable=false, insertable = true, updatable = true)
    private Mentor mentor;
	
//	@Column(name = "mentor_id")
//	private int mentor_id;


	public Applicant() {
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

	
}