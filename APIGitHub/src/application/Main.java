package application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {

	
	/* Uma abstração simples do que são os componentes em JavaFx é pensar como:
	 *  * um Stage sendo uma janela
	 *  * uma Scene sendo o conteúdo dentro da janela
	 *  * o programa pode ter vários Stages, mas deve começar com uma padrão.
	 *  */
	@Override
	public void start(Stage primaryStage) {
		try {
			//Carrega o arquivo fxml dentro de um objeto Parent (classe base de todos os painéis do JavaFX)
			Pane root = FXMLLoader.load(getClass().getResource("index.fxml"));

			//Insere na cena o conteúdo carregado
			Scene scene = new Scene(root);

			//Define um título para a janela
			primaryStage.setTitle("Buscador de Seguidores do GitHub");

			//Define qual cena ocupará a janela no momento
			primaryStage.setScene(scene);

			//Exibe a janela
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
