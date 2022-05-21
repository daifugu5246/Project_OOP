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
	private LocalDate birthdate;
	abstract public void initialize(URL arg0, ResourceBundle arg1);
	public String getUsername() {
		return username;
	}

	protected void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	protected void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	protected void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}
	public User getUser() {
		return this.user;
	}
	protected void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	public void takeUser(User user) {
		this.user = user;
		setUsername(user.getUsername());
		System.out.println(user.getUsername());
		setFirstname(user.getFirstname());
		System.out.println(user.getFirstname());
		setLastname(user.getLastname());
		System.out.println(user.getLastname());
		setBirthdate(user.getBirthdate());
	}
}
