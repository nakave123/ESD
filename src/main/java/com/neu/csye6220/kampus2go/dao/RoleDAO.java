package com.neu.csye6220.kampus2go.dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.neu.csye6220.kampus2go.model.Role;
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
			Query q = getSession().createQuery("from Role where id= :id");
			q.setParameter("id", id);
			role = (Role) q.uniqueResult();
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
			Query q = getSession().createQuery("from Role where role= :roleName");
			q.setParameter("roleName", roleName);
			role = (Role) q.uniqueResult();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return role;
	}
}
