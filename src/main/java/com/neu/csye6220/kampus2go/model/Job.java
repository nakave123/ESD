package com.neu.csye6220.kampus2go.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
@Table(name="job")
public class Job {
	
	//Many  jobs can be posted by an admin
	@ManyToOne
    @JoinColumn(name="admin_id",referencedColumnName="admin_id")
	private Admin admin;
	
	@Id
	@Column(name = "job_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "summary")
	private String summary;

	@Column(name = "organization")	
	private String organization;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "jobType")
	private String jobType;
	
	@Column(name = "level")
	private String level;
	
	@Column(name = "salary")
	private int salary;
	
	@Column(name = "openings")
	private int openings;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "responsibilities")
	private String responsibilities;
	
	@Column(name = "qualifications")
	private String qualifications;
	
	@ElementCollection
	@CollectionTable(name="job_skills", joinColumns=@JoinColumn(name="job_id"))
	@Column(name="skill")
	private Set<String> skills;
	
	@Column(name = "postDate")
	private String postDate;
	
	@Column(name = "closeDate")
	private String closeDate;
	
	@Column(name = "numOfApplications")
	private int numOfApplications;

	public Job() {
		
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getOpenings() {
		return openings;
	}

	public void setOpenings(int openings) {
		this.openings = openings;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getResponsibilities() {
		return responsibilities;
	}

	public void setResponsibilities(String responsibilities) {
		this.responsibilities = responsibilities;
	}

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}

	public Set<String> getSkills() {
		return skills;
	}

	public void setSkills(Set<String> skills) {
		this.skills = skills;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}

	public int getNumOfApplications() {
		return numOfApplications;
	}

	public void setNumOfApplications(int numOfApplications) {
		this.numOfApplications = numOfApplications;
	}
	
	
}
