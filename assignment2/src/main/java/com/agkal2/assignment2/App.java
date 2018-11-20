package com.agkal2.assignment2;

import java.awt.List;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hello world!
 *
 */

public class App {

	
	
	
	 public static void update(Person e) {
			Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Person.class);

			ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();

			SessionFactory sf = con.buildSessionFactory(reg);

			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
		
		         Person em = (Person) session.load(Person.class, e.getUsername());
		
		         em.setFirstName(e.getFirstName());
		
		         em.setPassword(e.getPassword());
		
		         session.getTransaction().commit();
		
		         session.close();
		
		         System.out.println("Successfully updated " + e.toString());
		
		  
		
		     }

	
	
	private static SessionFactory getSessionFactory() {
		// TODO Auto-generated method stub
		return null;
	}



	public static void main(String[] args) {
		System.out.println("Hello World!");

		Person obj = new Person();
		
		  obj.setFirstName("gahaar"); obj.setLastName("ffar"); obj.setMobile("23af32");
		  obj.setUsername("username2"); obj.setPassword("123445");
		  obj.setAccessLevel("superadmin");
		 
		//  App appobj = new App();
		//  appobj.update(obj);
		  
		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Person.class);

		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();

		SessionFactory sf = con.buildSessionFactory(reg);

		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
	

		// session.save(obj);

		 //session.update(obj);

		// session.saveOrUpdate(obj);

		// obj.setPassword("12345");
		// String searchId = "agkal123";//this is the key id for field being updated

		// create the delete query
		// String query = "delete from Person as p where p.username = :key";

		// session.createQuery(query).setString("key", searchId).executeUpdate();
		// String searchId = "agkal923";
		// String query = "from Person as p where p.username = :sId";

		// java.util.List list = session.createQuery(query).setString("sId",
		// searchId).list();

		// @SuppressWarnings("rawtypes")
		// Iterator iterator = ((java.util.List) list).iterator();

		// while (iterator.hasNext()) {//is next data exist? then loop
		// Person obje = (Person) iterator.next();//cast and assign next data to Person
		// type object

		// System.out.print(obje.getUsername() + "\t" + obje.getFirstName() + "\t" +
		// obje.getAccessLevel() + "\n");
		// }

		// session.delete(obj);

		// session.close();
		/*
		 * Query que=(Query) session.createQuery("UPDATE person\r\n" +
		 * "SET firstName = 'Alfred'\r\n" + "WHERE username = 'ag12'"); // +
		 * "update person set firstName=:n where username=:i");
		 * 
		 * // ((org.hibernate.Query) que).setParameter("n","ghaffi");
		 * //((org.hibernate.Query) que).setParameter("i","ag12");
		 * 
		 * int status=((org.hibernate.Query) que).executeUpdate();
		 * System.out.println(status);
		 */

		/*
		 * String searchId = "ag12";//this is the key id for field being updated String
		 * query = "UPDATE person SET :key  where username = ag12 ";
		 * 
		 * session.createQuery(query).setString("key", searchId).executeUpdate();
		 */

		String usetna = "satirangiRe";
		Query query = session.createQuery("UPDATE Person SET firstName = :a , lastName = :bb , mobile = :cc , password = :dd , accessLevel = :e WHERE username = :f");
		
		//Query query = session.createQuery("update Person set firstName = :a where username = :c");
		query.setParameter("a", usetna);
		query.setParameter("bb", "mama");
		query.setParameter("cc", "0123dcd");
		query.setParameter("dd", "hurray");
		query.setParameter("e", "sallumian");
		query.setParameter("f", "username");
		int result = query.executeUpdate();
		System.out.println(result);
		tx.commit();

	}
}
