package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		requisicao();
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();



		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void requisicao() {
		URL url;
		System.out.println("teste");
		try {
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.254", 8080));
			url = new URL("https://api.github.com/users/RamonGiovane/followers");
			HttpURLConnection con = (HttpURLConnection) url.openConnection(proxy);
			con.setRequestMethod("GET");

			int status = con.getResponseCode();
			System.out.println(status);

			BufferedReader in = new BufferedReader(
					  new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer content = new StringBuffer();
					while ((inputLine = in.readLine()) != null) {
					    content.append(inputLine);
					}
					in.close();
		System.out.println(content.toString());
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
