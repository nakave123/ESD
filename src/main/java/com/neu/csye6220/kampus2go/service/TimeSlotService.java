/**
 * 
 */
package com.neu.csye6220.kampus2go.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.csye6220.kampus2go.dao.TimeSlotDAO;
import com.neu.csye6220.kampus2go.model.Mentor;
import com.neu.csye6220.kampus2go.model.TimeSlot;

/**
 * @author pratiknakave
 *
 */
@Service
public class TimeSlotService {
	
	@Autowired
	TimeSlotDAO timeSlotDAO;

	public void createSlots(List<TimeSlot> timeSlots) {
		timeSlotDAO.create(timeSlots);
	}
	
	public List<TimeSlot> findByMentor(Mentor mentor) {
		return timeSlotDAO.findByMentor(mentor.getId());
	}
	
	public TimeSlot findById(int id) {
		return timeSlotDAO.findById(id);
		
	}
	
	public void merge(TimeSlot slot) {
		timeSlotDAO.merge(slot);
	}
	
	public void delete(TimeSlot slot) {
		timeSlotDAO.delete(slot);
	}
}
