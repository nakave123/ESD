/**
 * 
 */
package com.neu.csye6220.kampus2go.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.neu.csye6220.kampus2go.model.TimeSlot;

/**
 * @author pratiknakave
 *
 */
@Repository
public class TimeSlotDAO extends DAO {

	public void create(List<TimeSlot> slots) {
		try {
			begin();
			for(TimeSlot slot:slots) {
				getSession().save(slot);
			}
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public List<TimeSlot> findByMentor(int mentor_id) {
		List<TimeSlot> slots = null;
		try {
			begin();
			Query query = getSession().createQuery("from TimeSlot where mentor_id= :mentor_id");
			query.setParameter("mentor_id",mentor_id);
			slots = query.list();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return slots;
	}
	
	public TimeSlot findById(int id) {
		TimeSlot slot = null;
		try {
			begin();
			Query query = getSession().createQuery("from TimeSlot where slot_id= :id");
			query.setParameter("id",id);
			slot = (TimeSlot)query.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return slot;
	}
	
	public void merge(TimeSlot slot) {
		try {
			begin();
			getSession().merge(slot);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public void delete(TimeSlot slot) {
		try {
			begin();
			getSession().delete(slot);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
}
