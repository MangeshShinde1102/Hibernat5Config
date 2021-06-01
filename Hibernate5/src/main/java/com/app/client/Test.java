package com.app.client;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.app.hibernateUtility.HibernateUtil;
import com.app.model.Employee;

public class Test {
	public static void main(String[] args) {
		Test test = new Test();
//		test.save();
		Employee emp=test.getEmployeeById(5);
		System.out.println(emp);
		test.findAll();
	}

	public void save() {
		Employee employee=new Employee();
		employee.setName("Mangesh");
		Session session=HibernateUtil.getSessionFactory().openSession();
		session.save(employee);
		session.beginTransaction().commit();
		session.close();
		
	}
	public void findAll() {
		Session session=HibernateUtil.getSessionFactory().openSession();
		CriteriaQuery<Employee> cq=session.getCriteriaBuilder().createQuery(Employee.class);
		cq.from(Employee.class);
		List<Employee> emps=session.createQuery(cq).getResultList();
		emps.forEach(System.out::println);
	}
	public Employee getEmployeeById(int id) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder criteria=session.getCriteriaBuilder();
		CriteriaQuery<Employee> criteriaQuery=criteria.createQuery(Employee.class);
		
		Root<Employee> root=criteriaQuery.from(Employee.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteria.equal(root.get("id"), id));
		Employee employee=session.createQuery(criteriaQuery).getSingleResult();
		return employee;
	}
}
