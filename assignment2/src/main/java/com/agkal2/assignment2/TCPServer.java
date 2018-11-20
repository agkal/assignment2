/*
	Name:	Abdul Ghaffar Kalhoro
	Class:	BSCS-6C
	Registration#	194699

*/

//Server side
package com.agkal2.assignment2;

import java.io.*;
import java.net.*;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class TCPServer implements Runnable {

	Socket csocket;

	public TCPServer() {
	};

	TCPServer(Socket csocket) {
		this.csocket = csocket;
	}

	public void stop() throws IOException {
		csocket.close();
	}

	public static void main(String argv[]) throws Exception {

		ServerSocket sokt_server = new ServerSocket(5000);
		System.out.println("Listening....");

		//TCPServer serverOBJ = new TCPServer();

		while (true) {
			Socket conn_sockt = sokt_server.accept();
			System.out.println("Connected");
			//starting the thread
			new Thread(new TCPServer(conn_sockt)).start();
			System.out.println("server closed.....!");
			sokt_server.close();
		}
	}
	//run method that starts at the begining of the thread
	public void run() {

		try {		
			
			TCPServer tcpObj = new TCPServer();
			String clientSentence;

			System.out.println("...connection established...");
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(csocket.getInputStream()));

			DataOutputStream outToClient = new DataOutputStream(csocket.getOutputStream());

			clientSentence = inFromClient.readLine();
			System.out.println("first received: " + clientSentence);
			String recname = clientSentence; // full file name
			String[] parts = recname.split("\\:", 2); // String array, each element is text between dots

			switch (parts[0]) {

			case "post":
				tcpObj.getPostData(outToClient, parts);
				break;

			case "put":			
				tcpObj.getPutData(outToClient, parts);
				break;
			case "delete":
				tcpObj.getDeleteData(outToClient, parts);
				break;
			case "get":
				tcpObj.getGetData(outToClient,parts);
				break;
			default:
				System.out.println("tag not found..!");

			}

			
		} catch (IOException e) {

		}
	}
	
	void getPostData(DataOutputStream outToClient, String[] parts) throws JsonParseException, JsonMappingException, IOException {
		
		try {
			Hib_connection hb_obj = new Hib_connection();
			hb_obj.getOpenSession();
			ObjectMapper objectMapper = new ObjectMapper();
			Transaction tx;
			
			System.out.println("post started...");
			Person jsontoobj = objectMapper.readValue(parts[1], Person.class);
			tx = hb_obj.session.beginTransaction();
			hb_obj.session.save(jsontoobj);
			tx.commit();
			String mess = "success: {" + jsontoobj.getUsername() + "} successfully registered." + '\n';
			System.out.println(mess);
			outToClient.writeBytes(mess);
			System.out.println("posting is completed...!");
		} catch (ConstraintViolationException e) {

			System.out.println("duplicate username found...");
			String mess = "error: duplicate username found..." + '\n';

			System.out.println(mess);
			outToClient.writeBytes(mess);

		}
		
		
	}
	
	void getPutData(DataOutputStream outToClient, String[] parts) throws JsonParseException, JsonMappingException, IOException {
		Hib_connection hb_obj = new Hib_connection();
		hb_obj.getOpenSession();
		ObjectMapper objectMapper = new ObjectMapper();
		Transaction tx;
		
		Person jsontoobj34 = objectMapper.readValue(parts[1], Person.class);	
		tx = hb_obj.session.beginTransaction();
		Query query2 = hb_obj.session.createQuery("UPDATE Person SET firstName = :a , lastName = :bb , mobile = :cc , password = :dd , accessLevel = :e WHERE username = :f");

		query2.setParameter("a", jsontoobj34.getFirstName());
		query2.setParameter("bb", jsontoobj34.getLastName());
		query2.setParameter("cc", jsontoobj34.getMobile());
		query2.setParameter("dd", jsontoobj34.getPassword());
		query2.setParameter("e", jsontoobj34.getAccessLevel());
		query2.setParameter("f", jsontoobj34.getUsername());
		
		int result = query2.executeUpdate();
		if(result == 0) {
			String mess = "can not find user name.." + '\n';
			outToClient.writeBytes(mess);
			
		}else {
			String mess = "successfully updated.." + '\n';
			outToClient.writeBytes(mess);
			
		}
		tx.commit();


		
		
		
	}
	
	void getDeleteData(DataOutputStream outToClient, String[] parts) throws JsonParseException, JsonMappingException, IOException {
		Hib_connection hb_obj = new Hib_connection();
		hb_obj.getOpenSession();
		Transaction tx;
		
		System.out.println("name to be deleted: " + parts[1]);
		String s = parts[1];
		s = s.replaceAll("\\p{P}", "");
		tx = hb_obj.session.beginTransaction();
		// create the delete query
		String query = "delete from Person as p where p.username = :key";
		hb_obj.session.createQuery(query).setString("key", s).executeUpdate();

		tx.commit();

		String mess = "deleted username:[" + s + "] successfully" + '\n';

		System.out.println(mess);
		outToClient.writeBytes(mess);
		
		
	}	
	
	void getGetData(DataOutputStream outToClient, String[] parts) throws JsonParseException, JsonMappingException, IOException {
		Hib_connection hb_obj = new Hib_connection();
		hb_obj.getOpenSession();
		Transaction tx;
		ObjectMapper objectMapper = new ObjectMapper();
		
		String objtojson;
		String s2 = parts[1];
		s2 = s2.replaceAll("\\p{P}", "");
		tx = (hb_obj.session).beginTransaction();

		String searchId = s2;
		String query3 = "from Person as p where p.username = :sId";

		@SuppressWarnings("rawtypes") 
		java.util.List list = hb_obj.session.createQuery(query3).setString("sId", searchId).list();

		@SuppressWarnings("rawtypes")
		Iterator iterator = ((java.util.List) list).iterator();
		if (iterator.hasNext()) {
			while (iterator.hasNext()) {// is next data exist? then loop
				Person obje = (Person) iterator.next();// cast and assign next data to Person type object
				objtojson = objectMapper.writeValueAsString(obje);
				outToClient.writeBytes("success:" + objtojson + '\n');
			}
		} else {

			String mess1 = "error:{" + s2 + "} not found.." + '\n';
			outToClient.writeBytes(mess1);
			// System.out.println("enter correct user name..");

		}
		
		
	}
}
