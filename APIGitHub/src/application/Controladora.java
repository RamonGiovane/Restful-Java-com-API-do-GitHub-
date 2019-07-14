package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Controladora  implements Initializable{
	private final String URL_INICIAL ="https://api.github.com/users/", URL_FINAL = "/followers";
	private Requisicao requisicao;
	
	public Controladora() {
		//requisicao = new Requisicao("10.0.0.254", 8080);
		requisicao = new Requisicao();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {}

	@FXML
	TextField textField;
	
	@FXML
	VBox vBox;

	@FXML
	protected void pesquisar(ActionEvent action){
		System.out.println(textField.getText());
		limparVBox();
		preencherCaixaDeUsuarios(lerUsuarios(textField.getText()));
	}
	
	private void limparVBox() {
		vBox.getChildren().remove(0, vBox.getChildren().size());
	}
	
	private void preencherCaixaDeUsuarios(List<Usuario> usuariosList) {
		
		HBox card;
		
		for(Usuario usuario : usuariosList) {
			ImageView imagemPerfil = new ImageView(new Image(usuario.getUrlImagemPerfil().replace("\"", ""), 50.0, 50.0, true, false));
			card = new HBox();
			//card.setPrefWidth(BorderPane);

			card.setPrefHeight(50);
			card.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, new CornerRadii(1),null)));
			Label nomeUsuario = new Label(usuario.getLogin().split("\"")[1]);
			card.getChildren().addAll(imagemPerfil, nomeUsuario);
			card.setPadding(new Insets(15, 12, 15, 12));
		    card.setSpacing(10);
			vBox.getChildren().addAll(card);
		}
		
	}
	
	private List<Usuario> lerUsuarios(String nomeUsuario) {
		List<Usuario> usuariosList = new ArrayList<Usuario>();
		
		try {

			//Cria um objeto URL de onde ser� a requisi��o
			String url = URL_INICIAL + nomeUsuario + URL_FINAL;
			requisicao.requisitar(url, "GET");
				
		
			//Verifica o status da requisi��o. 200  = sucesso!
			System.out.println(requisicao.statusDeResposta());

			JsonArray jsonArray = requisicao.asJSonArray();

			Usuario usuario;
			
			for (JsonElement jsonElement : jsonArray) {
				JsonObject jsonObject = jsonElement.getAsJsonObject();

				usuario = new Usuario();
				
				usuario.setLogin(jsonObject.get("login").toString());
				usuario.setLinkPerfil(jsonObject.get("url").toString());
				usuario.setUrlImagemPerfil(jsonObject.get("avatar_url").toString());
				
				usuariosList.add(usuario);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usuariosList;
	}

}
