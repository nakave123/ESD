package com.neu.csye6220.kampus2go.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.neu.csye6220.kampus2go.model.Mentor;
import com.neu.csye6220.kampus2go.model.Resume;

@Repository
public class MentorDAO extends DAO {
	
	public void create(Mentor mentor) {
		try {
			begin();
			getSession().save(mentor);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}

	public Mentor findByUsername(String username) {
		Mentor applicant = null;
		try {
			begin();
			Query q = getSession().createQuery("from Mentor where username= :username");
			q.setParameter("username",username);
			applicant = (Mentor)q.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return applicant;
	}
	
	public List<Mentor> list() {
		List<Mentor> mentors = null;
		try {
			begin();
			Query q = getSession().createQuery("from Mentor order by username desc");
			mentors = q.list();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return mentors;
	}

}
