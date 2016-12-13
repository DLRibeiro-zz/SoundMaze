package utils;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.Scanner;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;

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
		
		//MODIFICACAO
		try{
			AL.create();
		} catch (LWJGLException le) {
			le.printStackTrace();
			return;
		}
		AL10.alGetError();
		
		//posicao do listener
		FloatBuffer listenerPos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f+map.achaBoneco().getY(), 0.0f+map.achaBoneco().getX(), 0.0f+map.achaBoneco().getZ()/*1.0f, 1.0f, 1.0f*/ }).rewind();
		//velocidade do listener
		FloatBuffer listenerVel = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
		/** Orientation of the listener. (first 3 elements are "at", second 3 are "up") */
		FloatBuffer listenerOri = (FloatBuffer)BufferUtils.createFloatBuffer(6).put(new float[] { 0.0f, 0.0f, -1.0f,  0.0f, 1.0f, 0.0f }).rewind();
		
		
		//sound.execute(0.0f+map.achaBoneco().getX(), 0.0f+map.achaBoneco().getY(),0.0f+ map.achaBoneco().getZ());
		SoundSource step1 = new SoundSource();
		step1.execute("p1.wav", 0.0f+map.achaBoneco().getY(), 0.0f+map.achaBoneco().getX(), 0.0f+map.achaBoneco().getZ(), listenerPos, listenerVel, listenerOri);
		SoundSource step2 = new SoundSource();
		step2.execute("p2.wav", 0.0f+map.achaBoneco().getY(), 0.0f+map.achaBoneco().getX(), 0.0f+map.achaBoneco().getZ(), listenerPos, listenerVel, listenerOri);
		SoundSource hitwall = new SoundSource();
		hitwall.execute("bodyhit.wav", 0.0f+map.achaBoneco().getY(), 0.0f+map.achaBoneco().getX(), 0.0f+map.achaBoneco().getZ(), listenerPos, listenerVel, listenerOri);
		SoundSource win = new SoundSource();
		win.execute("win.wav", 0.0f+map.achaBoneco().getY(), 0.0f+map.achaBoneco().getX(), 0.0f+map.achaBoneco().getZ(), listenerPos, listenerVel, listenerOri);
		//bugado
		//ObjectiveSound obj = new ObjectiveSound(0.0f+map.achaFonte().getX(), 0.0f+map.achaFonte().getY(),0.0f+ map.achaFonte().getZ(), bonecao);
		//(new Thread(obj)).start();
		//SoundSource objective = new SoundSource();
		ObjectiveSound obj = new ObjectiveSound(0.0f+map.achaFonte().getX(), 0.0f+map.achaFonte().getY(),0.0f+ map.achaFonte().getZ(), listenerPos, listenerVel, listenerOri);
		(new Thread(obj)).start();
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
					String  ret = map.moveBoneco(aux);
					if(ret.equals("false")){
//						10f, 0f, 0f, 0f, 0f, 0f
						//step1.execute("p1.wav", 0f, 0f, 0f, 0f, 0f, 0f);
						//step2.execute("p2.wav", 0f, 0f, 0f, 0f, 0f, 0f);
						step1.playSound();
						step2.playSound();
						//AQUI DANILO
						//nova posicao da fonte
						//obj.setObjectPosition(obj_x, obj_y, obj_z);
					}else if (ret.equals("true")){
						//hitwall.execute("bodyhit.wav", 0f, 0f, 0f, 0f, 0f, 0f);
						hitwall.playSound();
					} else {
						//win.execute("win.wav", 0f, 0f, 0f, 0f, 0f, 0f);
						obj.setJogando(false);
						win.playSound();
					}
					/*sound.andar(0.0f+map.achaBoneco().getX(), 0.0f+map.achaBoneco().getY(),0.0f+ map.achaBoneco().getZ());*/ 
					
					bonecao.setX(map.achaBoneco().getX());
					bonecao.setY(map.achaBoneco().getY());
					bonecao.setZ(map.achaBoneco().getZ());
					map.printaTudo();  
					break;
				case "e": exit = !exit;  break;
				case " ": 
					/*objective.execute 
					("latido.wav", 0.0f+map.achaFonte().getY(), 0.0f+map.achaFonte().getX(), 0.0f+map.achaFonte().getZ(), 0.0f+map.achaBoneco().getY(), 0.0f+map.achaBoneco().getX(), 0.0f+map.achaBoneco().getZ());
					*/break;
			}
		}
		in.close();
	}
	
	public static void main(String[] args) throws IOException {
		(new Teste()).MainVerdadeiro();
	}

}
