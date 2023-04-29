package com.neu.csye6220.kampus2go.dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.neu.csye6220.kampus2go.model.Role;

/**
 * @author pratiknakave
 *
 */
@Repository
public class RoleDAO extends DAO {
	public void create(Role role) {
		try {
			begin();
			getSession().save(role);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public Role findById(int id) {
		Role role = null;
		try {
			begin();
			Query query = getSession().createQuery("from Role where id= :id");
			query.setParameter("id", id);
			role = (Role) query.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return role;
	}
	
	public Role findByRoleName(String roleName) {
		Role role = null;
		try {
			begin();
			Query query = getSession().createQuery("from Role where role= :roleName");
			query.setParameter("roleName", roleName);
			role = (Role) query.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return role;
	}
}
