package com.neu.csye6220.kampus2go.model;
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
@Table(name = "admin")
@PrimaryKeyJoinColumn(name = "admin_id", referencedColumnName = "user_id")
public class Admin extends User {
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "admin")
	private List<Position> positions;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "admin")
	private List<Application> applications;

	public Admin() {
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
