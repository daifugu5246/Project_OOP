package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController{
	private Stage stage;
	private Scene scene;
	private Parent root;
	private FXMLLoader loader;
	
	public void switchToLogin(ActionEvent event) throws IOException {
		loader = new FXMLLoader(getClass().getResource("login.fxml"));
		root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = this.getClass().getResource("application.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setX(340);
        stage.setY(100);
        stage.setResizable(false);
        stage.show();
	}
	public void switchToRegister(ActionEvent event) throws IOException {
		loader = new FXMLLoader(getClass().getResource("test_register.fxml"));
		root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = this.getClass().getResource("application.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
	}
	public void switchToTable(ActionEvent event) throws IOException {
		loader = new FXMLLoader(getClass().getResource("Table.fxml"));
		root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = this.getClass().getResource("application.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
	}
	
}