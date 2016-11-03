package utils;

import java.io.IOException;
import java.util.Scanner;

import sound.MultipleSound;
import sound.ObjectiveSound;
import sound.SoundSource;

public class Teste {
	
	private Ponto bonecao;
	
	public void MainVerdadeiro() throws IOException{
		Mapa map = new Mapa("teste.txt");
		map.criarMapa();
		System.out.println("Boneco: " + map.achaBoneco().toString());
		System.out.println("Fonte: " + map.achaFonte().toString());
		//MultipleSound sound = new MultipleSound();
		
		bonecao = new Ponto(map.achaBoneco().getX(), map.achaBoneco().getY(), map.achaBoneco().getZ());
		//sound.execute(0.0f+map.achaBoneco().getX(), 0.0f+map.achaBoneco().getY(),0.0f+ map.achaBoneco().getZ());
		SoundSource step1 = new SoundSource();
		SoundSource step2 = new SoundSource();
		SoundSource hitwall = new SoundSource();
		//bugado
		//ObjectiveSound obj = new ObjectiveSound(0.0f+map.achaFonte().getX(), 0.0f+map.achaFonte().getY(),0.0f+ map.achaFonte().getZ(), bonecao);
		//(new Thread(obj)).start();
		SoundSource objective = new SoundSource();
		map.printaTudo();
		boolean exit = false;
		Scanner in = new Scanner(System.in);
		while (!exit){
			String aux = in.nextLine();
			System.out.println("\n");
			switch (aux){ 
				case "w": //map.moveBoneco("w"); /*sound.andar(0.0f+map.achaBoneco().getX(), 0.0f+map.achaBoneco().getY(),0.0f+ map.achaBoneco().getZ());*/ map.printaTudo();  break;
				case "a": //map.moveBoneco("a"); /*sound.andar(0.0f+map.achaBoneco().getX(), 0.0f+map.achaBoneco().getY(),0.0f+ map.achaBoneco().getZ());*/ map.printaTudo();  break;
				case "s": //map.moveBoneco("s"); /*sound.andar(0.0f+map.achaBoneco().getX(), 0.0f+map.achaBoneco().getY(),0.0f+ map.achaBoneco().getZ());*/ map.printaTudo();  break;
				case "d": 
					boolean ret = map.moveBoneco(aux);
					if(!ret){
						//10f, 0f, 0f, 0f, 0f, 0f
						//step1.execute("p1.wav", 0f, 0f, 0f, 0f, 0f, 0f);
						//step2.execute("p2.wav", 0f, 0f, 0f, 0f, 0f, 0f);
					}else{
						//hitwall.execute("bodyhit.wav", 0f, 0f, 0f, 0f, 0f, 0f);
					}
					/*sound.andar(0.0f+map.achaBoneco().getX(), 0.0f+map.achaBoneco().getY(),0.0f+ map.achaBoneco().getZ());*/ 
					
					bonecao.setX(map.achaBoneco().getX());
					bonecao.setY(map.achaBoneco().getY());
					bonecao.setZ(map.achaBoneco().getZ());
					map.printaTudo();  
					break;
				case "e": exit = !exit;  break;
				case " ": 
					objective.execute("p1.wav", 0.0f+map.achaFonte().getY(), 0.0f+map.achaFonte().getX(), 0.0f+map.achaFonte().getZ(), 0.0f+map.achaBoneco().getY(), 0.0f+map.achaBoneco().getX(), 0.0f+map.achaBoneco().getZ());
					break;
			}
		}
		in.close();
	}
	
	public static void main(String[] args) throws IOException {
		(new Teste()).MainVerdadeiro();
	}

}
