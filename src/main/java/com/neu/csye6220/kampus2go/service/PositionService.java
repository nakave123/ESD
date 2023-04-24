package com.neu.csye6220.kampus2go.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.csye6220.kampus2go.dao.PositionDAO;
import com.neu.csye6220.kampus2go.model.Position;
import com.neu.csye6220.kampus2go.model.Recruiter;

/**
 * @author pratiknakave
 *
 */
@Service
public class PositionService {
	
	
	@Autowired
    private PositionDAO positionDAO;
	
	public void create(Position position) {
		positionDAO.create(position);
	}
	
	public List<Position> list() {
		return positionDAO.list();
	}

	public List<Position> findByKeywords(String keywords) {
		return positionDAO.findByKeywords(keywords);
		
	}

	public Position findById(int id) {
		return positionDAO.findById(id);
	}

	public List<Position> findByFilter(String[] categories, String[] jobTypes, String[] levels, String location) {
		return positionDAO.findByFilter(categories,jobTypes,levels,location);
	}

	public List<Position> findByRecruiter(Recruiter recruiter) {
		return positionDAO.findByRecruiter(recruiter);
	}
}
