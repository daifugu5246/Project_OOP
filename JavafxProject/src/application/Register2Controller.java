package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Register2Controller implements Initializable {
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField  passwordField;
	@FXML
	private Text warningText;
	@FXML
	private Pane subPane;
	@FXML
	private DatePicker birthdate;
	@FXML
	private TextField firstname;
	@FXML 
	private TextField lastname;
	
	private SceneController switchScene;
	//private File file = null;
	//private PrintWriter writer = null;
	private FileOutputStream fout = null;
	private ObjectOutputStream oout = null;
	private FileInputStream fin = null;
	private ObjectInputStream oin = null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TranslateTransition translate = new TranslateTransition();
		FadeTransition fade = new FadeTransition();
		subPane.setLayoutX(0);
		translate.setNode(subPane);
		fade.setNode(subPane);
		translate.setDuration(Duration.seconds(1.3));
		translate.setByX(256);
		fade.setFromValue(0.2);
		fade.setToValue(1);
		fade.setDuration(Duration.seconds(1.3));
		fade.play();
		translate.play();
	}
	
	public void register(ActionEvent event){
		try {
			fin = new FileInputStream("resource/user.txt");
			oin = new ObjectInputStream(fin);
			@SuppressWarnings("unchecked")
			ArrayList<User> users = (ArrayList<User>) oin.readObject();
			if(mapUsername()) {
				if(usernameCheck(usernameField.getText()) && passwordCheck(passwordField.getText()) && infoCheck(firstname.getText(), lastname.getText(), birthdate.getEditor().getText())) {
					//System.out.println(usernameField.getText());
					User user = new User(usernameField.getText(),passwordField.getText(),firstname.getText(),lastname.getText(),birthdate.getValue());
					users.add(user);
					//System.out.println(user.getUsername());
					fout = new FileOutputStream("resource/user.txt");
					oout = new ObjectOutputStream(fout);
					oout.writeObject(users);
					warningText.setFill(Color.GREEN);
					warningText.setText("Register success.");
				}
			}
			else {
				warningText.setFill(Color.RED);
				warningText.setText("*this username have been used.");
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
	public void backToLogin(ActionEvent event) throws IOException {
		switchScene = new SceneController();
		switchScene.switchToLogin(event);
	}
	public boolean usernameCheck(String username) {
		if(username.isBlank() || username.isEmpty() || username.length() < 3) {
			warningText.setText("*username should be 3 or more characters.");
			return false;
		}else {
			boolean check = true;
			for(int i = 0 ; i < username.length();i++) {
				if(username.charAt(i) == ' ') {
					check = false;
					warningText.setText("*username can not have a space.");
					break;
				}
			}
			return check;
		}
	}
	public boolean passwordCheck(String password) {
		if(password.isBlank() || password.isEmpty() || password.length() < 8 || password.length() > 12) {
			warningText.setText("*password should be 8-12 characters.");
			return false;
		}else {
			boolean check = true;
			for(int i = 0 ; i < password.length();i++) {
				if(password.charAt(i) == ' ') {
					check = false;
					warningText.setText("*username can not have a space.");
					break;
				}
			}
			return check;	
		}
	}
	public boolean infoCheck(String firstname,String lastname,String birthdate) {
		if(firstname.isBlank() || firstname.isEmpty()) {
			warningText.setText("*please enter your firstname");
			return false;
		}else if(lastname.isBlank() || lastname.isEmpty()) {
			warningText.setText("*please enter your lastname");
			return false;
		}else if(birthdate.isBlank() || birthdate.isEmpty()) {
			warningText.setText("*please enter your birthdate");
			return false;
		}else {
			return true;
		}	
	}
	public boolean  mapUsername() {
		boolean check = true;
		try {
			fin = new FileInputStream("resource/user.txt");
			oin = new ObjectInputStream(fin);
			@SuppressWarnings("unchecked")
			ArrayList<User> users = (ArrayList<User>) oin.readObject();
			for(int i = 0 ; i < users.size(); i++) {
				if(usernameField.getText().equals(users.get(i).getUsername())) {
					check = false;
					break;
				}
			}
			fin.close();
			oin.close();
			return check;
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
		//return true;
	}
	public void cleanWarning(KeyEvent type) {
		warningText.setText("");
	}
}