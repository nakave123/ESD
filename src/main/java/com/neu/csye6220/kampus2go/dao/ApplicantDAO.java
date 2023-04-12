package com.neu.csye6220.kampus2go.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.neu.csye6220.kampus2go.model.Applicant;
import com.neu.csye6220.kampus2go.model.Position;

@Repository
public class ApplicantDAO extends DAO {
	
	public void create(Applicant applicant) {
		try {
			begin();
			getSession().save(applicant);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}

	public Applicant findByUsername(String username) {
		Applicant applicant = null;
		try {
			begin();
			Query q = getSession().createQuery("from Applicant where username= :username");
			q.setParameter("username",username);
			applicant = (Applicant)q.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return applicant;
	}
	
	public List<Applicant> findByMentor(int mentor_id) {
		List<Applicant> applicants = null;
		try {
			begin();
			Query q = getSession().createQuery("from Applicant where mentor_id= :mentor_id");
			q.setParameter("mentor_id",mentor_id);
			applicants = q.list();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return applicants;
	}
	
	public void merge(Applicant applicant) {
		try {
			begin();
			getSession().merge(applicant);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public void delete(Applicant applicant) {
		try {
			begin();
			getSession().delete(applicant);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}

}
