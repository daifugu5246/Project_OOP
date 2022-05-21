package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.ScaleTransition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class helloController extends TranferInfo{
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
	
	public void greeting() {
		greetingText.setText("Hello " + getFirstname() + " " + getLastname()+ " :)");
	}
	public void backToLogin(ActionEvent event) throws IOException {
		switchScene = new SceneController();
		switchScene.switchToLogin(event);
	}
	public void switchToEditProfile(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("editProfile.fxml"));
		Parent root = loader.load();
		EditProfileController edit = loader.getController();
		edit.takeUser(user);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("application.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
	}
}
