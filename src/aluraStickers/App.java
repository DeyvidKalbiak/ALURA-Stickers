package aluraStickers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
	public static void main(String[] args) throws Exception {
		
		// Pegar os dados no IMDB
		String url = "https://raw.githubusercontent.com/alexfelipe/imersao-java/json/top250.json";
		URI endereco = URI.create(url);
		var client = HttpClient.newHttpClient(); // a partir do java 11 posso usar var
		HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body(); // Busca os dados no corpo no body, nesse caso no Json da api

		
		
		// Extraindo os dados do stringão (titulo, poster e classificação)
		JsonParser parse = new JsonParser();
		List<Map<String, String>> listaDeFilmes = parse.parse(body);
		
		// exibir e manipular os dados
		for (Map<String, String> mapFilme : listaDeFilmes) {
			System.out.println(mapFilme.get("title"));
			System.out.println(mapFilme.get("image"));
			System.out.println(mapFilme.get("imDbRating"));
			System.out.println(mapFilme.get("imDbRating"));
		}
		
	}
}