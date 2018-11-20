/*
	Name:	Abdul Ghaffar Kalhoro
	Class:	BSCS-6C
	Registration#	194699

*/
//used to create the object of the person class.
package com.agkal2.assignment2;

public class PersonFactory {
	public static Person getObjectStd(String firstName, String lastName, String mobile, String userName, String password, String accessLevel) {
		
		return new Person(firstName, lastName, mobile, userName, password, accessLevel);
	//returns the student object with initialised attributes
	}
}