package com.neu.csye6220.kampus2go.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.neu.csye6220.kampus2go.model.Mentee;

/**
 * @author pratiknakave
 *
 */
@Repository
public class MenteeDAO extends DAO {
	
	public void create(Mentee mentee) {
		try {
			begin();
			getSession().save(mentee);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}

	public Mentee findByUsername(String username) {
		Mentee mentee = null;
		try {
			begin();
			Query query = getSession().createQuery("from Mentee where username= :username");
			query.setParameter("username",username);
			mentee = (Mentee)query.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return mentee;
	}
	
	public List<Mentee> findByMentor(int mentor_id) {
		List<Mentee> mentees = null;
		try {
			begin();
			Query query = getSession().createQuery("from Mentee where mentor_id= :mentor_id");
			query.setParameter("mentor_id",mentor_id);
			mentees = query.list();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return mentees;
	}
	
	public void merge(Mentee mentee) {
		try {
			begin();
			getSession().merge(mentee);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public void delete(Mentee mentee) {
		try {
			begin();
			getSession().delete(mentee);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}

}

