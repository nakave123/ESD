package com.neu.csye6220.kampus2go.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.neu.csye6220.kampus2go.dao.ApplicationDAO;
import com.neu.csye6220.kampus2go.dao.PositionDAO;
import com.neu.csye6220.kampus2go.dao.ResumeDAO;
import com.neu.csye6220.kampus2go.model.Applicant;
import com.neu.csye6220.kampus2go.model.Application;
import com.neu.csye6220.kampus2go.model.Position;

@Service
public class ApplicationService {
	@Autowired
    private ResumeDAO resumeDAO;
	
	@Autowired
    private PositionDAO positionDAO;
	
	@Autowired
    private ApplicationDAO applicationDAO;

	public boolean create(Applicant applicant, int resumeId, int positionId) {
		
		
		if(applicationDAO.exists(applicant.getId(),positionId)) {
			return false;
		}
		Position position = positionDAO.findById(positionId);
		Application application = new Application();
		application.setApplicant(applicant);
		application.setResume(resumeDAO.findById(resumeId));
		application.setPosition(position);
		application.setRecruiter(position.getRecruiter());
		application.setStatus("Pending Review");
		LocalDateTime localDate = LocalDateTime.now();
		application.setApplyDate(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm").format(localDate));
		applicationDAO.create(application);
		
		position.setNumberOfApplications(position.getNumberOfApplications()+1);
		positionDAO.update(position);
		return true;
	}

	public List<Application> findByPosition(int positionId) {
		
		return applicationDAO.findByPosition(positionId);
	}

	public void processApplication(String applicationId, String status, String message) {
		Application application = applicationDAO.findById(Integer.valueOf(applicationId));
		
		if(!status.isEmpty()) {
			application.setStatus(status);
			
			if(status.equals("send offer")) {
				Email email = new SimpleEmail();
				email.setHostName("smtp.googlemail.com");
				email.setSmtpPort(465);
				email.setAuthenticator(new DefaultAuthenticator("tanmeng.job@gmail.com", "you never know"));
				email.setSSLOnConnect(true);
				try {
					email.setFrom("auto-send@jobboard.com");
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

	public List<Application> findByApplicant(Applicant applicant) {
		return applicationDAO.findByApplicant(applicant);
	}

}
