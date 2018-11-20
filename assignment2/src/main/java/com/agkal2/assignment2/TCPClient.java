/*
	Name:	Abdul Ghaffar Kalhoro
	Class:	BSCS-6C
	Registration#	194699

*/


//Client Side

package com.agkal2.assignment2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

class TCPClient {
	
	//main function
	@SuppressWarnings("static-access")
	public static void main(String argv[]) throws Exception {
	
		Person person;
		ObjectMapper objectMapper = new ObjectMapper();
		String objtojson, getdata;
		PersonFactory factory = new PersonFactory();
		Socket clientSocket = new Socket("127.0.0.1", 5000);

		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		System.out.println("~~~~Client side input system~~~~");
		Scanner input = new Scanner(System.in);

		System.out.println("1. Add a record" + "\n2. Delete record\n" + 
		"3. update a record" + "\n4. display a record"
				+ "\n5. EXIT");

		System.out.print("Enter from above numbers choice: ");
		int temp = input.nextInt();

		switch (temp) {
		case 1:
			System.out.print("Enter first Name: ");
			String firstName = input.next();

			System.out.print("Enter last Name: ");
			String lastName = input.next();

			System.out.print("Enter mobile number ");
			String mobile = input.next();

			System.out.print("Enter username: ");
			String username = input.next();

			System.out.print("Enter password: ");
			String password = input.next();

			System.out.print("Enter access level: ");
			String accessLevel = input.next();

			person = factory.getObjectStd(firstName, lastName, mobile, username, password, accessLevel);

			objtojson = objectMapper.writeValueAsString(person);
			outToServer.writeBytes("post:" + objtojson + '\n');
			String ssss;
			ssss = inFromServer.readLine();

			System.out.println("server response:-   " + ssss);
			break;
		case 2:
			System.out.println("Enter username to delete:  ");
			String deleteinput = input.next();

			objtojson = objectMapper.writeValueAsString(deleteinput);
			outToServer.writeBytes("delete:{" + objtojson + "}" + '\n');
			System.out.println("server response:-   " + inFromServer.readLine());
			break;

		case 3:
			System.out.println("Enter username to update record:  ");
			getdata = input.next();

			person = factory.getObjectStd("firstName", "lastName", "mobile", getdata, "password", "accessLevel");

			objtojson = objectMapper.writeValueAsString(person);
			System.out.println("Print at client side: " + objtojson);
			outToServer.writeBytes("put:" + objtojson + '\n');
			System.out.println("server response:-   " + inFromServer.readLine());

			break;
		case 4:
			System.out.println("Enter username to fetch data:  ");
			getdata = input.next();
			objtojson = objectMapper.writeValueAsString(getdata);
			outToServer.writeBytes("get:{" + objtojson + "}" + '\n');
			System.out.println("server response:-   " + inFromServer.readLine());
			break;
		case 5:
			break;
		default:
			System.out.println("Out of scope number..");
		}
		System.out.println("closed....!");
		clientSocket.close();
	}
}