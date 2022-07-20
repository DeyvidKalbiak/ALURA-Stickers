package aluraStickers;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
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
			
			String urlImagem = mapFilme.get("image");
			String titulo = mapFilme.get("title");
			InputStream inputStream = new URL(urlImagem).openStream();

			String nomeArquivo = titulo + ".png";
			
			var geradora = new StickersGenerated();
			geradora.geraStricker(inputStream, nomeArquivo);
			System.out.println(mapFilme.get("title"));
			System.out.println();
		}

	}
}