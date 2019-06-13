package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class Requisicao {
	 
	private Proxy proxy;
	private HttpURLConnection conexao;
	
	public Requisicao(String enderecoProxy, int porta) {
		proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(enderecoProxy, porta));
	}
	
	public Requisicao() {
		proxy = null;
	}
	
	public void requisitar(String endereco, String metodoDeRequisicao) 
			throws IOException {
		try {
		//Cria um objeto URL de onde ser� a requisi��o
		URL url = new URL(endereco);


		//Cria um objeto para a estabelcer uma conex�o HTTP
		if(proxy != null)
			conexao = (HttpURLConnection) url.openConnection(proxy);
		else
			conexao = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);

		//Faz uma chamada GET na URL fornecida.
		conexao.setRequestMethod(metodoDeRequisicao);
		}catch (Exception e) {
			throw new IOException(e.getCause());
		}
		
	}
	
	public int statusDeResposta() throws IOException {
		return conexao.getResponseCode();
	}
	
	private String lerDadosRequisicao() throws IOException {
		//Lendo os dados da requisi��o
		BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine).append("\n");
		}
		in.close();
		
		return content.toString();

	}
	
	/**Transforma o resultado de uma requisicão em um JSonArray*/
	public JsonArray asJSonArray() throws JsonSyntaxException, IOException {
		return new JsonParser().parse(lerDadosRequisicao()).getAsJsonArray();
	}
	
	
}
