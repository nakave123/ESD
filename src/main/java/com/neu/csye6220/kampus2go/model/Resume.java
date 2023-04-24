package com.neu.csye6220.kampus2go.model;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author pratiknakave
 *
 */
@Entity
@Table(name="resume")
public class Resume {
	@ManyToOne
    @JoinColumn(name="mentee_id",referencedColumnName="mentee_id")
	private Mentee mentee;
	
	@ManyToOne
    @JoinColumn(name="mentor_id",referencedColumnName="mentor_id")
	private Mentor mentor;
	
	@Id
	@Column(name = "resume_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="objective")
	private String objective;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="tel")
	private String tel;
	
	@Column(name="email")
	private String email;
	
	
	@Column(name="yearsOfExperience")
	private int yearsOfExperience;
	
	@Column(name="createDate")
	private String createDate;
	
	@ElementCollection
	@CollectionTable(name="resume_skills", joinColumns=@JoinColumn(name="resume_id"))
	@Column(name="skill")
	private Set<String> skills;
	
	@OneToMany(cascade= CascadeType.ALL,mappedBy="resume")
	private List<Education> educations;

	@OneToMany(cascade= CascadeType.ALL,mappedBy="resume")
	private List<Experience> experiences;

	public Mentee getMentee() {
		return mentee;
	}

	public void setMentee(Mentee mentee) {
		this.mentee = mentee;
	}
	
	public Mentor getMentor() {
		return mentor;
	}

	public void setMentor(Mentor mentor) {
		this.mentor = mentor;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public Set<String> getSkills() {
		return skills;
	}

	public void setSkills(Set<String> skills) {
		this.skills = skills;
	}

	public List<Education> getEducations() {
		return educations;
	}

	public void setEducations(List<Education> educations) {
		this.educations = educations;
	}

	public List<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	

}
