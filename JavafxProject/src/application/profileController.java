package application;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class profileController extends TranferInfo{
	@FXML
	private ImageView profile;
	@FXML
	private Label firstnameLabel;
	@FXML
	private Label lastnameLabel;
	@FXML
	private Label birthdateLabel;
	@FXML
	private Pane subPane;
	private SceneController switchScene;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Animation
		TranslateTransition translate = new TranslateTransition();
		FadeTransition fade = new FadeTransition();
		subPane.setLayoutX(192);
		translate.setNode(subPane);
		fade.setNode(subPane);
		fade.setFromValue(0.2);
		fade.setToValue(1);
		fade.setDuration(Duration.seconds(0.8));
		fade.play();
		translate.play();
	}
	public void setInfo() {
		//setInfo to show on profile scene
		Image male = new Image("application/img/male.png");
		Image female = new Image("application/img/female.png");
		Image prefNotSay = new Image("application/img/prefer_not_to_say.png");
		String dateFormat = getBirthdate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		firstnameLabel.setText(getFirstname());
		lastnameLabel.setText(getLastname());
		birthdateLabel.setText(" Birthdate\n" + dateFormat);
		switch(getGender()) {
		case MALE:
			profile.setImage(male);
			break;
		case FEMALE:
			profile.setImage(female);
			break;
		case PREFER_NOT_TO_SAY :
			profile.setImage(prefNotSay);
			break;
		default:
			profile.setImage(prefNotSay);
			break;
		}
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
		edit.setFeild();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("application.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
	}
	public void switchToEditVaccine(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("vaccine.fxml"));
		Parent root = loader.load();
		vaccineController vacrec = loader.getController();
		vacrec.takeUser(user);
		vacrec.setTable();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("application.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
	}
}
