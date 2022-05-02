package application;

import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Timer;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class loginController implements Initializable{
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Text warningText;
	@FXML
	private AnchorPane pane;
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
	public void login(ActionEvent event) throws IOException {
		try(Scanner reader = new Scanner(file)){
			reader.useDelimiter("/");
			while(reader.hasNext()) {
				String username = reader.next();
				String password = reader.next();
				if( ((usernameField.getText()).equals(username)) && ((passwordField.getText()).equals(password)) ) {
					switchToHello(event);
					break;
				}
				else if( ((usernameField.getText()).equals(username))== true && ((passwordField.getText()).equals(password)) == false ) {
					warningText.setText("*username mismatch with the password.");
					break;
				}
				else if(usernameField.getText().isBlank() || usernameField.getText().isEmpty() 
						|| passwordField.getText().isBlank() || passwordField.getText().isEmpty()) {
					
				}
				else {
					warningText.setText("*username not found.");
				}
			}
		}catch(Exception ex) {
			
		}
	}
	public void register(ActionEvent event) throws IOException {
		switchScene = new SceneController();
		switchScene.switchToRegister(event);
	}
	
	public void switchToHello(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("hello.fxml"));
		Parent root = loader.load();
		helloController hello = loader.getController();
		String username = usernameField.getText();
		hello.greeting(username);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("application.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
	}
	public void cleanWarning(KeyEvent type) {
		warningText.setText("");
	}
}