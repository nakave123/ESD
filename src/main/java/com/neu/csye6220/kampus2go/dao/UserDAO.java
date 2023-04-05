package com.neu.csye6220.kampus2go.dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.neu.csye6220.kampus2go.model.User;

@Repository
public class UserDAO extends DAO {
	
	public User findByUsername(String username) {
		User user = null;
		try {
			begin();
			Query q = getSession().createQuery("from User where username= :username");
			q.setParameter("username", username);
			user = (User) q.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return user;
	}

}
