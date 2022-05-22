package application;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.ObservableList;

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
	private Gender gender;
	private ArrayList<Vaccine> vaccines;
	User(){
		
	}
	User(String username,String password,String firstname,String lastname,LocalDate birthdate,Gender gender){
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthdate = birthdate;
		this.gender = gender;
	}
	public String getUsername() {
		return username;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
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
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public ArrayList<Vaccine> getVaccines() {
		return vaccines;
	}
	public void setVaccines(ArrayList<Vaccine> vaccines) {
		this.vaccines = vaccines;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", firstname=" + firstname + ", lastname="
				+ lastname + ", birthdate=" + birthdate + ", gender=" + gender + ", vaccines=" + vaccines + "]";
	}
	
	
}