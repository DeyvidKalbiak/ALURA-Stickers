package aluraStickers;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickersGenerated {

	public void geraStricker(InputStream inputStream, String nomeArquivo) throws IOException {

		// leitura da imagem
//		InputStream inputStream = new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_4.jpg")
//				.openStream();
		BufferedImage imagemOriginal = ImageIO.read(inputStream);

		// cria nova img em memória com transparencia e tamanho novo
		int largura = imagemOriginal.getWidth();
		int altura = imagemOriginal.getHeight();
		int novaAltura = altura - 400;
		BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

		// copiar a imagem original para nova img (em memória)
		Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
		graphics.drawImage(imagemOriginal, 0, 0, null);

		// configurando a fonte
		var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 32);
		graphics.setFont(fonte);

		// escrever uma frase na imagem
		graphics.drawString("TOPZERA", 100, novaAltura - 500);

		// escrever a nova img em um arquivo
		ImageIO.write(novaImagem, "png", new File("saida/filmeFigura.png"));

	}

}
