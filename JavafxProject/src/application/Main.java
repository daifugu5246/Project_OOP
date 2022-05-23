package application;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
    public void start(Stage primaryStage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("application.css").toExternalForm();
        Image icon = new Image("application/img/vaccine.png"); 
        scene.getStylesheets().add(css);
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Covid-19 Application");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) throws ClassNotFoundException, IOException {
    	//All data print
    	/*
    	FileInputStream fin = new FileInputStream("resource/user.txt");
    	ObjectInputStream oin = new ObjectInputStream(fin);
    	ArrayList<User> users = (ArrayList<User>) oin.readObject();
    	System.out.println("All users");
    	for(int i = 0 ; i < users.size() ;i++) {
    		System.out.println(i+1 +": "+ users.get(i).toString());
    	}*/
        launch(args);
    }
}
