/**
 * 
 */
package com.neu.csye6220.kampus2go.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="timeslot")
@PrimaryKeyJoinColumn(name = "slot_id")
public class TimeSlot {

	@ManyToOne
    @JoinColumn(name="mentor_id",referencedColumnName="mentor_id")
	private Mentor mentor;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="timeSlot")
    private List<Applicant> applicants = new ArrayList<>();

	@Id
	@Column(name = "slot_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="date")
	private String date;
	
	@Column(name="start")
	private String start;
	
	@Column(name="end")
	private String end;
	
	@Column(name="capacity")
	private String capacity;

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	
	public List<Applicant> getApplicants() {
		return applicants;
	}

	public void setApplicants(List<Applicant> applicants) {
		this.applicants = applicants;
	}
	
}
