package com.neu.csye6220.kampus2go.dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.neu.csye6220.kampus2go.model.User;

/**
 * @author pratiknakave
 *
 */
@Repository
public class UserDAO extends DAO {
	
	public User findByUsername(String username) {
		User user = null;
		try {
			begin();
			Query query = getSession().createQuery("from User where username= :username");
			query.setParameter("username", username);
			user = (User) query.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return user;
	}

}
