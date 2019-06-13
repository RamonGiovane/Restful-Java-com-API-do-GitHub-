package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {

	
	/*Uma abstra��o simples do que s�o os componentes em JavaFx � pensar como:
	 *  * um Stage sendo uma janela
	 *  * uma Scene sendo o conte�do dentro da janela
	 *  * o programa pode ter v�rios Stages, mas deve come�ar com uma padr�o.
	 *  * o
	 *   e v�rias Scenes*/
	@Override
	public void start(Stage primaryStage) {
		//requisicao();
		try {
			//Carrega o arquivo fxml dentro de um objeto Parent (classe base de todos os pain�is do JavaFX)
			Pane root = FXMLLoader.load(getClass().getResource("index.fxml"));

			//Insere na cena o conte�do carregado
			Scene scene = new Scene(root);

			//Define um t�tulo para a janela
			primaryStage.setTitle("Buscador de Seguidores do GitHub");

			//Define qual cena ocupar� a janela no momento
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
