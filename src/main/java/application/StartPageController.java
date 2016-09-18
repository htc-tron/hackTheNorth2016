package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;

public class StartPageController implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Model.initialize();
		
	}

	public void start(ActionEvent event) throws IOException{
		Model.start();
	}
	
	public void stop(ActionEvent event) throws IOException{
		Model.stop();
	}
	
	public void exit(ActionEvent event) throws IOException{
		((Node)event.getSource()).getScene().getWindow().hide();
	}
}
	
