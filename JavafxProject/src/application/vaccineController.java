package application;



import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class vaccineController {
	@FXML
	TableView<Vaccine> table1;
	
	public ObservableList<Vaccine> getProduct(){
		ObservableList<Vaccine> vaccines = FXCollections.observableArrayList();
		vaccines.add(new Vaccine(1, "Covax", "Moderna", LocalDate.parse("2022-08-12"), "Ladkrabang"));
		return vaccines;
	}
}
