package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.ScaleTransition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class helloController implements Initializable{
	@FXML
	private Label greetingText;
	@FXML
	private AnchorPane pane;
	private SceneController switchScene;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ScaleTransition scale = new ScaleTransition();
		scale.setNode(greetingText);
		scale.setFromX(0);
		scale.setFromY(0);
		scale.setToX(1);
		scale.setToY(1);
		scale.setDuration(Duration.seconds(1));
		scale.play();
	}
	public void greeting(String username) {
		greetingText.setText("Hello " + username + " :)");
	}
	public void backToLogin(ActionEvent event) throws IOException {
		switchScene = new SceneController();
		switchScene.switchToLogin(event);
	}
	
	
}
