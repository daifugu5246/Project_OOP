package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public abstract class TranferInfo implements Initializable{
	protected User user;
	private String username;
	private String firstname;
	private String lastname;
	private Gender gender;
	private LocalDate birthdate;
	abstract public void initialize(URL arg0, ResourceBundle arg1);
	public String getUsername() {
		return username;
	}

	private void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	private void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	private void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}
	public User getUser() {
		return this.user;
	}
	private void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	
	public Gender getGender() {
		return gender;
	}
	private void setGender(Gender gender) {
		this.gender = gender;
	}
	public void takeUser(User user) {
		this.user = user;
		setUsername(user.getUsername());
		setFirstname(user.getFirstname());
		setLastname(user.getLastname());
		setBirthdate(user.getBirthdate());
		setGender(user.getGender());
		user.toString();
	}
}
