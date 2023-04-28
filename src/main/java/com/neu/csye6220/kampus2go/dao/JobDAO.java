package com.neu.csye6220.kampus2go.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.neu.csye6220.kampus2go.model.Job;
import com.neu.csye6220.kampus2go.model.Admin;

/**
 * @author pratiknakave
 *
 */
@Repository
public class JobDAO extends DAO {

	public void create(Job job) {
		try {
			begin();
			getSession().save(job);
			commit();
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}

	public List<Job> list() {
		List<Job> jobs = null;
		try {
			begin();
			Query q = getSession().createQuery("from Job order by postDate desc");
			jobs = q.list();
			commit();
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return jobs;
	}

	public List<Job> findByKeywords(String keywords) {
		List<Job> jobs = null;
		try {
			begin();
			Criteria crit = getSession().createCriteria(Job.class);
			if (!(keywords == null || keywords.isEmpty())) {
				Criterion title = Restrictions.ilike("title", keywords, MatchMode.ANYWHERE);
				Criterion company = Restrictions.ilike("company", keywords, MatchMode.ANYWHERE);
				Criterion responsibilities = Restrictions.ilike("responsibilities", keywords, MatchMode.ANYWHERE);
				Criterion qualifications = Restrictions.ilike("qualifications", keywords, MatchMode.ANYWHERE);
				Disjunction disjunction = Restrictions.disjunction();
				disjunction.add(title);
				disjunction.add(company);
				disjunction.add(responsibilities);
				disjunction.add(qualifications);
				crit.add(disjunction);
			}
			crit.addOrder(Order.desc("postDate"));
			jobs = crit.list();
			commit();
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return jobs;
	}

	public Job findById(int id) {
		Job job = null;
		try {
			begin();
			Query q = getSession().createQuery("from Job where id =: id");
			q.setInteger("id", id);
			job = (Job) q.uniqueResult();
			commit();
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return job;
	}

	public List<Job> findByFilter(String[] categories, String[] jobTypes, String[] levels, String location) {
		List<Job> jobs = null;
		try {
			begin();
			Criteria crit = getSession().createCriteria(Job.class);
			if (categories != null && categories.length > 0) {
				Criterion categoriesIn = Restrictions.in("category", categories);
				crit.add(categoriesIn);
			}
			if (jobTypes != null && jobTypes.length > 0) {
				Criterion jobTypesIn = Restrictions.in("jobType", jobTypes);
				crit.add(jobTypesIn);
			}
			if (levels != null && levels.length > 0) {
				Criterion levelsIn = Restrictions.in("level", levels);
				crit.add(levelsIn);
			}
			if (!(location == null || location.isEmpty())) {
				Criterion city = Restrictions.ilike("city", location, MatchMode.ANYWHERE);
				Criterion state = Restrictions.ilike("state", location, MatchMode.ANYWHERE);
				LogicalExpression locationIn = Restrictions.or(city, state);
				crit.add(locationIn);
			}
			crit.addOrder(Order.desc("postDate"));
			jobs = crit.list();
			commit();
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return jobs;
	}

	public void update(Job job) {
		try {
			begin();
			getSession().update(job);
			commit();
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}

	public List<Job> findByAdmin(Admin admin) {
		List<Job> jobs = null;
		try {
			begin();
			Criteria crit = getSession().createCriteria(Job.class);
			Criteria hr = crit.createCriteria("admin");
			hr.add(Restrictions.eq("id", admin.getId()));
			crit.addOrder(Order.desc("postDate"));
			jobs = crit.list();
			commit();
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return jobs;
	}
}
