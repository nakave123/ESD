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

import com.neu.csye6220.kampus2go.model.Position;
import com.neu.csye6220.kampus2go.model.Recruiter;

/**
 * @author pratiknakave
 *
 */
@Repository
public class PositionDAO extends DAO {

	public void create(Position position) {
		try {
			begin();
			getSession().save(position);
			commit();
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}

	public List<Position> list() {
		List<Position> positions = null;
		try {
			begin();
			Query q = getSession().createQuery("from Position order by postDate desc");
			positions = q.list();
			commit();
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return positions;
	}

	public List<Position> findByKeywords(String keywords) {
		List<Position> positions = null;
		try {
			begin();
			Criteria crit = getSession().createCriteria(Position.class);
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
			positions = crit.list();
			commit();
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return positions;
	}

	public Position findById(int id) {
		Position position = null;
		try {
			begin();
			Query q = getSession().createQuery("from Position where id =: id");
			q.setInteger("id", id);
			position = (Position) q.uniqueResult();
			commit();
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return position;
	}

	public List<Position> findByFilter(String[] categories, String[] jobTypes, String[] levels, String location) {
		List<Position> positions = null;
		try {
			begin();
			Criteria crit = getSession().createCriteria(Position.class);
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
			positions = crit.list();
			commit();
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return positions;
	}

	public void update(Position position) {
		try {
			begin();
			getSession().update(position);
			commit();
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}

	public List<Position> findByRecruiter(Recruiter recruiter) {
		List<Position> positions = null;
		try {
			begin();
			Criteria crit = getSession().createCriteria(Position.class);
			Criteria hr = crit.createCriteria("recruiter");
			hr.add(Restrictions.eq("id", recruiter.getId()));
			crit.addOrder(Order.desc("postDate"));
			positions = crit.list();
			commit();
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return positions;
	}
}
