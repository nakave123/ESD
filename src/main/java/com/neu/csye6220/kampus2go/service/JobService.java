package com.neu.csye6220.kampus2go.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.csye6220.kampus2go.dao.JobDAO;
import com.neu.csye6220.kampus2go.model.Job;
import com.neu.csye6220.kampus2go.model.Admin;

/**
 * @author pratiknakave
 *
 */
@Service
public class JobService {
	
	
	@Autowired
    private JobDAO jobDAO;
	
	public void create(Job position) {
		jobDAO.create(position);
	}
	
	public List<Job> list() {
		return jobDAO.list();
	}

	public List<Job> findByKeywords(String keywords) {
		return jobDAO.findByKeywords(keywords);
		
	}

	public Job findById(int id) {
		return jobDAO.findById(id);
	}

	public List<Job> findByFilter(String[] categories, String[] jobTypes, String[] levels, String location) {
		return jobDAO.findByFilter(categories,jobTypes,levels,location);
	}

	public List<Job> findByAdmin(Admin recruiter) {
		return jobDAO.findByAdmin(recruiter);
	}
}
