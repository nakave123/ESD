package com.neu.csye6220.kampus2go.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author pratiknakave
 *
 */
@Entity
@Table(name="application")
public class Application {
	
	@Id
	@Column(name = "application_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//Many applications can be submitted by one mentee
	@ManyToOne
	@JoinColumn(name="menteee_id", referencedColumnName="mentee_id")
	private Mentee mentee;
	
	//Many applications can be reviewed by an admin
	@ManyToOne
	@JoinColumn(name="admin_id", referencedColumnName="admin_id")
	private Admin admin;
	
	//Many applications can be posted using one resume
	@ManyToOne
	@JoinColumn(name="resume_id", referencedColumnName="resume_id")
	private Resume resume;
	
	//Many applications can be submitted for one job
	@ManyToOne
	@JoinColumn(name="job_id", referencedColumnName="job_id")
	private Job job;
	
	@Column(name="status")
	private String status;
	
	@Column(name="message")
	private String message;
	
	@Column(name="applyDate")
	private String applyDate;
	
	@Column(name="lastUpdate")
	private String lastUpdate;

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

	public Mentee getMentee() {
		return mentee;
	}

	public void setMentee(Mentee mentee) {
		this.mentee = mentee;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
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
