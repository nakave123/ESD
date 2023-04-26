package com.neu.csye6220.kampus2go.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.csye6220.kampus2go.dao.ResumeDAO;
import com.neu.csye6220.kampus2go.model.Mentee;
import com.neu.csye6220.kampus2go.model.Mentor;
import com.neu.csye6220.kampus2go.model.Resume;

/**
 * @author pratiknakave
 *
 */
@Service
public class ResumeService {
	@Autowired
    private ResumeDAO resumeDAO;
	
	public void create(Resume resume) {
		resumeDAO.create(resume);
	}

	public List<Resume> list() {
		return resumeDAO.list();
	}
	
	public Resume findById(int id) {
		return resumeDAO.findById(id);
	}

	public List<Resume> findByFilter(String[] objectives, String experience, String[] degrees) {
		return resumeDAO.findByFilter(objectives,experience,degrees);
	}

	public List<Resume> findByMentee(Mentee mentee) {
		return resumeDAO.findByMentee(mentee);
	}
	
	public List<Resume> findByMentor(Mentor mentor) {
		return resumeDAO.findByMentor(mentor);
	}
}
