package com.neu.csye6220.kampus2go.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.neu.csye6220.kampus2go.model.Employee;
import com.neu.csye6220.kampus2go.model.Mentor;
import com.neu.csye6220.kampus2go.model.Position;

@Repository
@Transactional
public class EmployeeDAO extends DAO{
	
	public void insertNewUser(Employee mentor) {
		try {
			begin();
			getSession().save(mentor);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
	
	public List<Employee> getAllEmployees() {
		List<Employee> employees = null;
		try {
			begin();
			Query q = getSession().createQuery("from Employee");
			employees = q.list();
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		return employees;
	}
	
	public void update(Employee position) {
		try {
			begin();
			getSession().update(position);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
	}
}
