package com.neu.csye6220.kampus2go.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.csye6220.kampus2go.dao.ResumeDAO;
import com.neu.csye6220.kampus2go.model.Applicant;
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

	public List<Resume> findByFilter(String[] objectives, String experience, String[] degrees, String target) {
		return resumeDAO.findByFilter(objectives,experience,degrees,target);
	}

	public List<Resume> findByApplicant(Applicant applicant) {
		return resumeDAO.findByApplicant(applicant);
	}
	
	public List<Resume> findByMentor(Mentor mentor) {
		return resumeDAO.findByMentor(mentor);
	}
}
