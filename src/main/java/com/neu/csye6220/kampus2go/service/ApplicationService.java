package com.neu.csye6220.kampus2go.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.csye6220.kampus2go.dao.ApplicationDAO;
import com.neu.csye6220.kampus2go.dao.JobDAO;
import com.neu.csye6220.kampus2go.dao.ResumeDAO;
import com.neu.csye6220.kampus2go.model.Mentee;
import com.neu.csye6220.kampus2go.model.Application;
import com.neu.csye6220.kampus2go.model.Job;

/**
 * @author pratiknakave
 *
 */
@Service
public class ApplicationService {
	@Autowired
    private ResumeDAO resumeDAO;
	
	@Autowired
    private JobDAO jobDAO;
	
	@Autowired
    private ApplicationDAO applicationDAO;

	public boolean create(Mentee mentee, int resumeId, int jobId) {
		
		
		if(applicationDAO.exists(mentee.getId(),jobId)) {
			return false;
		}
		Job job = jobDAO.findById(jobId);
		Application application = new Application();
		application.setMentee(mentee);
		application.setResume(resumeDAO.findById(resumeId));
		application.setJob(job);
		application.setAdmin(job.getAdmin());
		application.setStatus("Pending Review");
		LocalDateTime localDate = LocalDateTime.now();
		application.setApplyDate(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm").format(localDate));
		applicationDAO.create(application);
		
		job.setNumOfApplications(job.getNumOfApplications()+1);
		jobDAO.update(job);
		return true;
	}

	public List<Application> findByJob(int jobId) {
		
		return applicationDAO.findByJob(jobId);
	}

	public void processApplication(String applicationId, String status, String message) {
		Application application = applicationDAO.findById(Integer.valueOf(applicationId));
		
		if(!status.isEmpty()) {
			application.setStatus(status);
			
			if(status.equals("send offer")) {
				Email email = new SimpleEmail();
				email.setHostName("smtp.googlemail.com");
				email.setSmtpPort(465);
				email.setAuthenticator(new DefaultAuthenticator("nakaveprarik@gmail.com", "Tibco@2020"));
				email.setSSLOnConnect(true);
				try {
					email.setFrom("nakavepratik@gmail.com");
					email.setSubject("Offer Letter");
					email.setMsg("This is an offer letter.");
					email.addTo(application.getResume().getEmail());
					email.send();
				} catch (EmailException e) {
					e.printStackTrace();
				}

			}
		}
		if(!message.isEmpty()) {
			application.setMessage(message);
		}
		LocalDateTime localDate = LocalDateTime.now();
		application.setLastUpdate(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm").format(localDate));
		applicationDAO.update(application);
		

	}

	public List<Application> findByMentee(Mentee mentee) {
		return applicationDAO.findByMentee(mentee);
	}

}
