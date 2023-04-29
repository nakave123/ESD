package com.neu.csye6220.kampus2go.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.neu.csye6220.kampus2go.model.Mentee;
import com.neu.csye6220.kampus2go.model.Application;

/**
 * @author pratiknakave
 *
 */
@Repository
public class ApplicationDAO extends DAO {
	
	public void create(Application application) {
		try {
			begin();
			getSession().save(application);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}

	public boolean exists(int menteeId, int jobId) {
		Application application = null;
		try {
			begin();
			Criteria crit = getSession().createCriteria(Application.class);
			
			//query to check if mentee has already applied to job created
			Criteria mentee = crit.createCriteria("mentee");
			mentee.add(Restrictions.eq("id", menteeId));
			
			Criteria job = crit.createCriteria("job");
			job.add(Restrictions.eq("id", jobId));
			
			application = (Application)crit.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		if(application == null) {
			return false;
		}else {
			return true;
		}
		
	}

	public List<Application> findByJob(int jobId) {
		List<Application> applications = null;
		try {
			begin();
			Criteria crit = getSession().createCriteria(Application.class);
			
			//query to find job by Id and order by apply date
			Criteria job = crit.createCriteria("job");
			job.add(Restrictions.eq("id", jobId));
			crit.addOrder(Order.asc("applyDate"));
			applications = crit.list();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return applications;
	}

	public Application findById(int id) {
		Application application = null;
		try {
			begin();
			
			//query to Application table by id
			Query query = getSession().createQuery("from Application where id =:id");
			query.setParameter("id", id);
			application = (Application)query.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return application;
	}

	public void update(Application application) {
		try {
			begin();
			getSession().update(application);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		
	}

	public List<Application> findByMentee(Mentee mentee) {
		List<Application> applications = null;
		try {
			begin();
			
			//query to Application table by mentee and order by apply date
			Query query = getSession().createQuery("from Application where mentee =:mentee order by applyDate asc");
			query.setParameter("mentee", mentee);
			applications = query.list();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return applications;
	}

}

