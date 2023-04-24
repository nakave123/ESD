package com.neu.csye6220.kampus2go.dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.neu.csye6220.kampus2go.model.Recruiter;

/**
 * @author pratiknakave
 *
 */
@Repository
public class RecruiterDAO extends DAO {

	
	public void create(Recruiter recruiter) {
		try {
			begin();
			getSession().save(recruiter);
			commit();
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}

	public Recruiter findByUsername(String username) {
		Recruiter recruiter = null;
		try {
			begin();
			Query q = getSession().createQuery("from Recruiter where username= :username");
			q.setParameter("username",username);
			recruiter = (Recruiter)q.uniqueResult();
			commit();
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return recruiter;
	}
	
	public void merge(Recruiter recruiter) {
		try {
			begin();
			getSession().merge(recruiter);
			commit();
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public void delete(Recruiter recruiter) {
		try {
			begin();
			getSession().delete(recruiter);
			commit();
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
}

