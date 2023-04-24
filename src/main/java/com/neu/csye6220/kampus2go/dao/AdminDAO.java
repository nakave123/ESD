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
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}

	public Admin findByUsername(String username) {
		Admin admin = null;
		try {
			begin();
			Query q = getSession().createQuery("from Admin where username= :username");
			q.setParameter("username",username);
			admin = (Admin)q.uniqueResult();
			commit();
			//close();
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
			//close();
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
			//close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
}

