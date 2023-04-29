package com.neu.csye6220.kampus2go.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.neu.csye6220.kampus2go.model.Mentee;
import com.neu.csye6220.kampus2go.model.Mentor;
import com.neu.csye6220.kampus2go.model.Resume;

/**
 * @author pratiknakave
 *
 */
@Repository
public class ResumeDAO extends DAO {
	public void create(Resume resume) {
		try {
			begin();
			getSession().save(resume);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}

	public List<Resume> list() {
		List<Resume> resumes = null;
		try {
			begin();
			Query query = getSession().createQuery("from Resume order by createDate desc");
			resumes = query.list();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return resumes;
	}

	public Resume findById(int id) {
		Resume resume = null;
		try {
			begin();
			Query query = getSession().createQuery("from Resume where id =: id");
			query.setParameter("id", id);
			resume = (Resume) query.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return resume;

	}

	public List<Resume> findByFilter(String[] objectives, String experience, String[] degrees) {
		List<Resume> resumes = null;
		try {
			begin();
			Criteria crit = getSession().createCriteria(Resume.class);
			
			// Create CriteriaBuilder
			//CriteriaBuilder builder = (CriteriaBuilder) getSession().getCriteriaBuilder();
			
			// Create CriteriaQuery
			//CriteriaQuery<Resume> criteria = (CriteriaQuery<Resume>) builder.createQuery(Resume.class);
			
			
			if (objectives != null && objectives.length > 0) {
				Criterion objectivesIn = Restrictions.in("objective", objectives);
				crit.add(objectivesIn);
			}
			if (!(experience == null || experience.isEmpty())) {
				switch (experience) {
				case "0":
					crit.add(Restrictions.le("yearsOfExperience", 1));
					break;
				case "1":
					crit.add(Restrictions.ge("yearsOfExperience", 1));
					crit.add(Restrictions.le("yearsOfExperience", 3));
					break;
				case "3":
					crit.add(Restrictions.ge("yearsOfExperience", 3));
					crit.add(Restrictions.le("yearsOfExperience", 5));
					break;
				case "5":
					crit.add(Restrictions.ge("yearsOfExperience", 5));
					break;
				}
			}
			if (degrees != null && degrees.length > 0) {
				Criteria edu = crit.createCriteria("educations");
				edu.add(Restrictions.in("degree", degrees));
			}

			resumes = crit.list();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return resumes;
	}

	public List<Resume> findByMentee(Mentee mentee) {
		List<Resume> resumes = null;
		try {
			begin();
			Criteria crit = getSession().createCriteria(Resume.class);
			Criteria app = crit.createCriteria("mentee");
			app.add(Restrictions.eq("id", mentee.getId()));
			crit.addOrder(Order.desc("createDate"));
			resumes = crit.list();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return resumes;
	}
	
	public List<Resume> findByMentor(Mentor mentor) {
		List<Resume> resumes = null;
		try {
			begin();
			Criteria crit = getSession().createCriteria(Resume.class);
			Criteria men = crit.createCriteria("mentor");
			men.add(Restrictions.eq("id", mentor.getId()));
			crit.addOrder(Order.desc("createDate"));
			resumes = crit.list();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return resumes;
	}
}
