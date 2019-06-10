package application;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class Controladora  implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	TextField textField;

	@FXML
	protected void pesquisar(ActionEvent action){
		System.out.println(textField.getText());
		Main.requisicao(textField.getText());
	}
}
