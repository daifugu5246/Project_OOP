package application;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private LocalDate birthdate;
	User(){
		
	}
	User(String username,String password,String firstname,String lastname,LocalDate birthdate){
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthdate = birthdate;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getFirstname() {
		return firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	
}