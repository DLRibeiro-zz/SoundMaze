package utils;

import java.io.IOException;

public class Teste {

	public static void main(String[] args) throws IOException {
		Mapa map = new Mapa("teste.txt");
		map.criarMapa();
		System.out.println("Boneco: " + map.achaBoneco().toString());
		System.out.println("Fonte: " + map.achaFonte().toString());
		map.printaTudo();
		/*System.out.println("\n");
		map.moveBoneco("w");
		map.printaTudo();
		System.out.println("\n");
		map.moveBoneco("s");
		map.printaTudo();
		System.out.println("\n");
		map.moveBoneco("a");
		map.printaTudo();
		System.out.println("\n");
		map.moveBoneco("d");
		map.printaTudo(); */

	}

}
