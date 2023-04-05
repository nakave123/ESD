package com.neu.csye6220.kampus2go.model;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Entity
@Table(name = "recruiter")
@Component
@PrimaryKeyJoinColumn(name = "recruiter_id", referencedColumnName = "user_id")
public class Recruiter extends User {
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recruiter")
	private List<Position> positions;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recruiter")
	private List<Application> applications;

	public Recruiter() {
		super();
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	
}
