package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class RegisterController implements Initializable{
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField  passwordField;
	@FXML
	private Text warningText;
	@FXML
	private Pane subPane;
	
	private SceneController switchScene;
	private File file = new File("resource/user.txt");
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TranslateTransition translate = new TranslateTransition();
		FadeTransition fade = new FadeTransition();
		subPane.setLayoutX(0);
		translate.setNode(subPane);
		fade.setNode(subPane);
		translate.setDuration(Duration.seconds(1.3));
		translate.setByX(319);
		fade.setFromValue(0.2);
		fade.setToValue(1);
		fade.setDuration(Duration.seconds(1.3));
		fade.play();
		translate.play();
	}
	
	public void register(ActionEvent event) throws IOException {
		try (PrintWriter writer = new PrintWriter(new FileWriter(file,true))) {
			if(mapUsername()) {
				if(usernameCheck(usernameField.getText()) && passwordCheck(passwordField.getText())) {
					writer.print(usernameField.getText()+"/");
					writer.print(passwordField.getText()+"/");
					warningText.setFill(Color.GREEN);
					warningText.setText("Register success.");
				}
			}
			else {
				warningText.setFill(Color.RED);
				warningText.setText("*this username have been used.");
			}
		}catch(Exception ex){
			System.out.println(ex);
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
	public boolean  mapUsername() {
		try(Scanner reader = new Scanner(file)){
			reader.useDelimiter("/");
			boolean check = true;
			while(reader.hasNext()) {
				if(usernameField.getText().equals(reader.next())) {
					check = false;
					break;
				}
				if(reader.hasNext()) {
					reader.next();	
				}
			}
			return check;
		}catch(Exception ex) {
			System.out.println(ex);
			return false;
		}
	}
	public void cleanWarning(KeyEvent type) {
		warningText.setText("");
	}
}
