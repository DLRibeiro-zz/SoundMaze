package utils;

import java.io.IOException;
import java.util.Scanner;

public class Teste {

	public static void main(String[] args) throws IOException {
		Mapa map = new Mapa("teste.txt");
		map.criarMapa();
		System.out.println("Boneco: " + map.achaBoneco().toString());
		System.out.println("Fonte: " + map.achaFonte().toString());

		map.printaTudo();
		boolean exit = false;
		Scanner in = new Scanner(System.in);
		while (!exit){
			String aux = in.nextLine();
			System.out.println("\n");
			switch (aux){ 
				case "w": map.moveBoneco("w"); map.printaTudo();  break;
				case "a": map.moveBoneco("a"); map.printaTudo();  break;
				case "s": map.moveBoneco("s"); map.printaTudo();  break;
				case "d": map.moveBoneco("d"); map.printaTudo();  break;
				case "e": exit = !exit;  break;
			}
		}
	}

}
