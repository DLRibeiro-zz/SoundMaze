package utils;

import java.io.IOException;
import java.util.Scanner;

import sound.MultipleSound;

public class Teste {

	public static void main(String[] args) throws IOException {
		Mapa map = new Mapa("teste.txt");
		map.criarMapa();
		System.out.println("Boneco: " + map.achaBoneco().toString());
		System.out.println("Fonte: " + map.achaFonte().toString());
		MultipleSound sound = new MultipleSound();
		
	
		sound.execute(0.0f+map.achaBoneco().getX(), 0.0f+map.achaBoneco().getY(),0.0f+ map.achaBoneco().getZ());
		map.printaTudo();
		boolean exit = false;
		Scanner in = new Scanner(System.in);
		while (!exit){
			String aux = in.nextLine();
			System.out.println("\n");
			switch (aux){ 
				case "w": map.moveBoneco("w");sound.andar(0.0f+map.achaBoneco().getX(), 0.0f+map.achaBoneco().getY(),0.0f+ map.achaBoneco().getZ()); map.printaTudo();  break;
				case "a": map.moveBoneco("a");sound.andar(0.0f+map.achaBoneco().getX(), 0.0f+map.achaBoneco().getY(),0.0f+ map.achaBoneco().getZ()); map.printaTudo();  break;
				case "s": map.moveBoneco("s");sound.andar(0.0f+map.achaBoneco().getX(), 0.0f+map.achaBoneco().getY(),0.0f+ map.achaBoneco().getZ()); map.printaTudo();  break;
				case "d": map.moveBoneco("d"); map.printaTudo();  break;
				case "e": exit = !exit;  break;
			}
		}
		in.close();
	}

}
