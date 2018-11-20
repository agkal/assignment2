/*
	Name:	Abdul Ghaffar Kalhoro
	Class:	BSCS-6C
	Registration#	194699

*/

//this is the Person class that contains the attributes and getter and setter methods.
package com.agkal2.assignment2;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {

	//attributes
	@Id
	private String firstName;
	private String lastName;
	private String mobile;
	private String username;
	private String password;
	private String accessLevel;
	
	public Person() {};
	
	public Person(String firstName, String lastName, String mobile, String username, String password,
			String accessLevel) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.username = username;
		this.password = password;
		this.accessLevel = accessLevel;
	}
	//getter and setter methods
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}
	
}
