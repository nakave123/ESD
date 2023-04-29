package com.neu.csye6220.kampus2go.dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.neu.csye6220.kampus2go.model.Admin;

/**
 * @author pratiknakave
 *
 */
@Repository
public class AdminDAO extends DAO {

	
	public void create(Admin admin) {
		try {
			begin();
			getSession().save(admin);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}

	public Admin findByUsername(String username) {
		Admin admin = null;
		try {
			begin();
			//query to Admin table by username
			Query query = getSession().createQuery("from Admin where username= :username");
			query.setParameter("username",username);
			admin = (Admin)query.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return admin;
	}
	
	public void merge(Admin admin) {
		try {
			begin();
			getSession().merge(admin);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public void delete(Admin admin) {
		try {
			begin();
			getSession().delete(admin);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
}

