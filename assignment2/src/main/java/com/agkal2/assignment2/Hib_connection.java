/*
	Name:	Abdul Ghaffar Kalhoro
	Class:	BSCS-6C
	Registration#	194699

*/


//this class is used to create database connection using hibernate
//and contain one method of void type that sets the session attribute after connection

package com.agkal2.assignment2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Hib_connection {

	public Session session;
	//method to establish mapping with hibernate.cfg.xml file
	public void getOpenSession() {
		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Person.class);

		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties())
				.buildServiceRegistry();

		SessionFactory sf = con.buildSessionFactory(reg);
		this.session = sf.openSession();
		
	}
}
