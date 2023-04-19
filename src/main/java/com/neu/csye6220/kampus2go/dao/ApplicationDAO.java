package com.neu.csye6220.kampus2go.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.neu.csye6220.kampus2go.model.Applicant;
import com.neu.csye6220.kampus2go.model.Application;

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

	public boolean exists(int applicantId, int positionId) {
		Application application = null;
		try {
			begin();
			Criteria crit = getSession().createCriteria(Application.class);
			
			Criteria applicant = crit.createCriteria("applicant");
			applicant.add(Restrictions.eq("id", applicantId));
			
			Criteria position = crit.createCriteria("position");
			position.add(Restrictions.eq("id", positionId));
			
			application = (Application)crit.uniqueResult();
			commit();
			//close();
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

	public List<Application> findByPosition(int positionId) {
		List<Application> applications = null;
		try {
			begin();
			Criteria crit = getSession().createCriteria(Application.class);
			Criteria position = crit.createCriteria("position");
			position.add(Restrictions.eq("id", positionId));
			crit.addOrder(Order.asc("applyDate"));
			applications = crit.list();
			commit();
			//close();
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
			Query q = getSession().createQuery("from Application where id =:id");
			q.setParameter("id", id);
			application = (Application)q.uniqueResult();
			commit();
			//close();
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
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		
	}

	public List<Application> findByApplicant(Applicant applicant) {
		List<Application> applications = null;
		try {
			begin();
			Query q = getSession().createQuery("from Application where applicant =:applicant order by applyDate asc");
			q.setParameter("applicant", applicant);
			applications = q.list();
			System.out.println("dao:"+applications);
			commit();
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return applications;
	}

}

