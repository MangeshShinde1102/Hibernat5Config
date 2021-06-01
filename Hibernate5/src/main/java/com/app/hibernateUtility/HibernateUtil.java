package com.app.hibernateUtility;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import com.app.model.Employee;

public class HibernateUtil {

	public static SessionFactory getSessionFactory() {
		Properties properties=new Properties();
		                                                                                                                                                                  
		properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
		properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/test");
		properties.setProperty("hibernate.connection.username", "root");
		properties.setProperty("hibernate.connection.password", "root");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.setProperty("hibernate.id.new_generator_mappings","false");
//		properties.setProperty("hibernate.connection.hbm2ddl", "update");
		
		StandardServiceRegistryBuilder serviceRegistryBuilder=new StandardServiceRegistryBuilder();
		serviceRegistryBuilder.applySettings(properties);
		ServiceRegistry service=serviceRegistryBuilder.build();
		
		MetadataSources sources=new MetadataSources(service).addAnnotatedClass(Employee.class);
		Metadata data=sources.getMetadataBuilder().build();
		
		SessionFactory sessionFactory=data.getSessionFactoryBuilder().build();
		return sessionFactory;
	}
}
