package application;



import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class vaccineController extends TranferInfo{
	@FXML
	private TableView<Vaccine> table1;

	@FXML
    private TableColumn<Vaccine, Integer> col_number;
	
    @FXML
    private TableColumn<Vaccine, String> col_name;
    
	@FXML
    private TableColumn<Vaccine, String> col_company;

    @FXML
    private TableColumn<Vaccine, LocalDate> col_date;

    @FXML
    private TableColumn<Vaccine, String> col_hospital;
    
    @FXML
    private TextField numField;
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextField compField;
    
    @FXML
    private DatePicker dateField;
    
    @FXML
    private TextField hosField;
    
	@FXML
	private Pane subPane;
	ObservableList<Vaccine> vaccines;
	private FileOutputStream fout = null;
	private ObjectOutputStream oout = null;
	private FileInputStream fin = null;
	private ObjectInputStream oin = null;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TranslateTransition translate = new TranslateTransition();
		subPane.setLayoutY(0);
		translate.setNode(subPane);
		translate.setDuration(Duration.seconds(0.8));
		translate.setByY(24);
		translate.play();
	}
	public ObservableList<Vaccine> getItem(){
		if(getVaccines() == null) {
			System.out.println(getFirstname());
			vaccines = FXCollections.observableArrayList();
		}else if(getVaccines() != null){
			System.out.println("Hello2");
			for(int i = 0 ; i < getVaccines().size(); i++) {
				vaccines.add(getVaccines().get(i));
			}
		}
		return vaccines;
	}
	public void addItem() {
		vaccines.add(new Vaccine(Integer.parseInt(numField.getText()), nameField.getText(), compField.getText(), dateField.getValue(), hosField.getText()));
	}
	public void save(ActionEvent event) {
		try {
			fin = new FileInputStream("resource/user.txt");
			oin = new ObjectInputStream(fin);
			ArrayList<User> users = (ArrayList<User>) oin.readObject();
			for(int i = 0 ; i < users.size();i++) {
				ArrayList<Vaccine> temp = new ArrayList<Vaccine>();
				if(users.get(i).getUsername().equals(getUsername())) {
					for(int j = 0 ; j < vaccines.size(); j++) {
						temp.add(vaccines.get(j));
					}
					users.get(i).setVaccines(temp);
					user.setVaccines(temp);
				}
			}
			fout = new FileOutputStream("resource/user.txt");
			oout = new ObjectOutputStream(fout);
			oout.writeObject(users);
		}catch (Exception e) {
			System.err.println(e);
		}
	}
	public void setTable() {
		col_number.setCellValueFactory(new PropertyValueFactory<>("number"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_company.setCellValueFactory(new PropertyValueFactory<>("company"));
		col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
		col_hospital.setCellValueFactory(new PropertyValueFactory<>("hospital"));
		table1.setItems(getItem());
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
