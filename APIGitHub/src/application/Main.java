package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {

	private static final String URL_INICIAL ="https://api.github.com/users/", URL_FINAL = "/followers";
	/*Uma abstração simples do que são os componentes em JavaFx é pensar como:
	 *  * um Stage sendo uma janela
	 *  * uma Scene sendo o conteúdo dentro da janela
	 *  * o programa pode ter vários Stages, mas deve começar com uma padrão.
	 *  * o
	 *   e várias Scenes*/
	@Override
	public void start(Stage primaryStage) {
		//requisicao();
		try {
			//Carrega o arquivo fxml dentro de um objeto Parent (classe base de todos os painéis do JavaFX)
			Pane root = FXMLLoader.load(getClass().getResource("index.fxml"));

			//Insere na cena o conteúdo carregado
			Scene scene = new Scene(root);

			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			//Define um título para a janela
			primaryStage.setTitle("Buscador do GitHub");

			//Define qual cena ocupará a janela no momento
			primaryStage.setScene(scene);

			//Exibe a janela
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void requisicao(String nomeUsuario) {
		URL url;
		System.out.println("teste");
		try {
			//Primeiro, cria um objeto que define o proxy e a porta
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.254", 8080));

			//Cria um objeto URL de onde será a requisição
			url = new URL(URL_INICIAL + nomeUsuario + URL_FINAL);


			//Cria um objeto para a estabelcer uma conexão HTTP
			HttpURLConnection con = (HttpURLConnection) url.openConnection(proxy);

			//Faz uma chamada GET na URL fornecida.
			con.setRequestMethod("GET");

			//Verifica o status da requisição. 200  = sucesso!
			int status = con.getResponseCode();
			System.out.println(status);

			//Lendo os dados da requisição
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine).append("\n");
			}
			in.close();
			System.out.println(content.toString());

			JsonArray jsonArray = new JsonParser().parse(content.toString()).getAsJsonArray();
			Usuario usuario = new Usuario();


			for (JsonElement jsonElement : jsonArray) {
				JsonObject jsonObject = jsonElement.getAsJsonObject();

				System.out.println(jsonObject);

				usuario.setLogin(jsonObject.get("login").toString());
				usuario.setLinkPerfil(jsonObject.get("url").toString());
				usuario.setUrlImagemPerfil(jsonObject.get("avatar_url").toString());
			}

			System.out.println(usuario);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
