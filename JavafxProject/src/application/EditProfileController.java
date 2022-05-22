package application;

import javafx.scene.control.TextField;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EditProfileController extends TranferInfo{
	@FXML
	private Pane subPane;
	@FXML
	private TextField firstnameField;
	@FXML
	private TextField lastnameField;
	@FXML
	private DatePicker birthdateField;
	@FXML
	private Text warningText; 
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
	public void setFeild() {
		firstnameField.setText(getFirstname());
		lastnameField.setText(getLastname());
		birthdateField.setValue(getBirthdate());
	}
	public void save(ActionEvent event) {
		if(infoCheck(firstnameField.getText(), lastnameField.getText(), birthdateField.getEditor().getText())) {
			try {
				fin = new FileInputStream("resource/user.txt");
				oin = new ObjectInputStream(fin);
				ArrayList<User> users = (ArrayList<User>) oin.readObject();
				for(int i = 0 ; i < users.size();i++) {
					if(users.get(i).getUsername().equals(getUsername())) {
						users.get(i).setFirstname(firstnameField.getText());
						user.setFirstname(firstnameField.getText());
						users.get(i).setLastname(lastnameField.getText());
						user.setLastname(lastnameField.getText());
						users.get(i).setBirthdate(birthdateField.getValue());
						user.setBirthdate(birthdateField.getValue());
					}
				}
				fout = new FileOutputStream("resource/user.txt");
				oout = new ObjectOutputStream(fout);
				oout.writeObject(users);
			}catch (Exception e) {
				System.err.println(e.getMessage());
			}finally {
				try {
					warningText.setFill(Color.GREEN);
					warningText.setText("information saved"); 
					fin.close();
					fout.close();
					oin.close();
					oout.close();
				}catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}		
	}
	public boolean infoCheck(String firstname,String lastname,String birthdate) {
		  warningText.setFill(Color.RED);
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
	public void switchToProfile(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
		Parent root = loader.load();
		profileController profile = loader.getController();
		profile.takeUser(user);
		profile.setInfo();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("application.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
	}
}
