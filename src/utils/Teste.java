package utils;

import java.io.IOException;

public class Teste {

	public static void main(String[] args) throws IOException {
		Mapa map = new Mapa("teste.txt");
		map.criarMapa();
		map.printaTudo();

	}

}
