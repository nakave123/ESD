package com.neu.csye6220.kampus2go.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.neu.csye6220.kampus2go.model.Mentor;

/**
 * @author pratiknakave
 *
 */
@Repository
public class MentorDAO extends DAO {
	
	public void create(Mentor mentor) {
		try {
			begin();
			getSession().save(mentor);
			commit();
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}

	public Mentor findByUsername(String username) {
		Mentor mentor = null;
		try {
			begin();
			Query q = getSession().createQuery("from Mentor where username= :username");
			q.setParameter("username",username);
			mentor = (Mentor)q.uniqueResult();
			commit();
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return mentor;
	}
	
	public Mentor findById(int id) {
		Mentor mentor = null;
		try {
			begin();
			Query q = getSession().createQuery("from Mentor where mentor_id= :id");
			q.setParameter("id",id);
			mentor = (Mentor)q.uniqueResult();
			commit();
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return mentor;
	}
	
	public List<Mentor> list() {
		List<Mentor> mentors = null;
		try {
			begin();
			Query q = getSession().createQuery("from Mentor order by username desc");
			mentors = q.list();
			commit();
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return mentors;
	}
	
	public void merge(Mentor mentor) {
		try {
			begin();
			getSession().merge(mentor);
			commit();
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public void delete(Mentor mentor) {
		try {
			begin();
			getSession().delete(mentor);
			commit();
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}

}
