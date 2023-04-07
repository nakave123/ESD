package com.neu.csye6220.kampus2go.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.neu.csye6220.kampus2go.model.Resume;
import com.neu.csye6220.kampus2go.model.Position;
import com.neu.csye6220.kampus2go.model.Applicant;
import com.neu.csye6220.kampus2go.model.Recruiter;
@Entity
@Table(name="application")
public class Application {
	
	@Id
	@Column(name = "application_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="applicant_id", referencedColumnName="applicant_id")
	private Applicant applicant;
	
	@ManyToOne
	@JoinColumn(name="recruiter_id", referencedColumnName="recruiter_id")
	private Recruiter recruiter;
	
	@ManyToOne
	@JoinColumn(name="resume_id", referencedColumnName="resume_id")
	private Resume resume;
	
	@ManyToOne
	@JoinColumn(name="position_id", referencedColumnName="position_id")
	private Position position;
	
	@Column(name="applyDate")
	private String applyDate;
	
	@Column(name="lastUpdate")
	private String lastUpdate;
	
	@Column(name="status")
	private String status;
	
	@Column(name="message")
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public Recruiter getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(Recruiter recruiter) {
		this.recruiter = recruiter;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
